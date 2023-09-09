plugins {
    kotlin("jvm") version "1.9.10"
}

group = "dev.techullurgy"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":data"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}