package example

class Test {
    static void main(String[] args) {
        def s1 = $/Failing string\/$
        def s2 = $/Will not be parsed as a new line/$

        println s1
    }
}
