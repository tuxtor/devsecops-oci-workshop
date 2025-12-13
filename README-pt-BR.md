# DevSec(minus Ops) com Java - Tutorial do Workshop

> Um workshop prático e completo para implementar práticas de DevSecOps com Java, Quarkus e tecnologias cloud-native

[![Open in GitHub Codespaces](https://img.shields.io/badge/Open%20in-Codespaces-181717?logo=github-codespaces&logoColor=white)](https://github.com/codespaces/new?repo=tuxtor/devsecops-oci-workshop&ref=main)

[![GitHub Actions](https://github.com/tuxtor/devsecops-oci-workshop/actions/workflows/full-build.yml/badge.svg)](https://github.com/tuxtor/devsecops-oci-workshop/actions/workflows/full-build.yml)

## Guia de commits

| # | SHA | Mensagem |
|---|-----|---------|
| 1 | [30dbf41](https://github.com/tuxtor/devsecops-oci-workshop/commit/30dbf41d55b273fce6498ca87ed0271a1bb75d2e) | Adicionando definição do GitHub Codespaces *(commit inicial)* |
| 2 | [7b182ba](https://github.com/tuxtor/devsecops-oci-workshop/commit/7b182ba7ec5122a3e17f4831da71a70f0387f901) | Adicionando definição do GitHub Codespaces |
| 3 | [b64377c](https://github.com/tuxtor/devsecops-oci-workshop/commit/b64377c04ff41cdc3bec6eb2a195cf46af79457a) | Adicionando esqueleto Quarkus |
| 4 | [b3848b0](https://github.com/tuxtor/devsecops-oci-workshop/commit/b3848b0ee3305f570a36804fca494a70a4125221) | Exemplo de testes de integração |
| 5 | [3a1bf03](https://github.com/tuxtor/devsecops-oci-workshop/commit/3a1bf03f4080030ebfdf62dad5741f5968d88cb6) | Rebaixando para Java 21 |
| 6 | [872235d](https://github.com/tuxtor/devsecops-oci-workshop/commit/872235d88fd28fc3f6946cd4fd00d6c423b16f38) | Demonstração de detecção SpotBugs |
| 7 | [d959814](https://github.com/tuxtor/devsecops-oci-workshop/commit/d9598141f88c8f2ddc479b234f486f8fb6f292aa) | Ajustando SpotBugs |
| 8 | [ef407fe](https://github.com/tuxtor/devsecops-oci-workshop/commit/ef407fe2828252479b2a196a17652de94eaf6567) | Adicionando recurso de IP para testes em container |
| 9 | [1258360](https://github.com/tuxtor/devsecops-oci-workshop/commit/1258360a548e71a205e552423d1313f91fe39907) | Adicionando .gitignore para ocultar dados sensíveis |
| 10 | [f4800e7](https://github.com/tuxtor/devsecops-oci-workshop/commit/f4800e757c619bf956ce7dede9687328feb63311) | Adicionando builder Java básico para Actions |
| 11 | [23f7471](https://github.com/tuxtor/devsecops-oci-workshop/commit/23f747128dbce54649233ce1b0899ffe8fcdeabb) | Adicionando suporte ao Eclipse JKube |
| 12 | [2b4a793](https://github.com/tuxtor/devsecops-oci-workshop/commit/2b4a79354c29c92c43e294f26fa55f5fd6acf4f9) | Adicionando análise shift-left |

## Sumário

- [Pré-requisitos](#pré-requisitos)
- [Passo 1: Fork do Repositório](#passo-1-fork-do-repositório)
- [Passo 2: Vamos Começar!](#passo-2-vamos-começar)
- [Passo 3: Criando o Esqueleto da Aplicação Quarkus](#passo-3-criando-o-esqueleto-da-aplicação-quarkus)
- [Passo 4: Adicionando Testes de Integração](#passo-4-adicionando-testes-de-integração)
- [Passo 5: Implementando Análise Estática com SpotBugs](#passo-5-implementando-análise-estática-com-spotbugs)
- [Passo 6: Corrigindo Problemas de Qualidade de Código](#passo-6-corrigindo-problemas-de-qualidade-de-código)
- [Passo 7: Adicionando Recurso de IP para Testes em Container](#passo-7-adicionando-recurso-de-ip-para-testes-em-container)
- [Passo 8: Configurando CI/CD com GitHub Actions](#passo-8-configurando-cicd-com-github-actions)
- [Passo 9: Habilitando Deploy no Kubernetes com Eclipse JKube (Apenas para testes locais)](#passo-9-habilitando-deploy-no-kubernetes-com-eclipse-jkube-apenas-para-testes-locais)
- [Passo 10: Implementando Segurança Shift-Left com Trivy](#passo-10-implementando-segurança-shift-left-com-trivy)
- [Conclusão](#conclusão)

---

## Pré-requisitos

- Conta no GitHub
- Conhecimentos básicos de Java, Maven e Docker
- Entendimento de APIs REST
- Familiaridade com Git

---

## Passo 1: Fork do Repositório

Basta fazer o fork para sua conta do GitHub clicando em "Fork" no canto superior direito da página do repositório.

## Passo 2: Vamos Começar!

**Você pode clonar ou abrir este projeto diretamente no GitHub Codespaces**

Dentro do seu repositório, navegue até o commit `7b182ba`

```bash
git reset --hard 7b182ba
```

Você pode sempre resetar o repositório com

```bash
git reset --hard <commit-sha>
```

Instale o Quarkus CLI se ainda não o tiver:

```bash
curl -Ls https://sh.quarkus.io | bash
```

Também é possível instalar via SDKMAN (o SDKMAN vem pré-instalado no GitHub Codespaces):

```bash
sdk install quarkus
```

## Passo 3: Criando o Esqueleto da Aplicação Quarkus

* **Objetivo**: _Bootstrap_ de uma aplicação Quarkus REST com health checks e monitoramento
* **Referência de Commit**: [b64377c](https://github.com/tuxtor/devsecops-oci-workshop/commit/b64377c04ff41cdc3bec6eb2a195cf46af79457a)

### Gerar o projeto Quarkus

Você pode gerar pelo [code.quarkus.io](https://code.quarkus.io) ou pelo Quarkus CLI.

```bash
quarkus create app com.vorozco:quarkus-cloud-native-workload:1.0.0-SNAPSHOT \
  --extensions=quarkus-rest,quarkus-arc,quarkus-rest-jackson,quarkus-smallrye-health,quarkus-micrometer-registry-prometheus,junit5
```

Os arquivos-chave são:

#### Estrutura do projeto

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

#### Dependência chave no `pom.xml`

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

#### Criar o endpoint REST

`src/main/java/com/vorozco/GreetingResource.java`:

```text
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

#### Criar health check

`src/main/java/com/vorozco/MyLivenessCheck.java`:

```text
package com.vorozco;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class MyLivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("alive");
    }

}
```

#### Criar teste unitário

`src/test/java/com/vorozco/GreetingResourceTest.java`:

```text
package com.vorozco;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
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

### Testar a aplicação

```bash
cd quarkus-cloud-native-workload
./mvnw quarkus:dev
```

Acesse a Dev UI em `http://localhost:8080/q/dev/` (no localhost)

Ou acesse os endpoints diretamente:

```bash
curl http://localhost:8080/hello
# Retorna: Hello from Quarkus REST
curl http://localhost:8080/q/health/live
# Retorna: {"status":"UP","checks":[{"name":"alive","status":"UP"}]}
curl http://localhost:8080/q/metrics
# Retorna: métricas em formato Prometheus
```

**O que você construiu**:
- Serviço web REST com JAX-RS
- Health checks para Kubernetes
- Integração com métricas Prometheus
- Várias opções de _build_ Docker (JVM, native, micro)
- Infraestrutura de testes completa

**Referência de Commit**: [b64377c](https://github.com/tuxtor/devsecops-oci-workshop/commit/b64377c04ff41cdc3bec6eb2a195cf46af79457a)

---

## Passo 4: Adicionando Testes de Integração

* **Objetivo**: Implementar um endpoint computacional com testes abrangentes
* **Referência de Commit**: [b3848b0](https://github.com/tuxtor/devsecops-oci-workshop/commit/b3848b0ee3305f570a36804fca494a70a4125221)

### Criar o endpoint Fibonacci REST

`src/main/java/com/vorozco/FibonacciResource.java`:

```text
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
        // Exemplo: se quiser usar o query param, descomente a linha abaixo
        // int iterations = (fibo != null && fibo > 0) ? fibo : 5;
        int iterations = 5; // valor padrão usado no exemplo de README
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

### Criar teste de integração

`src/test/java/com/vorozco/FibonacciResourceTest.java`:

```text
package com.vorozco;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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

### Testar

```bash
./mvnw test
curl "http://localhost:8080/fibonacci?fibo=7"
# Retorna: [0,1,1,2,3,5,8]
```

**O que isso demonstra**:
- Manipulação de query parameters
- Serialização JSON
- Uso do REST Assured para testes
- Padrões de testes de integração

**Referência de Commit**: [b3848b0](https://github.com/tuxtor/devsecops-oci-workshop/commit/b3848b0ee3305f570a36804fca494a70a4125221)

---

## Passo 5: Implementando Análise Estática com SpotBugs

* **Objetivo**: Adicionar verificações automatizadas de qualidade e segurança de código
* **Referência de Commit**: [872235d](https://github.com/tuxtor/devsecops-oci-workshop/commit/872235d88fd28fc3f6946cd4fd00d6c423b16f38)

### Adicionar plugin SpotBugs ao pom.xml

Adicione esta configuração de plugin na seção `<build><plugins>`:

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

### Introduzir um code smell para demonstração

Modifique `src/main/java/com/vorozco/GreetingResource.java`:

```text
@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        var dummyVar = doSpotBugsDemo();  // Variável não utilizada - SpotBugs detectará
        return "Hello from Quarkus REST";
    }

    private String doSpotBugsDemo() {
        var dummyVar = "This is not an error";  // Dead store
        return "SpotBugs demo";
    }
}
```

### Rodar a análise SpotBugs

```bash
./mvnw spotbugs:check
```

**Saída esperada**: SpotBugs reportará:
- Dead store to local variable (dummyVar)
- Unused method result

**O que o SpotBugs detecta**:
- Possíveis NullPointer
- Loops infinitos
- Problemas de sincronização
- Padrões de código vulneráveis
- Problemas de performance
- Más práticas

**Referência de Commit**: [872235d](https://github.com/tuxtor/devsecops-oci-workshop/commit/872235d88fd28fc3f6946cd4fd00d6c423b16f38)

---

## Passo 6: Corrigindo Problemas de Qualidade de Código

* **Objetivo**: Resolver avisos do SpotBugs para manter o código limpo
* **Referência de Commit**: [d959814](https://github.com/tuxtor/devsecops-oci-workshop/commit/d9598141f88c8f2ddc479b234f486f8fb6f292aa)

### Remover o código problemático

Atualize `src/main/java/com/vorozco/GreetingResource.java`:

```text
@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
```

### Verificar a correção

```bash
./mvnw spotbugs:check
# Deve passar sem avisos
```

**Boa prática**: Corrija problemas de segurança e qualidade o mais cedo possível (abordagem shift-left).

**Referência de Commit**: [d959814](https://github.com/tuxtor/devsecops-oci-workshop/commit/d9598141f88c8f2ddc479b234f486f8fb6f292aa)

---

## Passo 7: Adicionando Recurso de IP para Testes em Container

* **Objetivo**: Criar um endpoint para verificar a conectividade de container
* **Referência de Commit**: [ef407fe](https://github.com/tuxtor/devsecops-oci-workshop/commit/ef407fe2828252479b2a196a17652de94eaf6567)

### Criar o recurso IP

`src/main/java/com/vorozco/IpResource.java`:

```text
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

### Criar teste abrangente

`src/test/java/com/vorozco/IpResourceTest.java`:

```text
package com.vorozco;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class IpResourceTest {

    @Test
    public void testServerIpEndpoint() {
        // Regex para IPv4 e IPv6
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

### _Build_ da imagem do container

```bash
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-cloud-native-workload-jvm .
```

### Testar em diferentes ambientes

```bash
# Desenvolvimento local
./mvnw quarkus:dev
curl http://localhost:8080/ip

# Container Docker (após build)
docker run -p 8080:8080 quarkus/quarkus-cloud-native-workload-jvm
curl http://localhost:8080/ip
```

**Caso de uso**: Verificar rede de container, depurar pods no Kubernetes, demonstrar comunicação entre microsserviços.

**Referência de Commit**: [ef407fe](https://github.com/tuxtor/devsecops-oci-workshop/commit/ef407fe2828252479b2a196a17652de94eaf6567)

---

## Passo 8: Configurando CI/CD com GitHub Actions

* **Objetivo**: Automatizar testes, verificações de qualidade e _builds_
* **Referência de Commit**: [f4800e7](https://github.com/tuxtor/devsecops-oci-workshop/commit/f4800e757c619bf956ce7dede9687328feb63311)

### Criar workflow do GitHub Actions

Crie `.github/workflows/full-build.yml`:

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
        run: ./mvnw verify spotbugs:check
        working-directory: quarkus-cloud-native-workload
```

### O que esse workflow faz:

1. **Gatilho**: Roda em push para main e em pull requests
2. **Checkout**: Obtém o código
3. **Configuração Java**: Instala JDK 21 com _cache_ de Maven
4. **Verificação**: Executa testes e SpotBugs

### Testar o workflow

```bash
git add .github/workflows/full-build.yml
git commit -m "Add CI/CD pipeline"
git push origin main
```

Visite a aba **Actions** do repositório para ver a execução.

**Benefícios do CI/CD**:
- Gates de qualidade automatizados
- Ambiente de build consistente
- Feedback rápido
- Evita que defeitos cheguem à produção

**Referência de Commit**: [f4800e7](https://github.com/tuxtor/devsecops-oci-workshop/commit/f4800e757c619bf956ce7dede9687328feb63311)

---

## Passo 9: Habilitando Deploy no Kubernetes com Eclipse JKube (Apenas para testes locais)

* **Objetivo**: Facilitar a criação de containers e deployment no Kubernetes
* **Referência de Commit**: [23f7471](https://github.com/tuxtor/devsecops-oci-workshop/commit/23f747128dbce54649233ce1b0899ffe8fcdeabb)

### Adicionar plugin JKube ao pom.xml

Adicione em `<properties>`:

```xml
<jkube.generator.name>docker.io/tuxtor/quarkus-cloud-native-workload:%l</jkube.generator.name>
```

Adicione em `<build><plugins>`:

```xml
<plugin>
    <groupId>org.eclipse.jkube</groupId>
    <artifactId>kubernetes-maven-plugin</artifactId>
    <version>1.18.2</version>
</plugin>
```

### Build da imagem

```bash
./mvnw k8s:build
```

Isso cria uma imagem Docker com a etiqueta especificada em `jkube.generator.name`.

### Gerar manifests do Kubernetes

```bash
./mvnw k8s:resource
```

### Criar cluster k3d (opcional)

```bash
k3d cluster create k3d-cluster
# k3d cluster delete k3d-cluster
```

### Fazer deploy no Kubernetes

```bash
./mvnw k8s:apply
```

### Recursos do JKube:

- **Autodetecta**: Analisa o projeto e gera recursos Kubernetes apropriados
- **Zero-config**: Funciona com configurações sensatas por padrão
- **Customizável**: Pode sobrescrever via configuração XML ou fragments
- **Multi-plataforma**: Suporta Kubernetes e OpenShift

**Exemplos de recursos gerados**:
- Deployment
- Service

**Referência de Commit**: [23f7471](https://github.com/tuxtor/devsecops-oci-workshop/commit/23f747128dbce54649233ce1b0899ffe8fcdeabb)

---

## Passo 10: Implementando Segurança Shift-Left com Trivy

* **Objetivo**: Escanear imagens de container em busca de vulnerabilidades no pipeline CI/CD
* **Referência de Commit**: [2b4a793](https://github.com/tuxtor/devsecops-oci-workshop/commit/2b4a79354c29c92c43e294f26fa55f5fd6acf4f9)

### Atualizar workflow do GitHub Actions

Modifique `.github/workflows/full-build.yml` para adicionar build Docker e scan Trivy:

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
        run: ./mvnw verify spotbugs:check
        working-directory: quarkus-cloud-native-workload
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

### O que o Trivy escaneia:

- **Pacotes do SO**: Vulnerabilidades em Alpine, Debian, Ubuntu, etc.
- **Dependências da aplicação**: Java, Node.js, Python, Ruby, etc.
- **Configurações incorretas**: Manifests Kubernetes, Dockerfiles, Terraform
- **Segredos**: Senhas hardcoded, chaves de API, tokens
- **Conformidade de licenças**: Dependências e licenças

### Entendendo a configuração do scan:

- `scan-type: "image"`: Escaneia imagem Docker
- `severity: 'CRITICAL,HIGH'`: Reporta apenas vulnerabilidades sérias
- `exit-code: "0"`: Mude para "1" para enforcement
- `format: "table"`: Saída legível

### Exemplo de saída do Trivy:

```
quarkus-cloud-native-workload: latest (alpine 3.18.0)
===========================================================
Total: 2 (HIGH: 2, CRITICAL: 0)

┌─────────────┬────────────────┬──────────┬───────────────────┬───────────────┬────────────────────────────────────┐
│   Library   │ Vulnerability  │ Severity │ Installed Version │ Fixed Version │              Title                 │
├─────────────┼────────────────┼──────────┼───────────────────┼───────────────┼────────────────────────────────────┤
│ openssl     │ CVE-2023-12345 │ HIGH     │ 3.0.8-r0          │ 3.0.9-r0      │ Vulnerabilidade OpenSSL            │
│ libcrypto   │ CVE-2023-67890 │ HIGH     │ 3.0.8-r0          │ 3.0.9-r0      │ Fraqueza criptográfica             │
└─────────────┴────────────────┴──────────┴───────────────────┴───────────────┴────────────────────────────────────┘
```

### Benefícios do shift-left:

1. **Detecção precoce**: Encontre vulnerabilidades antes do deploy
2. **Feedback rápido**: Desenvolvedores recebem resultados imediatamente
3. **Redução de custos**: Mais barato consertar na dev do que em produção
4. **Compliance**: Documenta postura de segurança
5. **Prevenção**: Bloqueia imagens vulneráveis de chegar à produção

### Configuração avançada (opcional):

Para enforcement estrito, mude `exit-code` para `"1"`:

```yaml
- name: Run Trivy security scan (Docker)
  uses: aquasecurity/trivy-action@0.33.1
  with:
    scan-type: "image"
    image-ref: docker.io/tuxtor/quarkus-cloud-native-workload:latest
    format: "sarif"
    output: "trivy-results.sarif"
    exit-code: "1"  # Falha o build em caso de vulnerabilidades
    severity: 'CRITICAL,HIGH'

- name: Upload Trivy results to GitHub Security
  uses: github/codeql-action/upload-sarif@v2
  if: always()
  with:
    sarif_file: "trivy-results.sarif"
```

**Referência de Commit**: [2b4a793](https://github.com/tuxtor/devsecops-oci-workshop/commit/2b4a79354c29c92c43e294f26fa55f5fd6acf4f9)

---

## Conclusão

### O que você conquistou

Parabéns! Você construiu um pipeline DevSecOps completo incorporando:

* **Ambiente de desenvolvimento**: Cloud com GitHub Codespaces
* **Framework moderno**: Quarkus para aplicações Java cloud-native
* **Testes abrangentes**: Unitários e de integração com REST Assured
* **Qualidade de código**: Análise estática com SpotBugs
* **Automação CI/CD**: Pipeline com GitHub Actions
* **Containerização**: Docker com Eclipse JKube
* **Scan de segurança**: Trivy para detecção de vulnerabilidades
* **Cloud Native**: Deploys prontos para Kubernetes

### Fluxo do pipeline DevSecOps

```
┌─────────────────┐
│  Commit de Código│
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  GitHub Actions │
│   Acionado      │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Build & Test   │──► Testes unitários
│                 │──► Testes de integração
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Scan SpotBugs  │──► Qualidade de código
│                 │──► Problemas de segurança
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Docker Build   │──► JKube Build
│                 │──► Imagem de Container
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   Trivy Scan    │──► Verificação de vulnerabilidades
│                 │──► Verificação de conformidade
└─────────────────┘
```

### Princípios DevSecOps aplicados

1. **Segurança shift-left**: Checks de segurança integrados cedo no desenvolvimento
2. **Automação primeiro**: Cada passo é automatizado e reprodutível
3. **Feedback contínuo**: Desenvolvedores recebem resultados imediatos
4. **Infraestrutura como código**: Tudo versionado e reproduzível
5. **Defesa em profundidade**: Múltiplas camadas de segurança (estática, scan de imagem)

### Próximos passos

O Ops no DevSecOps :)

### Recursos

- [Documentação Quarkus](https://quarkus.io/guides/)
- [Eclipse JKube](https://www.eclipse.org/jkube/)
- [Documentação Trivy](https://aquasecurity.github.io/trivy/)
- [Manual SpotBugs](https://spotbugs.readthedocs.io/)
- [GitHub Actions](https://docs.github.com/en/actions)

### Repositório

Código fonte completo:  [https://github.com/tuxtor/devsecops-oci-workshop](https://github.com/tuxtor/devsecops-oci-workshop)
