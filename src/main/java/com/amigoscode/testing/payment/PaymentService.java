package com.amigoscode.testing.payment;

import com.amigoscode.testing.customer.CustomerRepository;
import com.amigoscode.testing.sms.MockSmsService;
import com.amigoscode.testing.sms.Sms;
import com.amigoscode.testing.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private static final List<Currency> ACCEPTED_CURRENCIES = List.of(Currency.USD, Currency.GBP);

    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    private final CardPaymentCharger cardPaymentCharger;
    private final SmsService smsService;

    @Autowired
    public PaymentService(CustomerRepository customerRepository,
                          PaymentRepository paymentRepository,
                          CardPaymentCharger cardPaymentCharger, SmsService smsService) {
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
        this.cardPaymentCharger = cardPaymentCharger;
        this.smsService = smsService;
    }

    void chargeCard(UUID customerId, PaymentRequest paymentRequest) {
        // 1. Does customer exists if not throw
        boolean isCustomerFound = customerRepository.findById(customerId).isPresent();
        if (!isCustomerFound) {
            throw new IllegalStateException(String.format("Customer with id [%s] not found", customerId));
        }

        // 2. Do we support the currency if not throw
        Currency currency = paymentRequest.getPayment().getCurrency();
        boolean isCurrencySupported = ACCEPTED_CURRENCIES.contains(currency);

        if (!isCurrencySupported) {
            String message = String.format(
                    "Currency [%s] not supported",
                    currency);
            throw new IllegalStateException(message);
        }

        // 3. Charge card
        String cardSource = paymentRequest.getPayment().getSource();
        BigDecimal amount = paymentRequest.getPayment().getAmount();
        String description = paymentRequest.getPayment().getDescription();
        CardPaymentCharge cardPaymentCharge = cardPaymentCharger.chargeCard(
                cardSource,
                amount,
                currency,
                description
        );

        // 4. If not debited throw
        if (!cardPaymentCharge.isCardDebited()) {
            throw new IllegalStateException(String.format("Card not debited for customer %s", customerId));
        }

        // 5. Insert payment
        paymentRequest.getPayment().setCustomerId(customerId);

        paymentRepository.save(paymentRequest.getPayment());

        // 6. TODO: send sms
        String smsMessage = String.format("Card [%s] is debited for [%s %s] by %s.",
                cardSource, amount, currency, customerRepository.findById(customerId).get().getName());
        Sms sms = new Sms();
        sms.setMessage(smsMessage);
        sms.setPaymentId(paymentRequest.getPayment().getPaymentId());
        smsService.sendSms(sms,
                customerRepository.findById(customerId).get().getPhoneNumber());
        smsService.smsSave(sms);
    }
}
