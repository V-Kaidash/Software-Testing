package com.amigoscode.testing.sms;

public interface SmsService {
    SmsSend sendSms(Sms sms, String phoneNumber);
    void smsSave(Sms sms);
}
