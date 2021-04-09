package com.example.data;

import com.example.model.EventData;

import java.util.Collection;

public class DataBuilder {

    public DataBuilder event(EventData data) {
        return this;
    }

    public void removeEventTypes(Collection<Class<? extends EventData>> types) {

    }
}
