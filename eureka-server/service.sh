image_name=eureka-server
container_name=eureka-server-container

active_container_id=$(docker ps -q --filter="ancestor=${image_name}")
all_container_id=$(docker ps -aq --filter="ancestor=${image_name}")
specfic_container_id=$(docker ps -aq --filter="name=${container_name}")

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

run_container() {
    echo "docker run ${container_name}"
    docker run -d -it -p 8761:8761 --network=microservice --name ${container_name} ${image_name}
}

restart() {
    rm_container
    run_container
}

case "$1" in
    start)
        if [ -n "${active_container_id}" ]; then
            echo "${container_name} has already been started"
        elif [ -n "${specfic_container_id}" ]; then
            echo "start ${container_name}"
            docker start ${specfic_container_id}
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
    remove)
        rm_container
    ;;
    *)
        echo "Usage: ./service.sh start|stop|restart|remove"
esac
