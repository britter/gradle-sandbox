abstract class Base {
    def foo() {
        return "foo"
    }
}

class Child extends Base {
    def foo

    def setup() {
        foo = foo()
    }
}

def child = new Child()
child.setup()
