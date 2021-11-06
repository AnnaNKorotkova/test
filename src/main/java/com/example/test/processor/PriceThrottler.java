package com.example.test.processor;

import com.example.test.exchange.Exchanger;
import com.example.test.model.ExchangeRate;
import com.example.test.model.Subscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class PriceThrottler implements PriceProcessor {

    private final List<Subscriber> subscribers = new CopyOnWriteArrayList<>();
    private final Exchanger exchanger;
    private final Logger log = Logger.getLogger(PriceThrottler.class.getName());

    public PriceThrottler(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void onPrice(String ccyPair, double rate) {
        log.info("Starting onPrice for all subscribers");
        subscribers.parallelStream()
                .forEach(subscriber -> subscriber.handleEvent(new ExchangeRate(ccyPair, rate)));
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber couldn`t be null");
        }
        log.info(String.format("Subscribe %s", subscriber));
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber couldn`t be null");
        }
        log.info(String.format("Unsubscribe %s", subscriber));
        subscribers.remove(subscriber);
        log.info(String.format("Remove subscriber %s from exchanger", subscriber));
        exchanger.remove(subscriber);
    }
}
