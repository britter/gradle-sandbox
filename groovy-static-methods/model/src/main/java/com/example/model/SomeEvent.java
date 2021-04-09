package com.example.model;

import java.util.List;

public class SomeEvent implements EventData {

    public String name;
    public List<? extends SomeElement> elements;

    public SomeEvent(String name, List<? extends SomeElement> elements) {
        this.name = name;
        this.elements = elements;
    }
}
