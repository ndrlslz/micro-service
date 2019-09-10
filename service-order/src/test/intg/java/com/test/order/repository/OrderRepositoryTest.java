package com.test.order.repository;

import com.test.order.entity.OrderEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void should_return_all_shop_entities() {
        Page<OrderEntity> orders = orderRepository.findAllByShopId("1", new PageRequest(0, 10));

        assertThat(orders.getContent())
                .hasSize(3)
                .extracting(OrderEntity::getVehicle)
                .containsExactlyInAnyOrder("benz", "mar", "test");
    }
}
