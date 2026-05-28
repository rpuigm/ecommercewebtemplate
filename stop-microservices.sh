#!/bin/bash

# ============================================================
#  Script de parada de microservicios
# ============================================================

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"
LOG_DIR="$BASE_DIR/logs"

GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

SERVICES=(
  "ectemplate-servicio-gateway"
  "ectemplate-servicio-productos"
  "ectemplate-servicio-personas"
  "ectemplate-service-oauth"
  "ectemplate-servicio-eureka-server"
  "ectemplate-service-config-server"
)

echo ""
echo -e "${YELLOW}============================================================${NC}"
echo -e "${YELLOW}   Parando microservicios (orden inverso)${NC}"
echo -e "${YELLOW}============================================================${NC}"
echo ""

for SERVICE in "${SERVICES[@]}"; do
  PID_FILE="$LOG_DIR/$SERVICE.pid"
  if [ -f "$PID_FILE" ]; then
    PID=$(cat "$PID_FILE")
    if kill -0 "$PID" 2>/dev/null; then
      kill "$PID"
      echo -e "$(date '+%H:%M:%S') ${GREEN}[STOP]${NC}  $SERVICE (PID $PID) detenido."
    else
      echo -e "$(date '+%H:%M:%S') ${YELLOW}[SKIP]${NC}  $SERVICE no estaba corriendo (PID $PID)."
    fi
    rm -f "$PID_FILE"
  else
    echo -e "$(date '+%H:%M:%S') ${RED}[WARN]${NC}  No se encontró PID para $SERVICE."
  fi
done

echo ""
echo -e "${GREEN}Todos los servicios detenidos.${NC}"
echo ""
