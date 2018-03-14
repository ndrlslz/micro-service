package com.test.order.service;

import com.test.order.entity.ShopEntity;
import com.test.order.model.Shops;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

public interface ShopService {
    Shops retrieveAllShops(Pageable pageable, PagedResourcesAssembler<ShopEntity> assembler);
}
