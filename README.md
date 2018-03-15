### Steps of how to start the whole micro service.

**Note**: you'd better to increase docker memory to 8G, otherwise docker will kill container itself because of OutOfMemory, and run below commands from project root folder.

1. build base image: `cd infra-image;docker build -q -t infra-image -f Dockerfile .`
2. build service images: `./service.sh build`
3. create network: `docker network create microservice`
4. start database: `cd mysql;docker-compose up`
5. start whole micro service: `docker-compose up -d`

After starting the project, below are the urls of service.

|    service name    | service url |
| ---------- | --- |
| eureka-server |  http://localhost:8761 |
| service-order       |  http://localhost:8763/swagger-ui.html |
| service-vehicle     |  http://localhost:8764/swagger-ui.html |
| bff     | http://localhost:8765/swagger-ui.html |
| api-gateway     | prefix is http://localhost:1101. for instance, access bff via http://localhost:1101/bff/v1/shops/1 |
| config-server   | prefix is http://localhost:1201. for instance, access bff config via http://localhost:1201/bff/docker |
| zipkin-server     | http://localhost:9411 |
