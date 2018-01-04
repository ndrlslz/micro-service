package com.test.service;

import com.test.model.Shops;
import com.test.entity.ShopEntity;
import com.test.model.Shop;
import com.test.repository.ShopRepository;
import com.test.translator.ShopsTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

@Service
public class DefaultShopService implements ShopService {
    private ShopRepository shopRepository;
    private ShopAssembler shopAssembler;
    private ShopsTranslator shopsTranslator;

    @Autowired
    public DefaultShopService(ShopRepository shopRepository,
                              ShopAssembler shopAssembler,
                              ShopsTranslator shopsTranslator) {
        this.shopRepository = shopRepository;
        this.shopAssembler = shopAssembler;
        this.shopsTranslator = shopsTranslator;
    }

    @Override
    public Shops retrieveAllShops(Pageable pageable, PagedResourcesAssembler<ShopEntity> assembler) {
        Page<ShopEntity> shopEntities = shopRepository.findAll(pageable);
        PagedResources<Resource<Shop>> shopResources = assembler.toResource(shopEntities, shopAssembler);
        return shopsTranslator.translate(shopResources);
    }
}
