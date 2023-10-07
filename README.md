# Arq_AWS_Docker

### Elaborado por:
Jose Ricardo Olarte Pardo
__________________________________________________________________

### Requisitos

- Docker
- Java
- Maven
- IDE (Visual, Netbeans, Intellij, entre otros)
- Git
- Browser

__________________________________________________________________

## Instalación

Seguimos los siguientes pasos:

1. Para ello clonamos el repositorio de GIT
```
https://github.com/Ricardo-Olarte/Arq_AWS_Docker.git
```
![](https://github.com/Ricardo-Olarte/TOOLS/blob/main/taller6/ggit%20clone.png)

2. Abrimos nuestro IDE y abrimos la terminal
3. Desde la terminal, nos vamos al directorio del repositorio descargado y con el siguiente comando:
```
mvn clean install
```
4. Finalizando la compilación de Maven, corremos la siguiente línea, y el docker hub debe visualizarce de la siguiente forma
```
docker-compose up -d
```
![](https://github.com/Ricardo-Olarte/TOOLS/blob/main/taller6/dockercompuse.png)
5. Seguimos los siguientes comandos, para desplegar cada servicio de manera manual
```
docker network create netaws
```
```
docker run -d -p 35000:24000 --name roundrobbin --network netaws richi99/log_round_robin
```
```
docker run -d -p 35001:15000 --name log_service1 --network netaws richi99/log_service
```
```
docker run -d -p 35002:15000 --name log_service2 --network netaws richi99/log_service
```
```
docker run -d -p 35003:15000 --name log_service3 --network netaws richi99/log_service
```
```
docker run -d -p 27017:27017 -v mongodb:/data/db -v mongodb_config:/data/configdb --name mongodb --network netaws  mongo:3.6.1 mongod
```
6. Abriendo el navegador, a partir del localhost o desde la instancia de AWS

- DOCKER : [localhost:35000](http://localhost:35000)
- AWS : [EC2](http://ec2-35-171-24-107.compute-1.amazonaws.com:35000/)
  
__________________________________________________________________
# Explicación

__________________________________________________________________
# Herramientas Utilizadas

- Docker HUB, a continuación se mostrarán los repositorios respectivos
[Log Round Robbin](https://hub.docker.com/repository/docker/richi99/log_round_robin/general)
[Log Service](https://hub.docker.com/repository/docker/richi99/log_service/general)
- AWS, sus instancias respectivas
  
![](https://github.com/Ricardo-Olarte/TOOLS/blob/main/taller6/aws.png)
__________________________________________________________________

## Autor

- Jose Ricardo Olarte Pardo - [Ricardo-Olarte](https://github.com/Ricardo-Olarte)
