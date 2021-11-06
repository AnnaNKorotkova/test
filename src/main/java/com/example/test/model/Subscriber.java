package com.example.test.model;

import com.example.test.Observer;
import com.example.test.exchange.Exchanger;

import java.util.Objects;
import java.util.logging.Logger;

public class Subscriber implements Observer {
    private final Logger log = Logger.getLogger(Subscriber.class.getName());

    private final String uniqueName;
    private final Exchanger exchanger;

    public Subscriber(String name, Exchanger exchanger) {
        this.uniqueName = name;
        this.exchanger = exchanger;
    }


    @Override
    public void handleEvent(ExchangeRate rate) {
        log.info("Putting rate subscriber");
        exchanger.put(this, rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscriber that = (Subscriber) o;

        return Objects.equals(uniqueName, that.uniqueName);
    }

    @Override
    public int hashCode() {
        return uniqueName != null ? uniqueName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "uniqueName='" + uniqueName + '\'' +
                ", exchanger=" + exchanger +
                '}';
    }
}
