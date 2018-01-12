package com.test.service;

import com.test.entity.VehicleEntity;
import com.test.model.Vehicles;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

public interface VehicleService {
    Vehicles retrieveAllVehicles(String shopId, Pageable pageable, PagedResourcesAssembler<VehicleEntity> pagedResourcesAssembler);
}
