package com.example

class SimpleSpecification extends BaseSpecification {

    def "simple test"() {
        given:
        def foo = foo("foo")
        def bar = "bar"

        when:
        foo.toString()

        then:
        assert bar != null

        cleanup:
        bar.length()
    }
}
