#!/bin/bash

# Compilar el proyecto Maven
mvn clean package

# Construir la imagen Docker con etiqueta "calculadora-primos"
docker build -t calculadora-primos .

echo "Imagen Docker construida correctamente."
