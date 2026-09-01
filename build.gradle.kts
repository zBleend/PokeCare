plugins {
    kotlin("jvm") version "2.4.10"
    application
}

application {
    mainClass.set("cl.ejercicio.MainKt")
}

group = "cl.ejercicio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}