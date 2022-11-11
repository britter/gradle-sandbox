plugins {
    id("mybuild.docker-application")
}

dockerApplications {
    create("simple") {
        imageName.set("simple-image")
    }
}

