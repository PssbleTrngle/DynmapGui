val sgui_version: String by extra
val dynmap_version: String by extra
val porting_lib_version: String by extra

plugins {
    id("com.possible-triangle.gradle") version ("0.2.2")
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

    maven {
        url = uri("https://mvn.devos.one/releases/")
        content {
            includeGroup("io.github.fabricators_of_create.Porting-Lib")
        }
    }
}

fabric {
    includesMod("eu.pb4:sgui:${sgui_version}")
    includesMod("io.github.fabricators_of_create.Porting-Lib:data:${porting_lib_version}")

    dataGen()
}

dependencies {
    modImplementation("maven.modrinth:dynmap:${dynmap_version}")
}

enablePublishing {
    githubPackages()
}

uploadToCurseforge()
uploadToModrinth {
    syncBodyFromReadme()
}