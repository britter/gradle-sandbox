package com.example

import geb.spock.GebSpec

class SimpleSpec extends GebSpec {

    def "go to page"() {
        expect:
        to GebHomePage
    }
}
