package com.amigoscode.testing.sms;

public class SmsSend {

    private final boolean isSmsSent;

    public SmsSend(boolean isSmsSent){
        this.isSmsSent = isSmsSent;
    }

    public boolean isSmsSent(){return isSmsSent;}

    @Override
    public String toString() {
        return "SmsSend{" +
                "isSmsSent=" + isSmsSent +
                '}';
    }
}
