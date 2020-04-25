#!/bin/bash

SCRIPT_DIR="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
function log_blue() { printf "\x1B[94m>> $1\x1B[39m\n"; }

function start-kafka() {
  log_blue "Starting kafka..."
  LOCAL_IP=$(ifconfig en0 | grep "inet " | cut -d\  -f2) docker-compose -f "$SCRIPT_DIR/docker/kafka/docker-compose.yml" up -d --remove-orphans --build --force-recreate
}

function stop-all() {
  log_blue "Stopping kafka..."
    docker-compose -f "$SCRIPT_DIR/docker/kafka/docker-compose.yml" stop
}

function kafka-topic-consume() {
    log_blue "Consuming $1 topic"
    docker exec -it kafka kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic $1 --from-beginning
}