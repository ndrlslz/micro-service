package com.test.repository;

import com.test.entity.ShopEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShopRepositoryTest {
    @Autowired
    private ShopRepository shopRepository;

    @Test
    public void should_return_all_shop_entities() throws Exception {
        Page<ShopEntity> shopEntities = shopRepository.findAll(new PageRequest(0, 10));

        assertThat(shopEntities.getTotalElements(), is(2L));
        assertThat(shopEntities.getContent().get(0).getName(), is("name1"));
        assertThat(shopEntities.getContent().get(0).getCode(), is("code1"));
    }
}
