image_name=api-gateway
container_name=api-gateway-container

active_container_id=$(docker ps -q --filter="ancestor=${image_name}")
all_container_id=$(docker ps -aq --filter="ancestor=${image_name}")
specific_container_id=$(docker ps -aq --filter="name=${container_name}")

rm_container() {
    if [ -n "${active_container_id}" ]; then
        echo "stop active container ${active_container_id}"
        docker stop ${active_container_id}
    fi

    if [ -n "${all_container_id}" ]; then
        echo "rm container ${all_container_id}"
        docker rm ${all_container_id}
    fi
}

rm_image() {
    rm_container
    image_id=$(docker images -aq ${image_name})
    if [ -n "${image_id}" ]; then
        echo "remove image ${image_id}"
        docker rmi ${image_id}
    fi
}

build_image() {
    workdir=$(dirname $0)
    cd $workdir

    ./gradlew clean build

    rm_image

    export JAR_FILE=$(cd build/libs && ls *.jar)
    docker build -q -t ${image_name} -f docker/Dockerfile --build-arg jar=$JAR_FILE .
}

run_container() {
    echo "docker run ${container_name}"
    docker run -d -it -p 1101:1101 --network=microservice --name ${container_name} ${image_name}
}

restart() {
    rm_container
    run_container
}

case "$1" in
    start)
        if [ -n "${active_container_id}" ]; then
            echo "${container_name} has already been started"
        elif [ -n "${specific_container_id}" ]; then
            echo "start ${container_name}"
            docker start ${specific_container_id}
        else
            restart
        fi
    ;;
    stop)
        if [ -n "${active_container_id}" ]; then
            echo "stop container ${container_name}"
            docker stop ${active_container_id}
        else
            echo "container ${container_name} has already been stopped"
        fi
    ;;
    restart)
        restart
    ;;
    rm)
        rm_container
    ;;
    rmi)
        rm_image
    ;;
    build)
        build_image
    ;;
    *)
        echo "Usage: ./service.sh build|start|stop|restart|rm|rmi"
esac
