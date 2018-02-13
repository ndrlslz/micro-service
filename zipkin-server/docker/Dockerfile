FROM infra-image
MAINTAINER xu

ARG jar
ENV JAR_FILE=$jar
WORKDIR /apps/boot

COPY ./build/libs/$jar $jar
COPY ./docker/run.sh run.sh

CMD ./run.sh
EXPOSE 9411
