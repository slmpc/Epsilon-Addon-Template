plugins {
    id("multiloader-loader")
    id("net.fabricmc.fabric-loom")
}

val minecraftVersion = project.property("minecraft_version").toString()
val fabricLoaderVersion = project.property("fabric_loader_version").toString()
val fabricVersion = project.property("fabric_version").toString()
val epsilonFabricJar = rootProject.file("epsilon_libs/${project.property("epsilon_fabric_jar")}")

check(epsilonFabricJar.exists()) {
    "Missing OpenEpsilon Fabric jar: ${epsilonFabricJar.absolutePath}. Copy it into epsilon_libs/ or update epsilon_fabric_jar in gradle.properties."
}

dependencies {
    "minecraft"("com.mojang:minecraft:${minecraftVersion}")
    implementation("net.fabricmc:fabric-loader:${fabricLoaderVersion}")
    implementation("net.fabricmc.fabric-api:fabric-api:${fabricVersion}")
    implementation(files(epsilonFabricJar))
    compileOnly(group = "com.google.code.findbugs", name = "jsr305", version = "3.0.2")
}

loom {
    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            runDir("runs/client")
        }
        named("server") {
            server()
            configName = "Fabric Server"
            ideConfigGenerated(true)
            runDir("runs/server")
        }
    }
}

val loaderAttribute = Attribute.of("io.github.mcgradleconventions.loader", String::class.java)
listOf("apiElements", "runtimeElements", "sourcesElements", "includeInternal", "modCompileClasspath").forEach { variant ->
    configurations.named(variant) {
        attributes {
            attribute(loaderAttribute, "fabric")
        }
    }
}
sourceSets.configureEach {
    listOf(compileClasspathConfigurationName, runtimeClasspathConfigurationName).forEach { variant ->
        configurations.named(variant) {
            attributes {
                attribute(loaderAttribute, "fabric")
            }
        }
    }
}
