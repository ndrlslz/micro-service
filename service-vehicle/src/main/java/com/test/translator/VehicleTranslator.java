package com.test.translator;

import com.test.model.Vehicle;
import com.test.model.Vehicles;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class VehicleTranslator {
    public Vehicles translate(PagedResources<Resource<Vehicle>> resources) {
        return new Vehicles()
                .withMetadata(resources.getMetadata())
                .withLinks(resources.getLinks())
                .withVehicles(
                        resources
                                .getContent()
                                .stream()
                                .map(Resource::getContent)
                                .collect(toList()));
    }
}
