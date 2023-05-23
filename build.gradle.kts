import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
}

group = "fr.utbm.airsim"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")

    implementation("org.msgpack:msgpack:0.6.12")
    implementation("org.msgpack:msgpack-core:0.8.20")
    implementation("com.github.stampery:msgpack-rpc:0.7.1") {
        exclude("org.msgpack:msgpack")
    }

    testImplementation(kotlin("test"))
}
