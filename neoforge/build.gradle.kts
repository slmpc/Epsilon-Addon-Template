plugins {
    id("multiloader-loader")
    id("net.neoforged.moddev")
}

val neoforgeVersion = project.property("neoforge_version").toString()
val modId = project.property("mod_id").toString()
val epsilonNeoForgeJar = rootProject.file("epsilon_libs").listFiles()?.firstOrNull {
    it.name.startsWith("epsilon-neoforge-") && it.name.endsWith(".jar") && !it.name.endsWith("-sources.jar")
} ?: rootProject.file("epsilon_libs/missing.jar")

check(epsilonNeoForgeJar.exists()) {
    "Missing OpenEpsilon NeoForge jar in epsilon_libs/. Expected a file matching epsilon-neoforge-*.jar (excluding sources jars)."
}

neoForge {
    version = neoforgeVersion
    runs {
        configureEach {
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
            ideName = "NeoForge ${name.replaceFirstChar { it.uppercase() }} (${project.path})"
            logLevel = org.slf4j.event.Level.DEBUG
            systemProperty("terminal.jline", "true")
        }
        register("client") {
            client()
            gameDirectory = file("runs/client").also { it.mkdirs() }
        }
        register("data") {
            clientData()
            gameDirectory = file("runs/data").also { it.mkdirs() }
            programArguments.addAll("--mod", modId, "--all", "--output", file("src/generated/resources/").absolutePath, "--existing", file("src/main/resources/").absolutePath)
        }
        register("server") {
            server()
            gameDirectory = file("runs/server").also { it.mkdirs() }
        }
    }
    mods {
        register(modId) {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets.main.get().resources.srcDir("src/generated/resources")

dependencies {
    implementation(files(epsilonNeoForgeJar))
}

val loaderAttribute = Attribute.of("io.github.mcgradleconventions.loader", String::class.java)
listOf("apiElements", "runtimeElements", "sourcesElements").forEach { variant ->
    configurations.named(variant) {
        attributes {
            attribute(loaderAttribute, "neoforge")
        }
    }
}
sourceSets.configureEach {
    listOf(compileClasspathConfigurationName, runtimeClasspathConfigurationName, getTaskName(null, "jarJar")).forEach { variant ->
        configurations.named(variant) {
            attributes {
                attribute(loaderAttribute, "neoforge")
            }
        }
    }
}
