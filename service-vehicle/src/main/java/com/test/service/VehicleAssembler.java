package com.test.service;

import com.test.entity.VehicleEntity;
import com.test.model.Vehicle;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Service;

@Service
public class VehicleAssembler implements ResourceAssembler<VehicleEntity, Resource<Vehicle>> {

    @Override
    public Resource<Vehicle> toResource(VehicleEntity entity) {
        return new Resource<>(
                new Vehicle()
                    .withName(entity.getName())
                    .withPrice(entity.getPrice()));
    }
}
