import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("plugin.allopen") version "1.6.21"
}

group = "com.germani"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["testcontainersVersion"] = "1.17.3"

var versions = mapOf(
    "jslt" to "0.1.13",
    "reactive-feign" to "3.2.5",
    "mapstruct" to "1.5.2.Final"
)

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("com.schibsted.spt.data:jslt:${versions["jslt"]}")
    implementation("com.playtika.reactivefeign:feign-reactor-core:${versions["reactive-feign"]}")
    implementation("com.playtika.reactivefeign:feign-reactor-spring-configuration:${versions["reactive-feign"]}")
    //TODO: replace to cloud later
    implementation("com.playtika.reactivefeign:feign-reactor-webclient:${versions["reactive-feign"]}")
    implementation("org.mapstruct:mapstruct:${versions["mapstruct"]}")

    annotationProcessor("org.mapstruct:mapstruct-processor:${versions["mapstruct"]}")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:junit-jupiter")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

allOpen {
    annotations("org.springframework.data.mongodb.core.mapping.Document", "javax.persistence.MappedSuperclass", "javax.persistence.Embedabble")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
