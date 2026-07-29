#!/bin/bash

# ============================================================
#  Script de arranque de microservicios
#  Orden: config-server → eureka → oauth → personas → productos → gateway
# ============================================================

# --- Configuración ---
BASE_DIR="$(cd "$(dirname "$0")" && pwd)"   # directorio raíz del proyecto (ajusta si es necesario)
LOG_DIR="$BASE_DIR/logs"
JAVA_OPTS="-Xms128m -Xmx256m"              # ajusta memoria si lo necesitas
JAVA_OPTS="$JAVA_OPTS -Dspring.jmx.enabled=false"  # de
STARTUP_WAIT=15                             # segundos de espera entre servicios

# Modo de arranque: "jar" (java -jar) o "mvn" (mvn spring-boot:run)
MODE="${1:-jar}"

# --- Colores ---
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# --- Lista de servicios en orden de arranque ---
SERVICES=(
  # "ectemplate-service-config-server"
  "ectemplate-servicio-eureka-server"
  "ectemplate-service-oauth"
  "ectemplate-servicio-personas"
  "ectemplate-servicio-productos"
  "ectemplate-servicio-gateway"
)

# ============================================================
# Funciones
# ============================================================

mkdir -p "$LOG_DIR"

log() {
  echo -e "$(date '+%H:%M:%S') $1"
}

start_service_jar() {
  local SERVICE=$1
  local JAR_PATH="$BASE_DIR/$SERVICE/target/$SERVICE.jar"

  if [ ! -f "$JAR_PATH" ]; then
    # Intenta buscar cualquier jar que no sea -sources ni -javadoc
    JAR_PATH=$(find "$BASE_DIR/$SERVICE/target" -maxdepth 1 -name "*.jar" \
      ! -name "*sources*" ! -name "*javadoc*" 2>/dev/null | head -1)
  fi

  if [ -z "$JAR_PATH" ] || [ ! -f "$JAR_PATH" ]; then
    log "${RED}[ERROR]${NC} No se encontró el JAR para $SERVICE. ¿Has ejecutado 'mvn package'?"
    return 1
  fi

  log "${CYAN}[START]${NC} Arrancando $SERVICE (jar)..."
  nohup java $JAVA_OPTS -jar "$JAR_PATH" \
    > "$LOG_DIR/$SERVICE.log" 2>&1 &
  echo $! > "$LOG_DIR/$SERVICE.pid"
  log "${GREEN}[OK]${NC}    $SERVICE → PID $(cat "$LOG_DIR/$SERVICE.pid") | Log: $LOG_DIR/$SERVICE.log"
}

start_service_mvn() {
  local SERVICE=$1
  local SERVICE_DIR="$BASE_DIR/$SERVICE"

  if [ ! -d "$SERVICE_DIR" ]; then
    log "${RED}[ERROR]${NC} Directorio no encontrado: $SERVICE_DIR"
    return 1
  fi

  log "${CYAN}[START]${NC} Arrancando $SERVICE (mvn spring-boot:run)..."
  nohup mvn -f "$SERVICE_DIR/pom.xml" spring-boot:run \
    > "$LOG_DIR/$SERVICE.log" 2>&1 &
  echo $! > "$LOG_DIR/$SERVICE.pid"
  log "${GREEN}[OK]${NC}    $SERVICE → PID $(cat "$LOG_DIR/$SERVICE.pid") | Log: $LOG_DIR/$SERVICE.log"
}

wait_for_service() {
  local SERVICE=$1
  local SECONDS_TO_WAIT=$2
  log "${YELLOW}[WAIT]${NC}  Esperando ${SECONDS_TO_WAIT}s para que $SERVICE levante..."
  sleep "$SECONDS_TO_WAIT"
}

# ============================================================
# Arranque principal
# ============================================================

echo ""
echo -e "${CYAN}============================================================${NC}"
echo -e "${CYAN}   Arrancando microservicios — modo: $MODE${NC}"
echo -e "${CYAN}============================================================${NC}"
echo ""

for i in "${!SERVICES[@]}"; do
  SERVICE="${SERVICES[$i]}"

  if [ "$MODE" = "mvn" ]; then
    start_service_mvn "$SERVICE"
  else
    start_service_jar "$SERVICE"
  fi

  # Esperar más tiempo tras los servicios de infraestructura
  if [ "$SERVICE" = "ectemplate-service-config-server" ]; then
    wait_for_service "$SERVICE" 20
  elif [ "$SERVICE" = "ectemplate-servicio-eureka-server" ]; then
    wait_for_service "$SERVICE" 20
  elif [ $i -lt $(( ${#SERVICES[@]} - 1 )) ]; then
    wait_for_service "$SERVICE" "$STARTUP_WAIT"
  fi
done

echo ""
echo -e "${GREEN}============================================================${NC}"
echo -e "${GREEN}   Todos los servicios arrancados.${NC}"
echo -e "${GREEN}   Logs en: $LOG_DIR${NC}"
echo -e "${GREEN}============================================================${NC}"
echo ""
echo -e "Para parar todos los servicios ejecuta: ${YELLOW}./stop-microservices.sh${NC}"
