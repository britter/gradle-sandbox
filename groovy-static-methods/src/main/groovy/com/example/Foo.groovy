package com.example

import static com.example.Bar.bar

class Foo {

    static void main(String[] args) {
        foo(bar())
    }

    static foo(String val) {
        println val
    }
}
