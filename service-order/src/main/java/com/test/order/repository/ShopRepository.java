package com.test.order.repository;

import com.test.order.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, String> {
}
