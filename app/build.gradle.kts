plugins {
    java
    application
    kotlin("jvm") version "1.9.0"
    id("org.openjfx.javafxplugin").version("0.0.13")

}

group = "edu.austral.dissis.chess"
version = "1.0.0"

repositories {
//    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/chess-ui")
        credentials {
            username = project.properties["github.user"] as String
            password = project.properties["github.token"] as String
        }
    }
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/chess-simple-client-server")
        credentials {
            username = project.properties["github.user"] as String
            password = project.properties["github.token"] as String
        }
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("edu.austral.dissis.chess:chess-ui:2.0.1")
    implementation("edu.austral.dissis.chess:simple-client-server:1.2.0")
    implementation("org.testng:testng:7.7.0")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

javafx {
    version = "18"
    modules = listOf("javafx.graphics")
}

application {
    // Define the main class for the application.
    mainClass.set("src.App.ChessApplication")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
