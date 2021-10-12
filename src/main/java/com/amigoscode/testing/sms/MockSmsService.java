package com.amigoscode.testing.sms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        value = "twilio.enabled",
        havingValue = "false"
)
public class MockSmsService implements SmsService {

    private final SmsRepository smsRepository;

    public MockSmsService(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }
    @Override
    public SmsSend sendSms(Sms sms, String phoneNumber) {
        return new SmsSend(true);
    }

    @Override
    public void smsSave(Sms sms){
        smsRepository.save(sms);
    }
}
