package com.test.bff.service;

import com.test.bff.model.Shop;

public interface ShopService {
    Shop retrieveShop(String shopId) throws Exception;
}
