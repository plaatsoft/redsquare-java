plugins {
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("com.gradleup.shadow") version "9.4.2"
    java
    application
}

group = "nl.plaatsoft.redsquare"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // === JavaFX ===
    implementation("org.openjfx:javafx-controls:26.0.1")
    implementation("org.openjfx:javafx-fxml:26.0.1")
    implementation("org.openjfx:javafx-media:26.0.1")
    implementation("org.openjfx:javafx-graphics:26.0.1")

    // === JSON ===
    implementation("org.json:json:20260522")

    // === LOG4J ===
    implementation("org.apache.logging.log4j:log4j-core:2.26.0")
    implementation("org.apache.logging.log4j:log4j-api:2.26.0")
}

application {
    mainClass.set("nl.plaatsoft.redsquare.Launcher")
}

// Shadow JAR (recommended for easy distribution)
tasks.shadowJar {
    archiveBaseName.set("redsquare")
    manifest {
        attributes["Main-Class"] = "nl.plaatsoft.redsquare.Launcher"
    }
}