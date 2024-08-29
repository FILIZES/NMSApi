plugins {
    `java-library`
}

group = "me.filizes"
version = "1.0"

allprojects {
    repositories {
        mavenCentral()
        maven("https://repo.codemc.io/repository/nms/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

subprojects {
    apply {
        plugin("java-library")
    }

    tasks {
        withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
            targetCompatibility = "17"
            sourceCompatibility = "17"
        }
    }

    dependencies {
        compileOnly("org.spigotmc:spigot-api:1.13.2-R0.1-SNAPSHOT")

        compileOnly("org.projectlombok:lombok:1.18.34")
        annotationProcessor("org.projectlombok:lombok:1.18.34")
    }
}