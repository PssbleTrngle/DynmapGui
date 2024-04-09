val sgui_version: String by extra
val dynmap_version: String by extra

plugins {
    id("com.possible-triangle.gradle") version ("0.1.5")
}

withKotlin()

repositories {
    modrinthMaven()

    maven {
        url = uri("https://maven.nucleoid.xyz")
        content {
            includeGroup("eu.pb4")
            includeGroup("xyz.nucleoid")
        }
    }
}

fabric {
    includesMod("eu.pb4:sgui:${sgui_version}")
}

dependencies {
    modImplementation("maven.modrinth:dynmap:${dynmap_version}")
}

enablePublishing {
    githubPackages()
}

uploadToCurseforge()
uploadToModrinth()