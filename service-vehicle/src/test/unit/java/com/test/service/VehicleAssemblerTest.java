package com.test.service;

import com.test.entity.VehicleEntity;
import com.test.model.Vehicle;
import org.junit.Test;
import org.springframework.hateoas.Resource;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class VehicleAssemblerTest {
    private VehicleAssembler vehicleAssembler = new VehicleAssembler();

    @Test
    public void should_return_vehicle() throws Exception {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setPrice(new BigDecimal(100));
        vehicleEntity.setName("benz");

        Resource<Vehicle> vehicleResource = vehicleAssembler.toResource(vehicleEntity);

        assertThat(vehicleResource.getContent().getName(), is("benz"));
        assertThat(vehicleResource.getContent().getPrice(), is(new BigDecimal(100)));
    }
}