package com.example.app

import com.example.model.SomeEvent
import com.example.model.SomeOtherEvent

import static com.example.data.EventStreams.filter
import static com.example.data.EventStreams.testEventStream
import static com.example.data.Builder.testData
import static com.example.data.EventBuilder.elem

class Test extends BaseTest {

    def "some test"() {
        given:
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeEvent]))
            e.events.event(new SomeEvent($/WellHowAboutThatThen/$, [elem(':').build()]))
        })
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeEvent, SomeOtherEvent]))
            e.events.event(new SomeEvent($/Not\ this\\\ one\\/$, [elem(':').build()]))
        })
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeEvent, SomeOtherEvent]))
            e.events.event(new SomeEvent($/\_%*/$, [elem(':').build()]))
        })
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeEvent, SomeOtherEvent]))
            e.events.event(new SomeEvent('', [elem(':').build()]))
        })
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeEvent]))
        })

        expect:
        assertResultsForShortStringMatch("rootProjectNames")
    }

    def "another test"() {
        given:
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeOtherEvent]))
            e.events.event(new SomeOtherEvent($/WellHowAboutThatThen/$, null, null, []))
        })
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeOtherEvent]))
            e.events.event(new SomeOtherEvent($/Not\ this\\\ one\\/$, null, null, []))
        })
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeOtherEvent]))
            e.events.event(new SomeOtherEvent($/\_%*/$, null, null, []))
        })
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeOtherEvent]))
            e.events.event(new SomeOtherEvent('', null, null, []))
        })
        insert(testData { e ->
            e.events(filter(testEventStream(), [SomeOtherEvent]))
        })

        expect:
        assertResultsForShortStringMatch("usernames")
    }

    private void assertResultsForShortStringMatch(String key) {

    }
}
