package com.test.db;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.UUID;

public class DBHelper {
    private static final String CREATE_SHOP = "" +
            "INSERT INTO " +
            "shop (id, code, name) " +
            "VALUES (?, ?, ?)";
    private static final String CREATE_ORDER = "" +
            "INSERT INTO " +
            "vehicle_order (ID, vehicle, price, shop_id, shop_code, CREATED_AT) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    public static JdbcTemplate jdbcTemplate;

    public static void createShop(String shopName, String codeName) {
        jdbcTemplate.update(CREATE_SHOP, UUID.randomUUID().toString(), codeName, shopName);
    }

    public static void createOrder(String vehicle, String price, String shopId, String shopCode) {
        jdbcTemplate.update(CREATE_ORDER, UUID.randomUUID().toString(), vehicle, price, shopId, shopCode, new Timestamp(System.currentTimeMillis()));
    }
}
