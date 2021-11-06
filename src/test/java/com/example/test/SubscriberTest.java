package com.example.test;

import com.example.test.model.ExchangeRate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.test.TestData.*;

class SubscriberTest {

    @Test
    void handleEvent() {
        SUBSCRIBER_2.handleEvent(RATE_4);
        ExchangeRate actual = EXCHANGER.getRates(SUBSCRIBER_2).get(0);
        Assertions.assertEquals(RATE_4, actual);
    }
}