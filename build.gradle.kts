defaultTasks("mvgCorrector", "test", "publish", "mvgTagger")

plugins {
    `java-library`
    `maven-publish`
    id("org.modelingvalue.gradle.mvgplugin") version "0.4.26"
}
dependencies {
    
    
    //testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    //testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.modelingvalue:dclare:1.4.0-BRANCHED")
    implementation("org.modelingvalue:immutable-collections:1.4.0-BRANCHED")
    implementation("org.modelingvalue:mvgjson:1.1.6-BRANCHED")
}
publishing {
    publications {
        create<MavenPublication>("service-brainstorm") {
            from(components["java"])
        }
    }
}
