package com.example

import org.testcontainers.containers.GenericContainer
import spock.lang.Specification

class ExampleSpec extends Specification {

    def "test"() {
        given:
        def container = new GenericContainer("busybox:1.33.0")

        expect:
        container.start()

        cleanup:
        container.stop()
    }
}
