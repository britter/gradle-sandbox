package com.example.builder;

import com.example.EventStreamSerializationFormat;
import com.example.IncomingBuildAndPayload;

public class TestIncomingBuildBuilder {

    public static IncomingBuildAndPayload fullTestBuild() {
        return fullTestBuild(new EventStreamSerializationFormat());
    }

    public static IncomingBuildAndPayload fullTestBuild(EventStreamSerializationFormat eventStreamSerializationFormat) {
        return new IncomingBuildAndPayload();
    }
}
