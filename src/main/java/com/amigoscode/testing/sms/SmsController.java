package com.amigoscode.testing.sms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @GetMapping("/{customerId}")
    public Sms getSmsByCustomerId
}
