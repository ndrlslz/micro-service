package com.test.vehicle.service;

import com.test.vehicle.entity.VehicleEntity;
import com.test.vehicle.model.Vehicles;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

public interface VehicleService {
    Vehicles retrieveAllVehicles(String shopId, Pageable pageable, PagedResourcesAssembler<VehicleEntity> pagedResourcesAssembler);
}
