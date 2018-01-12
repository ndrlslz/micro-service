projects=( "eureka-server" "eureka-client" "service-order" "service-vehicle" )

microservice_dir=$(dirname $0)
command=$1

for project in "${projects[@]}"; do
    pushd ${microservice_dir}/${project}
    if [ -f ./service.sh ]; then
        ./service.sh $command
    fi
    popd
done


