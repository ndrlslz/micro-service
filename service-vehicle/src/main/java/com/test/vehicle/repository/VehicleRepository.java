package com.test.vehicle.repository;

import com.test.vehicle.entity.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, String> {
    Page<VehicleEntity> findAllByShopId(String shopId, Pageable pageable);
}
