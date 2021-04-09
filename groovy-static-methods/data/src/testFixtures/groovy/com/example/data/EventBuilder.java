package com.example.data;

import com.example.model.SomeElement;

public class EventBuilder {

    private String name;

    public EventBuilder(String name) {
        this.name = name;
    }

    public static EventBuilder elem(String name) {
        return new EventBuilder(name);
    }

    public SomeElement build() {
        return new SomeElement(name);
    }
}
