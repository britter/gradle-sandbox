import examplebuild.polymorphiccontainer.Bar
import examplebuild.polymorphiccontainer.ContainerType
import examplebuild.polymorphiccontainer.Foo

val polymorphicContainer = objects.polymorphicDomainObjectContainer(ContainerType::class.java).apply {
    registerBinding(Foo::class.java, Foo::class.java)
    registerBinding(Bar::class.java, Bar::class.java)
}

// TypeOf parameter is required! Otherwise, the generated accessor will be types with NamedDomainObjectContainer
// instead of PolymorphicDomainObjectContainer, which results in create(String, Class) not being available
extensions.add(
    object : TypeOf<PolymorphicDomainObjectContainer<ContainerType>>() {},
    ContainerType.NAME,
    polymorphicContainer
)
