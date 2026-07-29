# Copilot instructions for this repository

Purpose: quick operational guide for Copilot-powered sessions (build/test/lint, architecture, repo-specific conventions).

---
1) Build / test / lint commands

Java / multi-module (Maven)
- Full CI build + analysis (used in GitHub Actions):
  mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=rpuigm_ecommercewebtemplate
- Local full build and tests (root):
  mvn -B verify
- Build / test a single module (example: productos):
  mvn -pl ectemplate-servicio-productos -am package
- Run all unit tests (root or module):
  mvn test
- Run a single JUnit test class or method:
  mvn -Dtest=MyTestClass test
  mvn -Dtest=MyTestClass#myTestMethod test
- Coverage: jacoco is configured in service poms (prepare-agent + report during test phase).

Angular frontend (clientes-app)
- Install deps: npm ci (or npm install) inside clientes-app/
- Dev server: npm start  (runs `ng serve`)
- Build: npm run build  (runs `ng build`)
- Unit tests: npm test  (runs `ng test` via Karma)
- Run a single spec (Angular CLI >=13):
  npx -p @angular/cli ng test --project=clientes-app --include='**/path/to/file.spec.ts'
  or from inside clientes-app: ng test --include='**/path/to/file.spec.ts'

Notes: there is no repository-wide JS/TS lint script present; consider adding ESLint if needed.

Also: quick DB for local dev (from README):
  docker run -p 5432:5432 -e POSTGRES_PASSWORD=ruben -d -v ./postgres_data:/var/lib/postgresql/data postgres

---
2) High-level architecture (big picture)

- This is a Java Spring Boot multi-module microservices project managed by a root Maven POM. Modules live at the repo root (named `ectemplate-*` / `ectemplate-servicio-*`). Primary modules include:
  - ectemplate-service-config-server (Spring Cloud Config)
  - ectemplate-servicio-eureka-server (Service registry)
  - ectemplate-service-oauth (authorization)
  - ectemplate-servicio-personas (domain service)
  - ectemplate-servicio-productos (domain service)
  - ectemplate-servicio-gateway (API gateway)
  - (zuul server module is present but commented out in modules list)

- Front-end: `clientes-app/` — Angular application (Angular CLI scaffold). The frontend is separate from the Maven modules and lives at the repo top-level.

- CI: GitHub Actions workflows under .github/workflows (Build uses Maven + SonarCloud). There are CodeQL and ZAP workflows configured.

- Testing / coverage: services use JUnit (spring-boot-starter-test), Mockito and jacoco for coverage reporting. Integration test sources are added via build-helper (src/integrationTest/java).

---
3) Key conventions and repo-specific patterns

- Module naming: Java modules use `ectemplate-*` prefix; package roots use `net.ectemplate`.
- Microservice layout: each service is a standalone Spring Boot app (its own pom.xml). Use Maven `-pl <module> -am` to build dependent modules as needed.
- Tests: unit tests use standard Maven lifecycle; integration tests are expected under `src/integrationTest/java` (build-helper plugin adds this source folder).
- Coverage: jacoco configured per-service; CI uploads/analyzes via SonarCloud (see workflow).
- Frontend: Angular CLI is used; run frontend commands from clientes-app/.
- No centralized linting step currently enforced in CI for JS/TS or Java (consider adding checkstyle/spotless/ESLint if enforcing style).

---
4) Files to consult for context

- Root: pom.xml (multi-module configuration)
- Each module: <module>/pom.xml (service-specific plugins, jacoco, dependencies)
- Frontend: clientes-app/package.json and clientes-app/README.md
- CI: .github/workflows/*.yml (Build uses SonarCloud)

---
If you (the human) want additions or prefer different command examples (docker-compose, specific profiles, or npm script names), say which area to expand.
