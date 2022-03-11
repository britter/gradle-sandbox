package com.example.codenarc

import groovy.sql.Sql

class Library {
    def withoutCatch() {
        try (Sql sql = new Sql(null)) {
            return true
        }
    }

    def withCatch() {
        try (Sql sql = new Sql(null)) {
            return true
        } catch (Exception ex) {
            return false
        }
    }
}
