package com.test.service;

import com.test.entity.ShopEntity;
import com.test.model.Shops;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

public interface ShopService {
    Shops retrieveAllShops(Pageable pageable, PagedResourcesAssembler<ShopEntity> assembler);
}
