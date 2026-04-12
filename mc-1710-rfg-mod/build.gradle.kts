plugins {
    id("java-library")
    id("com.gtnewhorizons.retrofuturagradle") version "1.4.0"
}

group = "com.example.examplemod"
version = "1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        vendor.set(org.gradle.jvm.toolchain.JvmVendorSpec.AZUL)
    }
}

minecraft {
    mcVersion.set("1.7.10")
    username.set("Developer")
}

tasks.processResources.configure {
    val projVersion = project.version.toString()
    inputs.property("version", projVersion)
    inputs.property("mcversion", "1.7.10")

    filesMatching("mcmod.info") {
        expand(mapOf("version" to projVersion, "mcversion" to "1.7.10"))
    }
}
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}
