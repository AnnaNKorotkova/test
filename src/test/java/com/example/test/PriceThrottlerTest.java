package com.example.test;

import com.example.test.exchange.Exchanger;
import com.example.test.model.ExchangeRate;
import com.example.test.model.Subscriber;
import com.example.test.processor.PriceProcessor;
import com.example.test.processor.PriceThrottler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.example.test.TestData.EXCHANGER;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PriceThrottlerTest {


    private final Exchanger exchanger = EXCHANGER;
    private final PriceProcessor priceThrottler = new PriceThrottler(exchanger);


    @Test
    void onPrice() {
        Subscriber subscriber = mock(Subscriber.class);
        ExchangeRate rate = mock(ExchangeRate.class);
        priceThrottler.subscribe(subscriber);

        priceThrottler.onPrice(rate.getCcyPair(), rate.getRate());
        verify(subscriber).handleEvent(any(ExchangeRate.class));

    }

    @Test
    void subscribe() throws Exception {
        Subscriber subscriber = mock(Subscriber.class);
        priceThrottler.subscribe(subscriber);
        int size = 0;
        Class<? extends PriceProcessor> throttlerClass = priceThrottler.getClass();
        Field[] fields = throttlerClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldVal = field.get(priceThrottler);
            if (fieldVal instanceof CopyOnWriteArrayList) {
                List<?> arrayListField = (List<?>) fieldVal;
                size = arrayListField.size();
            }
        }
        Assertions.assertEquals(1, size);
    }

    @Test
    void subscribeNll() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> priceThrottler.subscribe(null));
    }

    @Test
    void unsubscribe() throws Exception {
        Subscriber subscriber = mock(Subscriber.class);
        priceThrottler.subscribe(subscriber);
        int size = 1;
        priceThrottler.unsubscribe(subscriber);
        Class<? extends PriceProcessor> throttlerClass = priceThrottler.getClass();
        Field[] fields = throttlerClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldVal = field.get(priceThrottler);
            if (fieldVal instanceof CopyOnWriteArrayList) {
                List<?> arrayListField = (List<?>) fieldVal;
                size = arrayListField.size();
            }
        }
        Assertions.assertEquals(0, size);
    }

    @Test
    void unsubscribeNll() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> priceThrottler.unsubscribe(null));
    }
}