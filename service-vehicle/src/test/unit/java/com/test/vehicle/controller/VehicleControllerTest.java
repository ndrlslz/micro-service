package com.test.vehicle.controller;

import com.test.vehicle.entity.VehicleEntity;
import com.test.vehicle.model.Vehicle;
import com.test.vehicle.model.Vehicles;
import com.test.vehicle.service.VehicleService;
import com.test.vehicle.controller.VehicleController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

import java.math.BigDecimal;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class VehicleControllerTest {
    @Mock
    private VehicleService vehicleService;

    @Mock
    private Pageable pageable;

    @Mock
    private PagedResourcesAssembler<VehicleEntity> pagedResourcesAssembler;

    @InjectMocks
    private VehicleController vehicleController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void should_return_vehicles() throws Exception {
        Vehicles expected = new Vehicles()
                .withVehicles(singletonList(
                        new Vehicle()
                                .withName("new_car")
                                .withPrice(new BigDecimal(100))));
        when(vehicleService.retrieveAllVehicles(anyString(), any(Pageable.class), any())).thenReturn(expected);

        Vehicles vehicles = vehicleController.retrieveAllVehicles(pageable, pagedResourcesAssembler, "1");

        assertEquals(expected, vehicles);
    }


}