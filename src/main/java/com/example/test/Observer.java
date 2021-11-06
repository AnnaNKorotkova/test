package com.example.test;

import com.example.test.model.ExchangeRate;

public interface Observer {

    void handleEvent(ExchangeRate rate);
}
