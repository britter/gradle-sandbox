package com.example.data;

import java.util.function.Consumer;

public class Builder {

    public DataBuilder events = new DataBuilder();

    public static Data testData(Consumer<? super Builder> consumer) {
        var builder = new Builder();
        consumer.accept(builder);
        return builder.build();
    }

    public Builder events(Consumer<? super DataBuilder> consumer) {
        consumer.accept(events);
        return this;
    }

    public Data build() {
        return new Data();
    }
}
