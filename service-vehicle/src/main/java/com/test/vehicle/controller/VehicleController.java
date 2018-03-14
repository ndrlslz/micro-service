package com.test.vehicle.controller;

import com.test.utils.constraint.ValidShopId;
import com.test.vehicle.entity.VehicleEntity;
import com.test.utils.log.PerformanceLog;
import com.test.vehicle.model.Vehicles;
import com.test.vehicle.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Vehicle", description = "Access to vehicle resource")
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@Validated
public class VehicleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    @ApiOperation(value = "Retrieve vehicles", response = Vehicles.class)
    @PerformanceLog
    public Vehicles retrieveAllVehicles(@PageableDefault Pageable pageable,
                                        PagedResourcesAssembler<VehicleEntity> pagedResourcesAssembler,
                                        @Valid @ValidShopId @ApiParam @RequestParam(required = false) String shopId) {
        LOGGER.info("retrieve vehicles for shop id " + shopId);

        return vehicleService.retrieveAllVehicles(shopId, pageable, pagedResourcesAssembler);
    }
}
