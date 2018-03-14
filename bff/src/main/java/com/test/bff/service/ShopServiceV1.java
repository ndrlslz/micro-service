package com.test.bff.service;

import com.test.bff.dao.OrderDao;
import com.test.bff.dao.VehicleDao;
import com.test.bff.exception.DaoException;
import com.test.bff.exception.DaoRuntimeException;
import com.test.bff.model.Orders;
import com.test.bff.model.Shop;
import com.test.bff.model.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ShopServiceV1 implements ShopService {
    private VehicleDao vehicleDaoV1;
    private OrderDao orderDaoV1;
    private Tracer tracer;
    private TraceKeys traceKeys;
    private SpanNamer spanNamer;

    @Autowired
    public ShopServiceV1(@Qualifier("vehicleDaoV1") VehicleDao vehicleDaoV1,
                         @Qualifier("orderDaoV1") OrderDao orderDaoV1,
                         Tracer tracer,
                         TraceKeys traceKeys,
                         SpanNamer spanNamer) {
        this.vehicleDaoV1 = vehicleDaoV1;
        this.orderDaoV1 = orderDaoV1;
        this.tracer = tracer;
        this.traceKeys = traceKeys;
        this.spanNamer = spanNamer;
    }

    public Shop retrieveShop(String shopId) throws Exception {
        Callable<Vehicles> callable = () -> vehicleDaoV1.retrieveVehiclesForShop(shopId);
        Callable<Orders> ordersCallable = () -> orderDaoV1.retrieveOrdersForShop(shopId);
        TraceableExecutorService traceableExecutorService = new TraceableExecutorService(
                Executors.newFixedThreadPool(2), tracer, traceKeys, spanNamer);
        Future<Vehicles> vehiclesFuture = traceableExecutorService.submit(callable);
        Future<Orders> ordersFuture = traceableExecutorService.submit(ordersCallable);

        Shop shop = new Shop();
        try {
            shop.setOrders(ordersFuture.get().getOrders());
            shop.setVehicles(vehiclesFuture.get().getVehicles());
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DaoException) {
                throw new DaoRuntimeException((DaoException) e.getCause());
            } else {
                throw e;
            }
        }

        return shop;
    }
}
