# ecommercewebtemplate

Java 21 + Spring Boot 2.7.18 + Maven multi-módulo.

## Build

```bash
mvn clean install -DskipTests
```

## Microservicios

- `ectemplate-servicio-eureka-server` — Eureka
- `ectemplate-servicio-gateway` — Gateway
- `ectemplate-service-config-server` — Config Server
- `ectemplate-service-oauth` — OAuth
- `ectemplate-servicio-personas` — Personas CRUD
- `ectemplate-servicio-productos` — Productos CRUD
- `ectemplate-service-zuul-server` — Zuul (desactivado)

## Reglas

- No modifiques pom.xml raíz sin preguntar
- Código nuevo en inglés (clases, variables, comentarios)
- Sigue el estilo existente en el módulo que toques
