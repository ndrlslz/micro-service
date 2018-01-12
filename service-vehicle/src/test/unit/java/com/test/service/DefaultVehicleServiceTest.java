package com.test.service;

import com.test.entity.VehicleEntity;
import com.test.model.Vehicle;
import com.test.model.Vehicles;
import com.test.repository.VehicleRepository;
import com.test.translator.VehicleTranslator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import java.math.BigDecimal;

import static com.test.util.VehicleBuilder.vehiclePagedResources;
import static com.test.util.VehicleBuilder.vehicleResource;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultVehicleServiceTest {
    private DefaultVehicleService service;
    private VehicleAssembler vehicleAssembler = new VehicleAssembler();
    private VehicleTranslator vehicleTranslator = new VehicleTranslator();

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private PagedResourcesAssembler<VehicleEntity> pagedResourcesAssembler;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new DefaultVehicleService(vehicleAssembler, vehicleTranslator, vehicleRepository);
    }

    @Test
    public void should_return_all_vehicles() throws Exception {
        PagedResources<Resource<Vehicle>> pagedResources = vehiclePagedResources(
                asList(vehicleResource(100, "benz"), vehicleResource(200, "mar")));
        Page<VehicleEntity> entityPage = new PageImpl<>(singletonList(new VehicleEntity()));

        when(vehicleRepository.findAllByShopId(anyString(), any(Pageable.class))).thenReturn(entityPage);
        when(pagedResourcesAssembler.toResource(entityPage, vehicleAssembler)).thenReturn(pagedResources);

        Vehicles vehicles = service.retrieveAllVehicles("1", new PageRequest(1, 10), pagedResourcesAssembler);

        assertThat(vehicles.getVehicles().size(), is(2));
        assertThat(vehicles.getVehicles().get(0).getName(), is("benz"));
        assertThat(vehicles.getVehicles().get(0).getPrice(), is(new BigDecimal(100)));
    }
}