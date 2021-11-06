package com.example.test.exchange;

import com.example.test.model.ExchangeRate;
import com.example.test.model.Subscriber;

import java.util.List;

public interface Exchanger {

    void put(Subscriber subscriber, ExchangeRate exchangeRate);

    void remove(Subscriber subscriber);

    List<ExchangeRate> getRates(Subscriber subscriber);
}
