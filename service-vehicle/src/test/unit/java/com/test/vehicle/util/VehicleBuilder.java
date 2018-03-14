package com.test.vehicle.util;

import com.test.vehicle.model.Vehicle;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import java.math.BigDecimal;
import java.util.Collection;

public class VehicleBuilder {
    public static Resource<Vehicle> vehicleResource(int price, String name) {
        return new Resource<>(
                new Vehicle()
                    .withName(name)
                    .withPrice(new BigDecimal(price))
        );
    }

    public static PagedResources<Resource<Vehicle>> vehiclePagedResources(Collection<Resource<Vehicle>> vehicleCollection) {
        return new PagedResources<>(
                vehicleCollection,
                new PagedResources.PageMetadata(10, 0, 0, 1)
        );
    }

}
