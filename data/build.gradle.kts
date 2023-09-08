val ktormVersion: String by project

plugins {
//    id("java")
    kotlin("jvm") version "1.9.10"
}

group = "dev.techullurgy"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.ktorm:ktorm-core:${ktormVersion}")
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}