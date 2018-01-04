package com.test.controller;

import com.test.entity.ShopEntity;
import com.test.model.Shops;
import com.test.service.ShopService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShopControllerTest {
    private final static PageRequest PAGE = new PageRequest(1, 10);

    @Mock
    private ShopService shopService;

    @Mock
    private PagedResourcesAssembler<ShopEntity> assembler;

    @InjectMocks
    private ShopController shopController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void should_return_all_shops() throws Exception {
        Shops expected = new Shops();

        when(shopService.retrieveAllShops(any(Pageable.class), any())).thenReturn(expected);

        Shops response = shopController.listAllShops(PAGE, assembler);

        assertEquals(expected, response);
    }
}