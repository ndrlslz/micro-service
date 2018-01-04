package com.test.controller;

import com.test.model.Shops;
import com.test.service.ShopService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShopControllerTest {
    @Mock
    private ShopService shopService;

    @InjectMocks
    private ShopController shopController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void should_return_all_shops() throws Exception {
        Pageable pageable = mock(Pageable.class);
        PagedResourcesAssembler assembler = mock(PagedResourcesAssembler.class);
        Shops expected = new Shops();

        when(shopService.retrieveAllShops(any(Pageable.class), any())).thenReturn(expected);

        assertEquals(shopController.listAllShops(pageable, assembler), expected);
    }

}