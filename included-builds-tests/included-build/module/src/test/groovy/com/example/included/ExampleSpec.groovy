package com.example.included

import spock.lang.Specification

class ExampleSpec extends Specification {

    def "first test"() {
        expect:
        true
    }

    def "second test"() {
        expect:
        1 + 1 == 2
    }
}