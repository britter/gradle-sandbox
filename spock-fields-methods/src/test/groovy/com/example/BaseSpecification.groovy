package com.example

import spock.lang.Specification

abstract class BaseSpecification extends Specification {

    def foo(String arg) {
        return arg
    }
}
