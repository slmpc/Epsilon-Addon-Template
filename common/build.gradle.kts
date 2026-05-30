plugins {
    id("multiloader-common")
    id("net.neoforged.moddev")
}

val epsilonCommonJar = rootProject.file("epsilon_libs").listFiles()?.firstOrNull {
    it.name.startsWith("epsilon-common-") && it.name.endsWith(".jar") && !it.name.endsWith("-sources.jar")
} ?: rootProject.file("epsilon_libs/missing.jar")

check(epsilonCommonJar.exists()) {
    "Missing Epsilon common jar in epsilon_libs/. Expected a file matching epsilon-common-*.jar (excluding sources jars)."
}

neoForge {
    neoFormVersion = project.property("neo_form_version").toString()
}

dependencies {
    compileOnly(files(epsilonCommonJar))
}

configurations {
    create("commonJava") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    create("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
}

artifacts {
    add("commonJava", sourceSets.main.get().java.sourceDirectories.singleFile)
    add("commonResources", sourceSets.main.get().resources.sourceDirectories.singleFile)
}

val loaderAttribute = Attribute.of("io.github.mcgradleconventions.loader", String::class.java)
listOf("apiElements", "runtimeElements", "sourcesElements").forEach { variant ->
    configurations.named(variant) {
        attributes {
            attribute(loaderAttribute, "common")
        }
    }
}
sourceSets.configureEach {
    listOf(compileClasspathConfigurationName, runtimeClasspathConfigurationName).forEach { variant ->
        configurations.named(variant) {
            attributes {
                attribute(loaderAttribute, "common")
            }
        }
    }
}
