package example

import spock.lang.Specification

class ExampleTest extends Specification {

    @Annotation(
            condition = {
                int s ->
                    s > 30
                            && s < 80
            },
            reason = "xyz"
    )
    def "test"() {
        given:
        def x = 20

        when:
        x *= 20

        then:
        x > 100
    }
}
