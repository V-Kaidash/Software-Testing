package com.amigoscode.testing.sms;

import java.util.UUID;

public class Sms {

    private int id;

    private String message;

    private UUID customerId;

    public Sms(){}

    public Sms(int id, String message, UUID customerId) {
        this.id = id;
        this.message = message;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
