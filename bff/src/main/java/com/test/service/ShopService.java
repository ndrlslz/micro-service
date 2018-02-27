package com.test.service;

import com.test.model.Shop;

public interface ShopService {
    Shop retrieveShop(String shopId) throws Exception;
}
