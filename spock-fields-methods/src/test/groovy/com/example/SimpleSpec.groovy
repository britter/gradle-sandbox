package com.example

class SimpleSpec extends BaseSpec {

    def foo

    def setup() {
        foo = foo()
    }

    def "some test"() {
        expect:
        foo == "foo"
    }

    static void main(String[] args) {
        SimpleSpec spec = new SimpleSpec()
        spec.setup()
        println spec.foo
    }
}
