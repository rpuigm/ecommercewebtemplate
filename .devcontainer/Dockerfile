FROM debian:bullseye-slim

# Variables para PostgreSQL
ENV POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=postgres \
    POSTGRES_DB=postgres \
    PGDATA=/var/lib/postgresql/data

# Instalar dependencias: PostgreSQL, curl, nodejs, etc.
RUN apt-get update && apt-get install -y --no-install-recommends \
    postgresql postgresql-contrib \
    curl bash git \
    ca-certificates \
    gnupg \
    lsb-release \
    nodejs npm \
    && rm -rf /var/lib/apt/lists/*

# Crear directorio para datos de PostgreSQL
RUN mkdir -p ${PGDATA} && chown -R postgres:postgres /var/lib/postgresql

# Cambiar a usuario postgres para inicializar la base de datos
USER postgres

# Inicializar PostgreSQL (solo estructura, sin ejecución)
RUN /usr/lib/postgresql/*/bin/initdb -D ${PGDATA}

USER root

# Copiar script de arranque
COPY init.sh /usr/local/bin/init.sh
RUN chmod +x /usr/local/bin/init.sh

# Exponer puertos comunes de desarrollo
EXPOSE 5432 4200 8090

# Lanzar init.sh como comando principal
CMD ["/usr/local/bin/init.sh"]
