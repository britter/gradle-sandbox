import examplebuild.polymorphiccontainer.Bar
import examplebuild.polymorphiccontainer.Foo

plugins {
    id("examplebuild.polymorphic-container")
}

polymorphicContainer {
    create<Foo>("foo") {
        foo.set("foo")
    }
    create<Bar>("bar") {
        bar.set(12)
    }
}
