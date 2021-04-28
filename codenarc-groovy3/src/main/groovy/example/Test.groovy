package example

import java.lang.annotation.ElementType
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

class Test {
    @Annotation(condition = { int number ->
        number > 30
        && number < 80
    })
    static void main(String[] args) {
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.TYPE, ElementType.METHOD])
@Inherited
@interface Annotation {
    Class<? extends Closure> condition()
}