package com.amigoscode.testing.sms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sms {

    @Id
    @GeneratedValue
    private int id;

    private String message;

    private Long paymentId;

    public Sms(){}

    public Sms(int id, String message, Long paymentId) {
        this.id = id;
        this.message = message;
        this.paymentId = paymentId;
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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", paymentId=" + paymentId +
                '}';
    }
}
