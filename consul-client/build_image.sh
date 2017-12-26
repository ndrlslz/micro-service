image_name=consul-client
container_name=consul-client-container

workdir=$(dirname $0)

cd $workdir
./gradlew clean build

active_container_id=$(docker ps -q --filter="ancestor=${image_name}")
if [ -n "${active_container_id}" ]; then
    echo "stop active container ${active_container_id}"
    docker stop ${active_container_id}
fi

all_container_id=$(docker ps -aq --filter="ancestor=${image_name}")
if [ -n "${all_container_id}" ]; then
    echo "rm container ${all_container_id}"
    docker rm ${all_container_id}
fi

image_id=$(docker images -aq ${image_name})
if [ -n "${image_id}" ]; then
    echo "remove image ${image_id}"
    docker rmi ${image_id}
fi

export JAR_FILE=$(cd $workdir/build/libs && ls *.jar)
docker build -q -t ${image_name} -f docker/Dockerfile --build-arg jar=$JAR_FILE .
