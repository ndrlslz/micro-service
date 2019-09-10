package com.test.order.repository;

import com.test.order.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
//    @Query("select o from OrderEntity o left join o.shop s where s.id = :shop_id")
    Page<OrderEntity> findAllByShopId(@Param("shop_id") String shop_id, Pageable pageable);
}
