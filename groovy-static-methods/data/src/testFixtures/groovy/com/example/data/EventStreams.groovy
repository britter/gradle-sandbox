package com.example.data

import com.example.model.EventData

import java.util.function.Consumer

class EventStreams {

    @SafeVarargs
    static Consumer<? super DataBuilder> testEventStream(DataBuilder<? extends EventData>... customBuilders) {
        return {}
    }

    static Consumer<? super DataBuilder> filter(Consumer<? super DataBuilder> incoming, Collection<Class<? extends EventData>> typesToRemove) {
        return { DataBuilder builder ->
            incoming.accept(builder)
            builder.removeEventTypes(typesToRemove)
        }
    }
}