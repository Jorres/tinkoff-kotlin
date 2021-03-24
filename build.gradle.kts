import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "me.home"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.2.0")

    testImplementation("io.mockk:mockk:1.10.6")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}