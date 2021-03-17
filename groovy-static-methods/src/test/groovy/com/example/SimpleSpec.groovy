package com.example

import static com.example.builder.TestIncomingBuildBuilder.fullTestBuild

class SimpleSpec extends BaseSpec {

    def "should redirect when no build cache event"() {
        given:
        def pubId = insertAndActivateBuildWithData(fullTestBuild())

        when:
        pubId

        then:
        pubId
    }
}
