package com.example.test.model;

import java.util.Objects;

public class ExchangeRate {

    private final String ccyPair;
    private final Double rate;

    public ExchangeRate(String ccyPair, Double rate) {
        this.ccyPair = ccyPair;
        this.rate = rate;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public Double getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeRate rate1 = (ExchangeRate) o;

        if (!Objects.equals(ccyPair, rate1.ccyPair)) return false;
        return Objects.equals(rate, rate1.rate);
    }

    @Override
    public int hashCode() {
        int result = ccyPair != null ? ccyPair.hashCode() : 0;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "ccyPair='" + ccyPair + '\'' +
                ", rate=" + rate +
                '}';
    }
}
