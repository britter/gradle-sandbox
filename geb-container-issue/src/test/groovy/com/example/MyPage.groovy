package com.example

import geb.Page
import geb.Module

class ManualsMenuModule extends Module {
    static content = {
        toggle { $("div.menu a.manuals") }
        linksContainer { $("#manuals-menu") }
        links { linksContainer.find("a") }
    }

    void open() {
        toggle.click()
        waitFor { !linksContainer.hasClass("animating") }
    }
}

class GebHomePage extends Page {
    static url = "http://gebish.org"

    static at = { title == "Geb - Very Groovy Browser Automation" }

    static content = {
        // having a content definition called container makes the test fail
//        manualsMenu { module(ManualsMenuModule) }
        container { module(ManualsMenuModule) }
    }
}

class TheBookOfGebPage extends Page {
    static at = { title.startsWith("The Book Of Geb") }
}