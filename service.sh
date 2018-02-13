config_project="config-server"
projects=( "eureka-server" "eureka-client" "service-order" "service-vehicle" "bff" "api-gateway" "zipkin-server" )

microservice_dir=$(dirname $0)
command=$1

${microservice_dir}/${config_project}/service.sh $1
if [ "$1" = "start" ]; then
  sleep 10
fi

for project in "${projects[@]}"; do
    pushd ${microservice_dir}/${project}
    if [ -f ./service.sh ]; then
        ./service.sh $command
    fi
    popd
done

