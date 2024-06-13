plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"

	id("org.sonarqube") version "4.0.0.2929"
	id("jacoco")
	id("checkstyle")
}

group = "com.upao.pe"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://repo.spring.io/milestone")
	}
	maven {
		url = uri("https://repo.spring.io/snapshot")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("javax.xml.bind:jaxb-api:2.3.0")
	implementation ("org.springframework.ai:spring-ai-ollama-spring-boot-starter:0.8.0-SNAPSHOT")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

sonar {
	properties {
		property("sonar.projectName", "app-web")
		property("sonar.projectKey", "secret-key")
		property("sonar.host.url", "http://localhost:9001")
		property("sonar.login", "sqa_83c6e761b531e309e98a7c70423d84695dbc7719");
	}
}

jacoco {
	toolVersion = "0.8.11"
	reportsDirectory = rootProject.file("reports/jacoco/jacocoRootReport")
}

tasks.withType<Pmd> {
	reports {
		xml.required = true
		html.required = true
	}
}

tasks.withType<Checkstyle> {
	reports {
		this.html.outputLocation = rootProject.file("reports/checkstyle/checkstyle.html")
	}
}


tasks.named("sonar") {
	dependsOn("jacocoTestReport")
}

tasks.named("jacocoTestReport") {
	dependsOn("test")
}