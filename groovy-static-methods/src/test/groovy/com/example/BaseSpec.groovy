package com.example

import spock.lang.Specification

class BaseSpec extends Specification {

    protected BuildIds insertAndActivateBuildWithData(IncomingBuildAndPayload data) {
        return new BuildIds()
    }
}
