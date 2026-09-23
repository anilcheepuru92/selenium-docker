# Selenium Docker

A Maven-based Selenium and TestNG automation project packaged for execution in Docker. The repository contains browser tests for search and flight-booking workflows, along with Docker and Jenkins configuration for repeatable test execution in CI.

## What is included

- **Selenium WebDriver** test automation written in Java
- **TestNG** suites for search and flight-booking scenarios
- **Docker** image configuration for running tests against a Selenium Hub
- **Jenkins** pipeline for building the Maven artifacts, creating the image, and publishing it to Docker Hub
- TestNG HTML and XML reports under [`output/`](output/)

## Technology stack

- Java 8
- Maven
- Selenium Java 3.141.59
- TestNG 7.0.0
- Docker
- Jenkins

## Project structure

```text
.
├── Dockerfile
├── Jenkinsfile
├── flightTestNG.xml       # Flight-booking TestNG suite
├── searchTestNG.xml       # Search TestNG suite
├── healthcheck.sh         # Waits for Selenium Hub and starts TestNG
├── pom.xml
├── src/main/java          # Shared application and page-object code
├── src/test/java          # Selenium tests
└── output/                # Generated TestNG reports and assets
```

## Prerequisites

Install the following locally or on the build agent:

- JDK 8
- Maven 3.x
- Docker, if building or running the container
- A reachable Selenium Grid/Selenium Hub when running the Docker image

The project uses Java 8 source and target compatibility. Newer JDKs may require additional Maven or dependency configuration.

## Build the project

Compile the project and create the application and test JARs:

```bash
mvn clean package
```

The Maven build also copies runtime dependencies into `target/libs`, which is consumed by the Docker image.

To package without running tests:

```bash
mvn clean package -DskipTests
```

## Run the test suites locally

After compiling, run either TestNG suite through Maven or your preferred TestNG runner:

```bash
mvn test
```

The checked-in suite files can be used directly with TestNG:

```bash
java -cp "target/selenium-docker.jar:target/selenium-docker-tests.jar:target/libs/*" \
  -DHUB_HOST=<selenium-hub-host> \
  -DBROWSER=<browser> \
  org.testng.TestNG searchTestNG.xml
```

Available suites:

- `searchTestNG.xml` runs searches for `java`, `qa`, `webdriver`, `docker`, and `kubernetes`.
- `flightTestNG.xml` runs booking scenarios for one through four passengers with expected prices defined in the suite parameters.

## Build the Docker image

The Dockerfile expects the Maven artifacts and dependency directory to exist under `target/`:

```bash
mvn clean package -DskipTests
docker build -t selenium-docker .
```

The image is based on Java 8 Alpine and includes `curl` and `jq` so it can poll the Selenium Hub readiness endpoint before starting the tests.

## Run the Docker image

Provide the Selenium Hub host, browser, and TestNG suite through environment variables:

```bash
docker run --rm \
  -e HUB_HOST=<selenium-hub-host> \
  -e BROWSER=chrome \
  -e XMLFILE=searchTestNG.xml \
  selenium-docker
```

The container waits until the Hub reports that it is ready, then launches TestNG. The supported runtime variables are:

| Variable | Description | Example |
| --- | --- | --- |
| `HUB_HOST` | Hostname or address of the Selenium Hub | `selenium-hub` |
| `BROWSER` | Browser requested by the tests | `chrome` |
| `XMLFILE` | TestNG suite file to execute | `searchTestNG.xml` |

Make sure the container can resolve and reach `<HUB_HOST>:4444` on the Docker network.

## Jenkins pipeline

[`Jenkinsfile`](Jenkinsfile) defines three stages:

1. Build the Maven artifacts with `mvn clean package -DskipTests`.
2. Build the image as `anilcheepuru92/docker-selenium`.
3. Log in to Docker Hub using the Jenkins credential named `dockerhub` and push the `latest` tag.

Configure a Jenkins agent with Java, Maven, Docker, and the `dockerhub` username/password credential before using this pipeline. The checked-in pipeline uses Windows `bat` steps, so the agent must support that command style or the steps should be adapted for the target operating system.

## Test reports

TestNG report files are generated in the `output/` directory when the suites are run. Open [`output/index.html`](output/index.html) in a browser to inspect the report when the report has been generated locally or copied from a test run.
