package com.example.test.exchange.exchagerImpl;

import com.example.test.exchange.Exchanger;
import com.example.test.model.ExchangeRate;
import com.example.test.model.Subscriber;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ExchangerImpl implements Exchanger {

    private final Map<Subscriber, CopyOnWriteArrayList<ExchangeRate>> exchangeMap = new ConcurrentHashMap<>();
    private final Logger log = Logger.getLogger(ExchangerImpl.class.getName());

    @Override
    public void put(Subscriber subscriber, ExchangeRate exchangeRate) {
        log.info(String.format("Put subscriber %s and exchangeMap %s to map", subscriber, exchangeRate));
        List<ExchangeRate> exchangeRates = exchangeMap.get(subscriber);
        if (Objects.nonNull(exchangeRates)) {
            CopyOnWriteArrayList<ExchangeRate> filtered = exchangeRates.parallelStream()
                    .filter(r -> !Objects.equals(r.getCcyPair(), exchangeRate.getCcyPair()))
                    .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
            filtered.add(exchangeRate);
            exchangeMap.put(subscriber, filtered);
        } else {
            exchangeMap.put(subscriber, new CopyOnWriteArrayList<>(List.of(exchangeRate)));
        }
    }

    @Override
    public void remove(Subscriber subscriber) {
        log.info("Remove subscriber " + subscriber);
        exchangeMap.remove(subscriber);
    }

    @Override
    public List<ExchangeRate> getRates(Subscriber subscriber) {
        log.info("Getting all rates for subscriber " + subscriber);
        return exchangeMap.get(subscriber);
    }
}
