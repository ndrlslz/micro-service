package com.test.translator;

import com.test.model.Vehicle;
import com.test.model.Vehicles;
import com.test.util.VehicleBuilder;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import static com.test.util.VehicleBuilder.vehicleResource;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class VehicleTranslatorTest {
    private VehicleTranslator vehicleTranslator = new VehicleTranslator();

    @Test
    public void should_return_vehicles() throws Exception {
        PagedResources<Resource<Vehicle>> vehiclePagedResources = VehicleBuilder.vehiclePagedResources(
                asList(vehicleResource(100, "benz"), vehicleResource(200, "maz")));

        Vehicles vehicles = vehicleTranslator.translate(vehiclePagedResources);

        assertThat(vehicles.getVehicles().size(), is(2));
        assertThat(vehicles.getVehicles().get(0).getName(), is("benz"));
        assertThat(vehicles.getVehicles().get(1).getName(), is("maz"));
    }
}