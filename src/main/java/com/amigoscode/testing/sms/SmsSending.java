package com.amigoscode.testing.sms;

public interface SmsSending {
    SmsSend sendSms(Sms sms, String phoneNumber);
}
