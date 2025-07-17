FROM debian:bullseye-slim

# Variables
ARG MAVEN_VERSION=3.9.11
ARG NODE_VERSION=18

# Evitar preguntas durante la instalación
ENV DEBIAN_FRONTEND=noninteractive

# Instalar dependencias básicas
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    bash \
    git \
    unzip \
    gnupg2 \
    ca-certificates \
    openjdk-17-jdk \
    && rm -rf /var/lib/apt/lists/*

# Instalar Maven
RUN curl -fsSL https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | tar -xz -C /opt \
    && ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME=/opt/apache-maven-${MAVEN_VERSION}
ENV PATH="${MAVEN_HOME}/bin:${PATH}"

# Instalar Node.js y npm (vía NodeSource)
RUN curl -fsSL https://deb.nodesource.com/setup_${NODE_VERSION}.x | bash - \
    && apt-get install -y --no-install-recommends nodejs \
    && npm install -g npm

# Instalar Angular CLI
RUN npm install -g @angular/cli

# Crear directorio de trabajo
WORKDIR /workspace

# Exponer puertos típicos
EXPOSE 4200 8080 8090

# Usuario por defecto
CMD [ "bash" ]
