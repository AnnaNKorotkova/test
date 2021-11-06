package com.example.test;

import com.example.test.exchange.Exchanger;
import com.example.test.exchange.exchagerImpl.ExchangerImpl;
import com.example.test.model.ExchangeRate;
import com.example.test.model.Subscriber;

import java.util.List;


public class TestData {
    public final static Exchanger EXCHANGER = new ExchangerImpl();

    public final static Subscriber SUBSCRIBER_1 = new Subscriber("Subs_1", EXCHANGER);
    public final static Subscriber SUBSCRIBER_2 = new Subscriber("Subs_2", EXCHANGER);
    public final static Subscriber SUBSCRIBER_3 = new Subscriber("Subs_3", EXCHANGER);
    public final static Subscriber SUBSCRIBER_4 = new Subscriber("Subs_4", EXCHANGER);

    public final static ExchangeRate RATE_1 = new ExchangeRate("USDRUB", 75.99);
    public final static ExchangeRate RATE_2 = new ExchangeRate("USDRUB", 72.28);
    public final static ExchangeRate RATE_3 = new ExchangeRate("USDEUR", 0.82);
    public final static ExchangeRate RATE_4 = new ExchangeRate("USDEUR", 0.86);

    private static final List<Subscriber> list = List.of(SUBSCRIBER_1, SUBSCRIBER_2, SUBSCRIBER_3, SUBSCRIBER_4);
}
