package com.amigoscode.testing.sms;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;

public class TwilioApi {
    @Value("{corporate.phone.number.from}")
    private static String corporatePhoneNumberFrom;

    public static Message create(String text, String phoneNumber) {
        return Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(corporatePhoneNumberFrom), text).create();
    }
}
