package com.test.service;

import com.test.entity.VehicleEntity;
import com.test.model.Vehicle;
import com.test.model.Vehicles;
import com.test.repository.VehicleRepository;
import com.test.translator.VehicleTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

@Service
public class DefaultVehicleService implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleAssembler vehicleAssembler;
    private final VehicleTranslator vehicleTranslator;

    @Autowired
    public DefaultVehicleService(VehicleAssembler vehicleAssembler, VehicleTranslator vehicleTranslator, VehicleRepository vehicleRepository) {
        this.vehicleAssembler = vehicleAssembler;
        this.vehicleTranslator = vehicleTranslator;
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicles retrieveAllVehicles(String shopId, Pageable pageable, PagedResourcesAssembler<VehicleEntity> pagedResourcesAssembler) {
        Page<VehicleEntity> entities = vehicleRepository.findAllByShopId(shopId, pageable);
        PagedResources<Resource<Vehicle>> resources = pagedResourcesAssembler.toResource(entities, vehicleAssembler);
        return vehicleTranslator.translate(resources);
    }
}
