package com.test.bdd.db;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.stream.Stream.of;

public class DBHelper {
    private static final String CREATE_SHOP = "" +
            "INSERT INTO " +
            "shop (id, code, name) " +
            "VALUES (?, ?, ?)";
    private static final String CREATE_ORDER = "" +
            "INSERT INTO " +
            "vehicle_order (ID, vehicle, price, shop_id, CREATED_AT) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static JdbcTemplate jdbcTemplate;

    static {
        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        Properties properties = new Properties();

        try {
            PropertySource<?> applicationYamlPropertySource = loader.load(
                    "properties", new ClassPathResource("application-bdd.yml"), null);// null indicated common properties for all profiles.
            Map source = ((MapPropertySource) applicationYamlPropertySource).getSource();
            properties.putAll(source);
        } catch (IOException e) {
            throw new RuntimeException("cannot load application-bdd.yml");
        }

        DataSource dataSource = DataSourceBuilder.create()
                .url(properties.getProperty("spring.datasource.url"))
                .driverClassName(properties.getProperty("spring.datasource.driverClassName"))
                .username(properties.getProperty("spring.datasource.username"))
                .password(properties.getProperty("spring.datasource.password"))
                .build();

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void cleanDB() {
        of("vehicle_order", "shop")
                .forEach(table -> jdbcTemplate.execute(format("DELETE FROM %s", table)));
    }

    public static void createShop(String shopName, String codeName) {
        createShop(shopName, codeName, UUID.randomUUID().toString());
    }

    public static void createShop(String shopName, String codeName, String shopId) {
        jdbcTemplate.update(CREATE_SHOP, shopId, codeName, shopName);
    }

    public static void createOrder(String vehicle, long price, String shopId) {
        jdbcTemplate.update(CREATE_ORDER, UUID.randomUUID().toString(), vehicle, price, shopId, new Timestamp(System.currentTimeMillis()));
    }
}
