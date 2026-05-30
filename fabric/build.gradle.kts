plugins {
    id("multiloader-loader")
    id("net.fabricmc.fabric-loom")
}

val minecraftVersion = project.property("minecraft_version").toString()
val fabricLoaderVersion = project.property("fabric_loader_version").toString()
val fabricVersion = project.property("fabric_version").toString()
val epsilonFabricJar = rootProject.file("epsilon_libs").listFiles()?.firstOrNull {
    it.name.startsWith("epsilon-fabric-") && it.name.endsWith(".jar") && !it.name.endsWith("-sources.jar")
} ?: rootProject.file("epsilon_libs/missing.jar")

check(epsilonFabricJar.exists()) {
    "Missing OpenEpsilon Fabric jar in epsilon_libs/. Expected a file matching epsilon-fabric-*.jar (excluding sources jars)."
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
