plugins {
    id("common-configuration")
    id("com.autonomousapps.dependency-analysis")
    kotlin("multiplatform")
    id("test-inputs-check")
}

kotlin {
    jvm()

    wasmJs()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                api(kotlinStdlib())
            }
            kotlin {
                srcDir("common/src")
            }
        }
        getByName("jvmTest") {
            dependencies {
                implementation(libs.junit.jupiter.api)
                runtimeOnly(libs.junit.jupiter.engine)
                runtimeOnly(libs.junit.platform.launcher)
                implementation(kotlinTest("junit5"))
            }
            kotlin {
                srcDir("jvm/test")
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
