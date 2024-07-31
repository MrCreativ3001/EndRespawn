import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask

plugins {
    java
    // Minecraft Server: https://github.com/sya-ri/minecraft-server-gradle-plugin
    id("dev.s7a.gradle.minecraft.server") version "2.1.0"
}

repositories {
    mavenCentral()
    maven { 
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${project.property("minecraft_version")}-${project.property("spigot_version")}")
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(
            "version" to project.version,
            "minecraft_major_version" to project.property("minecraft_major_version"),
        )
    }
}

task<LaunchMinecraftServerTask>("testPlugin") {
    dependsOn("build")

    doFirst {
        copy {
            from(buildDir.resolve("libs/${project.name}-${project.version}.jar"))
            into(buildDir.resolve("MinecraftServer/plugins/"))
        }
    }

    jarUrl.set(LaunchMinecraftServerTask.JarUrl.Paper(project.property("minecraft_version").toString()))
    agreeEula.set(true)
}