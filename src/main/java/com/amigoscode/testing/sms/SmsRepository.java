package com.amigoscode.testing.sms;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends CrudRepository<Sms, Integer> {
    @Query(nativeQuery = true,
    value = "SELECT * FROM sms WHERE payment_id = (:paymentId) LIMIT 1;")
    Sms findByPaymentId(long paymentId);
}
