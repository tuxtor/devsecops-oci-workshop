# DevSec(minus Ops) with Java - Workshop Tutorial

> A comprehensive hands-on workshop for implementing DevSecOps practices with Java, Quarkus, and cloud-native technologies

[![Open in GitHub Codespaces](https://img.shields.io/badge/Open%20in-Codespaces-181717?logo=github-codespaces&logoColor=white)](https://github.com/codespaces/new?repo=tuxtor/devsecops-oci-workshop&ref=main)

[![GitHub Actions](https://github.com/tuxtor/devsecops-oci-workshop/actions/workflows/full-build.yml/badge.svg)](https://github.com/tuxtor/devsecops-oci-workshop/actions/workflows/full-build.yml)


## Commits guide

| # | SHA | Message |
|---|-----|---------|
| 1 | [30dbf41](https://github.com/tuxtor/devsecops-oci-workshop/commit/30dbf41d55b273fce6498ca87ed0271a1bb75d2e) | Adding github codespace definition (\*initial commit\*) |
| 2 | [7b182ba](https://github.com/tuxtor/devsecops-oci-workshop/commit/7b182ba7ec5122a3e17f4831da71a70f0387f901) | Adding github codespace definition |
| 3 | [b64377c](https://github.com/tuxtor/devsecops-oci-workshop/commit/b64377c04ff41cdc3bec6eb2a195cf46af79457a) | Adding quarkus skeleton |
| 4 | [b3848b0](https://github.com/tuxtor/devsecops-oci-workshop/commit/b3848b0ee3305f570a36804fca494a70a4125221) | Integration testing sample |
| 5 | [3a1bf03](https://github.com/tuxtor/devsecops-oci-workshop/commit/3a1bf03f4080030ebfdf62dad5741f5968d88cb6) | Downgrading to Java 21 |
| 6 | [872235d](https://github.com/tuxtor/devsecops-oci-workshop/commit/872235d88fd28fc3f6946cd4fd00d6c423b16f38) | Spotbugs detection demo |
| 7 | [d959814](https://github.com/tuxtor/devsecops-oci-workshop/commit/d9598141f88c8f2ddc479b234f486f8fb6f292aa) | Making spotbugs happy |
| 8 | [ef407fe](https://github.com/tuxtor/devsecops-oci-workshop/commit/ef407fe2828252479b2a196a17652de94eaf6567) | Adding IP resource for docker testing |
| 9 | [1258360](https://github.com/tuxtor/devsecops-oci-workshop/commit/1258360a548e71a205e552423d1313f91fe39907) | Adding gitignore to hide sensitive data |
| 10 | [f4800e7](https://github.com/tuxtor/devsecops-oci-workshop/commit/f4800e757c619bf956ce7dede9687328feb63311) | Adding basic java action builder |
| 11 | [23f7471](https://github.com/tuxtor/devsecops-oci-workshop/commit/23f747128dbce54649233ce1b0899ffe8fcdeabb) | Adding Eclipse JKube support |
| 12 | [2b4a793](https://github.com/tuxtor/devsecops-oci-workshop/commit/2b4a79354c29c92c43e294f26fa55f5fd6acf4f9) | Adding shift-left analysis |

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Step 1: Fork the Repository](#step-1-fork-the-repository)
- [Step 2: Let's Get Started!](#step-2-lets-get-started)
- [Step 3: Creating Quarkus Application Skeleton](#step-3-creating-quarkus-application-skeleton)
- [Step 4: Adding Integration Testing](#step-4-adding-integration-testing)
- [Step 5: Implementing Static Code Analysis with SpotBugs](#step-5-implementing-static-code-analysis-with-spotbugs)
- [Step 6: Fixing Code Quality Issues](#step-6-fixing-code-quality-issues)
- [Step 7: Adding IP Resource for Container Testing](#step-7-adding-ip-resource-for-container-testing)
- [Step 8: Setting up CI/CD with GitHub Actions](#step-8-setting-up-cicd-with-github-actions)
- [Step 9: Enabling Kubernetes Deployment with Eclipse JKube (Only for local testing)](#step-9-enabling-kubernetes-deployment-with-eclipse-jkube-only-for-local-testing)
- [Step 10: Implementing Shift-Left Security with Trivy](#step-10-implementing-shift-left-security-with-trivy)
- [Conclusion](#conclusion)

---

## Prerequisites

- GitHub account
- Basic knowledge of Java, Maven, and Docker
- Understanding of REST APIs
- Familiarity with Git

---

## Step 1: Fork the Repository

That's it, just fork it to your own GitHub account by clicking the "Fork" button at the top right of the repository page.

## Step 2: Let's Get Started!

**You can clone or open this project directly in GitHub Codespaces**

Then, inside your repository, navigate to the commit `7b182ba`

```bash
git reset --hard 7b182ba
```
You can always reset the repository with 

```bash
git reset --hard <commit-sha>
```

Install quarkus CLI if you don't have it yet:

```bash
curl -Ls https://sh.quarkus.io | bash
```

You can install it via SDKMAN as well (SDKMAN is pre-installed in GitHub Codespaces):

```bash
sdk install quarkus
```

## Step 3: Creating Quarkus Application Skeleton

* **Goal**: Bootstrap a complete Quarkus REST application with health checks and monitoring
* **Commit Reference**: [b64377c](https://github.com/tuxtor/devsecops-oci-workshop/commit/b64377c04ff41cdc3bec6eb2a195cf46af79457a)

### Generate the Quarkus project

You can generate this using [code.quarkus.io](https://code.quarkus.io) or the Quarkus CLI.  

```bash
quarkus create app com.vorozco:quarkus-cloud-native-workload:1.0.0-SNAPSHOT \
  --extensions=quarkus-rest,quarkus-arc,quarkus-rest-jackson,quarkus-smallrye-health,quarkus-micrometer-registry-prometheus,junit5
```

The key files are:

#### Project structure

```
quarkus-cloud-native-workload/
├── .dockerignore
├── .gitignore
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── docker/
│   │   │   ├── Dockerfile.jvm
│   │   │   ├── Dockerfile.legacy-jar
│   │   │   ├── Dockerfile.native
│   │   │   └── Dockerfile.native-micro
│   │   ├── java/com/vorozco/
│   │   │   ├── GreetingResource.java
│   │   │   └── MyLivenessCheck.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/vorozco/
│       ├── GreetingResourceIT.java
│       └── GreetingResourceTest.java
```

#### Key dependency in `pom.xml`

```xml
<properties>
    <maven.compiler.release>21</maven.compiler.release>
    <quarkus.platform.version>3.30.3</quarkus.platform.version>
</properties>

<dependencies>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-smallrye-health</artifactId>
    </dependency>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-rest</artifactId>
    </dependency>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-rest-jackson</artifactId>
    </dependency>
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-micrometer-registry-prometheus</artifactId>
    </dependency>
</dependencies>
```

#### Create the REST endpoint

`src/main/java/com/vorozco/GreetingResource.java`:

```java
package com.vorozco;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
```

#### Create health check

`src/main/java/com/vorozco/MyLivenessCheck.java`:

```java
package com.vorozco;

import org.eclipse.microprofile.health. HealthCheck;
import org.eclipse.microprofile.health. HealthCheckResponse;
import org.eclipse.microprofile.health. Liveness;

@Liveness
public class MyLivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("alive");
    }

}
```

#### Create unit test

`src/test/java/com/vorozco/GreetingResourceTest.java`:

```java
package com.vorozco;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured. RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

}
```

### Test the application

```bash
cd quarkus-cloud-native-workload
./mvnw quarkus:dev
```

Access the dev UI at `http://localhost:8080/q/dev/` (on localhost)

Or access the endpoints directly:

```bash
curl http://localhost:8080/hello
# Returns: Hello from Quarkus REST
curl http://localhost:8080/q/health/live
# Returns: {"status":"UP","checks":[{"name":"alive","status":"UP"}]}
curl http://localhost:8080/q/metrics
# Returns: Prometheus metrics
```

**What you've built**:
- RESTful web service with JAX-RS
- Health checks for Kubernetes
- Prometheus metrics integration
- Multiple Docker build options (JVM, native, micro)
- Complete test infrastructure

**Commit Reference**: [b64377c](https://github.com/tuxtor/devsecops-oci-workshop/commit/b64377c04ff41cdc3bec6eb2a195cf46af79457a)

---

## Step 4: Adding Integration Testing

* **Goal**:  Implement a computational endpoint with comprehensive testing
* **Commit Reference**: [b3848b0](https://github.com/tuxtor/devsecops-oci-workshop/commit/b3848b0ee3305f570a36804fca494a70a4125221)

### Create the Fibonacci REST endpoint

`src/main/java/com/vorozco/FibonacciResource.java`:

```java
package com.vorozco;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/fibonacci")
public class FibonacciResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Long> getFibonacci(@QueryParam("fibo") Integer fibo) {
        int iterations = (fibo != null && fibo > 0) ? fibo : 5;
        List<Long> sequence = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            sequence.add(fibonacci(i));
        }
        return sequence;
    }

    private long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
```

### Create integration test

`src/test/java/com/vorozco/FibonacciResourceTest.java`:

```java
package com.vorozco;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest. Matchers.*;

@QuarkusTest
public class FibonacciResourceTest {

    @Test
    public void testFibonacciEndpointFibo2() {
        given()
            .queryParam("fibo", 2)
            .when().get("/fibonacci")
            .then()
            .statusCode(200)
            .body("size()", is(2))
            .body("[0]", is(0))
            .body("[1]", is(1));
    }
}
```

### Test it

```bash
./mvnw test
curl "http://localhost:8080/fibonacci?fibo=7"
# Returns: [0,1,1,2,3,5,8]
```

**What this demonstrates**:
- Query parameter handling
- JSON serialization
- REST Assured testing framework
- Integration testing patterns

**Commit Reference**: [b3848b0](https://github.com/tuxtor/devsecops-oci-workshop/commit/b3848b0ee3305f570a36804fca494a70a4125221)

---

## Step 5: Implementing Static Code Analysis with SpotBugs

* **Goal**:  Add automated code quality and security checks
* **Commit Reference**: [872235d](https://github.com/tuxtor/devsecops-oci-workshop/commit/872235d88fd28fc3f6946cd4fd00d6c423b16f38)

### Add SpotBugs plugin to pom.xml

Add this plugin configuration to the `<build><plugins>` section:

```xml
<plugin>
    <groupId>com.github.spotbugs</groupId>
    <artifactId>spotbugs-maven-plugin</artifactId>
    <version>4.9.8.2</version>
    <dependencies>
        <dependency>
            <groupId>com.github.spotbugs</groupId>
            <artifactId>spotbugs</artifactId>
            <version>4.9.8</version>
        </dependency>
    </dependencies>
</plugin>
```

### Introduce a code smell for demonstration

Modify `src/main/java/com/vorozco/GreetingResource.java`:

```java
@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        var dummyVar = doSpotBugsDemo();  // Unused variable - SpotBugs will detect
        return "Hello from Quarkus REST";
    }

    private String doSpotBugsDemo() {
        var dummyVar = "This is not an error";  // Dead store
        return "SpotBugs demo";
    }
}
```

### Run SpotBugs analysis

```bash
./mvnw spotbugs:check
```

**Expected output**: SpotBugs will report:
- Dead store to local variable (dummyVar)
- Unused method result

**What SpotBugs detects**:
- Null pointer dereferences
- Infinite loops
- Synchronization issues
- Vulnerable code patterns
- Performance problems
- Bad practices

**Commit Reference**: [872235d](https://github.com/tuxtor/devsecops-oci-workshop/commit/872235d88fd28fc3f6946cd4fd00d6c423b16f38)

---

## Step 6: Fixing Code Quality Issues

* **Goal**: Resolve SpotBugs warnings to achieve clean code
* **Commit Reference**: [d959814](https://github.com/tuxtor/devsecops-oci-workshop/commit/d9598141f88c8f2ddc479b234f486f8fb6f292aa)

### Remove the problematic code

Update `src/main/java/com/vorozco/GreetingResource.java`:

```java
@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
```

### Verify the fix

```bash
./mvnw spotbugs:check
# Should pass with no warnings
```

**Best practice**: Fix security and quality issues immediately in the development phase (shift-left approach).

**Commit Reference**: [d959814](https://github.com/tuxtor/devsecops-oci-workshop/commit/d9598141f88c8f2ddc479b234f486f8fb6f292aa)

---

## Step 7: Adding IP Resource for Container Testing

* **Goal**: Create an endpoint to verify container networking
* **Commit Reference**: [ef407fe](https://github.com/tuxtor/devsecops-oci-workshop/commit/ef407fe2828252479b2a196a17652de94eaf6567)
### Create the IP Resource

`src/main/java/com/vorozco/IpResource.java`:

```java
package com.vorozco;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/ip")
@Produces(MediaType.TEXT_PLAIN)
public class IpResource {

    @GET
    public String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "Unknown";
        }
    }
}
```

### Create comprehensive test

`src/test/java/com/vorozco/IpResourceTest.java`:

```java
package com.vorozco;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;

import io.quarkus.test.junit.QuarkusTest;
import org. junit.jupiter.api.Test;

@QuarkusTest
public class IpResourceTest {

    @Test
    public void testServerIpEndpoint() {
        // Regex for IPv4 and IPv6
        String ipv4Pattern = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$";
        String ipv6Pattern = "^([0-9a-fA-F]{0,4}:){2,7}[0-9a-fA-F]{0,4}$";
        given()
            .when()
            .get("/ip")
            .then()
            .statusCode(200)
            .body(
                anyOf(
                    matchesPattern(ipv4Pattern),
                    matchesPattern(ipv6Pattern),
                    is("Unknown")
                )
            );
    }
}
```

### Build the container

```bash
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-cloud-native-workload-jvm .
```

### Test in different environments

```bash
# Local development
./mvnw quarkus:dev
curl http://localhost:8080/ip

# Docker container (after building)
docker run -p 8080:8080 quarkus/quarkus-cloud-native-workload-jvm
curl http://localhost:8080/ip
```

**Use case**: Verify container networking, troubleshoot Kubernetes pods, demonstrate microservice communication.

**Commit Reference**: [ef407fe](https://github.com/tuxtor/devsecops-oci-workshop/commit/ef407fe2828252479b2a196a17652de94eaf6567)

---

## Step 8: Setting up CI/CD with GitHub Actions

* **Goal**: Automate testing, code quality checks, and builds
* **Commit Reference**: [f4800e7](https://github.com/tuxtor/devsecops-oci-workshop/commit/f4800e757c619bf956ce7dede9687328feb63311)

### Create GitHub Actions workflow

Create `.github/workflows/full-build.yml`:

```yaml
name: full-build. yml
on:
  push:
    branches: 
      - main
  pull_request:
    branches: 
      - main

permissions:
  contents: read

jobs:
  quarkus: 
    name: "Quarkus"
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v4
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          distribution: "temurin"
          java-version: "21"
          cache: "maven"
      - name: Run Maven tests
        run: ./mvnw verify spotbugs:check
        working-directory: quarkus-cloud-native-workload
```

### What this workflow does:

1. **Triggers**:  Runs on push to main and pull requests
2. **Checkout**: Gets the latest code
3. **Java Setup**: Installs JDK 21 with Maven caching
4. **Verification**: Runs unit tests, integration tests, and SpotBugs

### Test the workflow

```bash
git add .github/workflows/full-build.yml
git commit -m "Add CI/CD pipeline"
git push origin main
```

Visit your repository's **Actions** tab to see the workflow run.

**CI/CD benefits**:
- Automated quality gates
- Consistent build environment
- Fast feedback loop
- Prevention of defects reaching production

**Commit Reference**: [f4800e7](https://github.com/tuxtor/devsecops-oci-workshop/commit/f4800e757c619bf956ce7dede9687328feb63311)

---

## Step 9: Enabling Kubernetes Deployment with Eclipse JKube (Only for local testing)

* **Goal**:  Simplify container building and Kubernetes deployment
* **Commit Reference**: [23f7471](https://github.com/tuxtor/devsecops-oci-workshop/commit/23f747128dbce54649233ce1b0899ffe8fcdeabb)

### Add JKube plugin to pom.xml

Add to `<properties>`:

```xml
<jkube.generator.name>docker. io/tuxtor/quarkus-cloud-native-workload:%l</jkube.generator.name>
```

Add to `<build><plugins>`:

```xml
<plugin>
    <groupId>org.eclipse.jkube</groupId>
    <artifactId>kubernetes-maven-plugin</artifactId>
    <version>1.18.2</version>
</plugin>
```

### Build container image

```bash
./mvnw k8s:build
```

This creates a Docker image with the tag specified in `jkube.generator.name`.

### Generate Kubernetes manifests

```bash
./mvnw k8s:resource
```

### Create a k3d cluster (Really optional)

```bash
k3d cluster create k3d-cluster
# k3d cluster delete k3d-cluster
```

### Deploy to Kubernetes

```bash
./mvnw k8s:apply
```

### JKube features:

- **Auto-detection**: Analyzes your project and creates appropriate Kubernetes resources
- **Zero-config**: Works out of the box with sensible defaults
- **Customizable**: Override via XML configuration or resource fragments
- **Multi-platform**:  Supports Kubernetes and OpenShift

**Example generated resources**:
- Deployment
- Service

**Commit Reference**: [23f7471](https://github.com/tuxtor/devsecops-oci-workshop/commit/23f747128dbce54649233ce1b0899ffe8fcdeabb)

---

## Step 10: Implementing Shift-Left Security with Trivy

* **Goal**:  Scan container images for vulnerabilities in the CI/CD pipeline
* **Commit Reference**: [2b4a793](https://github.com/tuxtor/devsecops-oci-workshop/commit/2b4a79354c29c92c43e294f26fa55f5fd6acf4f9)

### Update GitHub Actions workflow

Modify `.github/workflows/full-build.yml` to add Docker build and Trivy scan:

```yaml
name: full-build.yml
on:
  push: 
    branches:
      - main
  pull_request:
    branches:
      - main

permissions: 
  contents: read

jobs: 
  quarkus:
    name: "Quarkus"
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v4
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          distribution: "temurin"
          java-version: "21"
          cache: "maven"
      - name: Run Maven tests
        run:  ./mvnw verify spotbugs:check
        working-directory:  quarkus-cloud-native-workload
      - name: Docker build
        run: ./mvnw k8s:build
        working-directory: quarkus-cloud-native-workload
      - name: Run Trivy security scan (Docker)
        uses: aquasecurity/trivy-action@0.33.1
        with:
          scan-type: "image"
          image-ref: docker.io/tuxtor/quarkus-cloud-native-workload:latest
          format: "table"
          exit-code: "0"
          severity: 'CRITICAL,HIGH'
```

### What Trivy scans for:

- **OS packages**: Vulnerabilities in Alpine, Debian, Ubuntu, etc.
- **Application dependencies**: Java, Node.js, Python, Ruby, etc.
- **Misconfigurations**: Kubernetes manifests, Dockerfiles, Terraform
- **Secrets**:  Hardcoded passwords, API keys, tokens
- **License compliance**:  Dependency licenses

### Understanding the scan configuration:

- `scan-type:  "image"`: Scan Docker container image
- `severity: 'CRITICAL,HIGH'`: Only report serious vulnerabilities
- `exit-code: "0"`: Don't fail the build (change to "1" for strict enforcement)
- `format: "table"`: Human-readable output

### Example Trivy output:

```
quarkus-cloud-native-workload: latest (alpine 3.18.0)
===========================================================
Total: 2 (HIGH: 2, CRITICAL: 0)

┌─────────────┬────────────────┬──────────┬───────────────────┬───────────────┬────────────────────────────────────┐
│   Library   │ Vulnerability  │ Severity │ Installed Version │ Fixed Version │              Title                 │
├─────────────┼────────────────┼──────────┼───────────────────┼───────────────┼────────────────────────────────────┤
│ openssl     │ CVE-2023-12345 │ HIGH     │ 3.0.8-r0          │ 3.0.9-r0      │ OpenSSL vulnerability              │
│ libcrypto   │ CVE-2023-67890 │ HIGH     │ 3.0.8-r0          │ 3.0.9-r0      │ Cryptographic weakness             │
└─────────────┴────────────────┴──────────┴───────────────────┴───────────────┴────────────────────────────────────┘
```

### Shift-left security benefits:

1. **Early detection**: Find vulnerabilities before deployment
2. **Fast feedback**: Developers get immediate results
3. **Cost savings**:  Cheaper to fix in development than production
4. **Compliance**:  Document security posture
5. **Prevention**: Block vulnerable images from reaching production

### Advanced configuration (optional):

For strict security enforcement, change `exit-code` to `"1"`:

```yaml
- name: Run Trivy security scan (Docker)
  uses: aquasecurity/trivy-action@0.33.1
  with:
    scan-type: "image"
    image-ref:  docker.io/tuxtor/quarkus-cloud-native-workload:latest
    format:  "sarif"
    output: "trivy-results.sarif"
    exit-code: "1"  # Fail the build on vulnerabilities
    severity: 'CRITICAL,HIGH'

- name: Upload Trivy results to GitHub Security
  uses: github/codeql-action/upload-sarif@v2
  if: always()
  with:
    sarif_file: "trivy-results.sarif"
```

**Commit Reference**: [2b4a793](https://github.com/tuxtor/devsecops-oci-workshop/commit/2b4a79354c29c92c43e294f26fa55f5fd6acf4f9)

---

## Conclusion

### What You've Accomplished

Congratulations!  You've built a complete DevSecOps pipeline incorporating:

* **Development Environment**: Cloud-based with GitHub Codespaces  
* **Modern Framework**: Quarkus for cloud-native Java applications  
* **Comprehensive Testing**: Unit and integration tests with REST Assured  
* **Code Quality**: SpotBugs static analysis  
* **CI/CD Automation**: GitHub Actions pipeline  
* **Containerization**: Docker with Eclipse JKube  
* **Security Scanning**: Trivy for vulnerability detection  
* **Cloud Native**: Kubernetes-ready deployments

### The DevSecOps Pipeline Flow

```
┌─────────────────┐
│  Code Commit    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  GitHub Actions │
│   Triggered     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Build & Test   │──► Unit Tests
│                 │──► Integration Tests
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  SpotBugs Scan  │──► Code Quality
│                 │──► Security Issues
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Docker Build   │──► JKube Build
│                 │──► Container Image
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   Trivy Scan    │──► Vulnerability Check
│                 │──► Compliance Check
└─────────────────┘
```

### Key DevSecOps Principles Applied

1. **Shift-Left Security**: Security checks integrated early in development
2. **Automation First**: Every step is automated and repeatable
3. **Continuous Feedback**: Developers get immediate results
4. **Infrastructure as Code**: Everything is versioned and reproducible
5. **Defense in Depth**: Multiple security layers (static analysis, container scanning)

### Next Steps

The Ops in DevSecOps :)

### Resources

- [Quarkus Documentation](https://quarkus.io/guides/)
- [Eclipse JKube](https://www.eclipse.org/jkube/)
- [Trivy Documentation](https://aquasecurity.github.io/trivy/)
- [SpotBugs Manual](https://spotbugs.readthedocs.io/)
- [GitHub Actions](https://docs.github.com/en/actions)

### Repository

Full source code:  [https://github.com/tuxtor/devsecops-oci-workshop](https://github.com/tuxtor/devsecops-oci-workshop)
