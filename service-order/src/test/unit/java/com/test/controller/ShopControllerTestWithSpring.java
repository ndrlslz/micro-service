package com.test.controller;

import com.test.service.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.test.util.ShopBuilder.shops;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShopController.class)
@EnableSpringDataWebSupport
public class ShopControllerTestWithSpring {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShopService shopService;

    @Test
    public void name() throws Exception {
        when(shopService.retrieveAllShops(isA(Pageable.class), any())).thenReturn(shops("code1", "name1", "code2", "name2"));

        mvc.perform(get("/v1/shops"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.[0].code").value("code1"));
    }
}
