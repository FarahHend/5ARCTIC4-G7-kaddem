terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 2.0"
    }
  }
}

provider "docker" {}

resource "docker_network" "microservices_net" {
  name   = "microservices-net"
  driver = "bridge"
}

resource "docker_volume" "mongo_data" {
  name = "mongo_data"
}

resource "docker_volume" "sonarqube_data" {
  name = "sonarqube_data"
}

resource "docker_volume" "sonarqube_extensions" {
  name = "sonarqube_extensions"
}

resource "docker_volume" "sonarqube_logs" {
  name = "sonarqube_logs"
}

resource "docker_volume" "nexus_data" {
  name = "nexus_data"
}

resource "docker_volume" "mysql_data" {
  name = "mysql_data"
}

resource "docker_volume" "postgres_data" {
  name = "postgres_data"
}

resource "docker_volume" "frontend_data" {
  name = "frontend_data"
}

resource "docker_volume" "projetmicroservice_data" {
  name = "projetmicroservice_data"
}

resource "docker_volume" "equipemicroservice_data" {
  name = "equipemicroservice_data"
}

resource "docker_volume" "prometheus_data" {
  name = "prometheus_data"
}

resource "docker_volume" "grafana_data" {
  name = "grafana_data"
}
