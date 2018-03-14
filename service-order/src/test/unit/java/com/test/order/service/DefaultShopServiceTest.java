package com.test.order.service;

import com.test.order.entity.ShopEntity;
import com.test.order.model.Shop;
import com.test.order.model.Shops;
import com.test.order.repository.ShopRepository;
import com.test.order.translator.ShopsTranslator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import java.util.ArrayList;
import java.util.Arrays;

import static com.test.order.util.ShopBuilder.shopPagedResources;
import static com.test.order.util.ShopBuilder.shopResource;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultShopServiceTest {
    private ShopsTranslator shopsTranslator = new ShopsTranslator();
    private ShopAssembler shopAssembler = new ShopAssembler();
    @Mock
    private ShopRepository shopRepository;

    @Mock
    private PagedResourcesAssembler<ShopEntity> pagedResourcesAssembler;

    private DefaultShopService shopService;

    @Before
    public void setUp() {
        initMocks(this);
        shopService = new DefaultShopService(shopRepository, shopAssembler, shopsTranslator);
    }

    @Test
    public void should_return_shops() {
        Page<ShopEntity> shopEntityPage = new PageImpl<>(new ArrayList<>());
        PagedResources<Resource<Shop>> resources = shopPagedResources(
                Arrays.asList(shopResource("code1", "name1"), shopResource("code2", "name2"))
        );

        when(shopRepository.findAll(any(Pageable.class))).thenReturn(shopEntityPage);
        when(pagedResourcesAssembler.toResource(shopEntityPage, shopAssembler)).thenReturn(resources);

        Shops shops = shopService.retrieveAllShops(new PageRequest(10, 1), pagedResourcesAssembler);

        assertEquals("name1", shops.getShops().get(0).getName());
    }
}