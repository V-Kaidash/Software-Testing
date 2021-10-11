package com.amigoscode.testing.sms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        value = "stripe.enabled",
        havingValue = "false"
)
public class MockSmsService implements SmsSending {
    @Override
    public SmsSend sendSms(Sms sms, String phoneNumber) {
        return new SmsSend(true);
    }
}
