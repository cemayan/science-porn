#!/bin/bash

SCRIPT_DIR="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
function log_blue() { printf "\x1B[94m>> $1\x1B[39m\n"; }

function start-kafka() {
  log_blue "Starting kafka..."
  LOCAL_IP=$(ifconfig en0 | grep "inet " | cut -d\  -f2) docker-compose -f "$SCRIPT_DIR/docker/kafka/docker-compose.yml" up -d  --remove-orphans --build
}

function start-mysql() {
  log_blue "Starting mysql..."
  docker-compose -f "$SCRIPT_DIR/docker/mysql/docker-compose.yml" up -d
}

function start-redis() {
  log_blue "Starting redis..."
  docker-compose -f "$SCRIPT_DIR/docker/redis/docker-compose.yml" up -d
}

function start-neo4j() {
  log_blue "Starting neo4j..."
  docker-compose -f "$SCRIPT_DIR/docker/neo4j/docker-compose.yml" up -d
}

function start-all() {
  log_blue "Starting services..."
  start-kafka
  start-redis
  start-neo4j
  start-mysql
  #start-all-services
}


function stop-all() {
  log_blue "Stopping kafka..."
  docker-compose -f "$SCRIPT_DIR/docker/kafka/docker-compose.yml" down
  docker-compose -f "$SCRIPT_DIR/docker/redis/docker-compose.yml" down
  docker-compose -f "$SCRIPT_DIR/docker/neo4j/docker-compose.yml" stop
  docker-compose -f "$SCRIPT_DIR/docker/mysql/docker-compose.yml" stop
  #docker-compose -f "$SCRIPT_DIR/docker/services/docker-compose.yml"  down

}

function kafka-topic-consume() {
    log_blue "Consuming $1 topic"
    docker exec -it kafka kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic $1 --from-beginning
}

function start-all-services() {
  log_blue "Starting auth-service..."
  docker-compose -f "$SCRIPT_DIR/docker/services/docker-compose.yml" up -d  --build --remove-orphans --force-recreate
}


function convert-services-kompose() {
  log_blue "Kompose service converting..."
  cd k8/services
  kompose -f "$SCRIPT_DIR/docker/services/docker-compose.yml" convert
}

function convert-mysql() {
  log_blue "Kompose mysql converting..."
  cd k8/mysql
  kompose -f "$SCRIPT_DIR/docker/mysql/docker-compose.yml" convert
}

function convert-kafka() {
  log_blue "Kompose kafka converting..."
  cd k8/kafka
  LOCAL_IP=$(ifconfig en0 | grep "inet " | cut -d\  -f2) kompose -f "$SCRIPT_DIR/docker/kafka/docker-compose.yml" convert
}


function kompose-up-kafka() {
  log_blue "Kompose up kafka ..."
  LOCAL_IP=$(ifconfig en0 | grep "inet " | cut -d\  -f2) kompose -f "$SCRIPT_DIR/docker/kafka/docker-compose.yml" up
}