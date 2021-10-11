package com.amigoscode.testing.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        value = "twilio.enabled",
        havingValue = "true"
)
public class SmsService implements SmsSending {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    public static final String AUTH_TOKEN = "your_auth_token";

    @Override
    public SmsSend sendSms(Sms sms, String phoneNumber) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = TwilioApi.create(sms.getMessage(), phoneNumber);
        if(message.getErrorMessage() != null){
            throw new IllegalStateException(message.getErrorMessage());
        } else {
            return new SmsSend(true);
        }
    }
}
