dependencies {
    implementation(project(":api"))
}

tasks {
    jar {

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from (
            configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
        )

    }

}
