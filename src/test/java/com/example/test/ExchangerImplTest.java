package com.example.test;

import com.example.test.model.ExchangeRate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.test.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class ExchangerImplTest {

    @Test
    void putOneRate() {
        EXCHANGER.put(SUBSCRIBER_1, RATE_1);
        List<ExchangeRate> rates = EXCHANGER.getRates(SUBSCRIBER_1);
        assertNotNull(rates);
        assertEquals(1, rates.size());
        assertTrue(rates.contains(RATE_1));
    }


    @Test
    void putMultiRate() {
        EXCHANGER.put(SUBSCRIBER_1, RATE_1);
        EXCHANGER.put(SUBSCRIBER_1, RATE_2);
        EXCHANGER.put(SUBSCRIBER_1, RATE_3);
        List<ExchangeRate> rates = EXCHANGER.getRates(SUBSCRIBER_1);
        assertNotNull(rates);
        assertEquals(2, rates.size());
        assertTrue(rates.contains(RATE_2));
        assertTrue(rates.contains(RATE_3));
    }

    @Test
    void remove() {
        EXCHANGER.put(SUBSCRIBER_1, RATE_1);
        EXCHANGER.remove(SUBSCRIBER_1);
        List<ExchangeRate> rates = EXCHANGER.getRates(SUBSCRIBER_1);
        assertNull(rates);
    }
}