package com.test.service;

import com.test.dao.OrderDao;
import com.test.dao.VehicleDao;
import com.test.model.AsyncResult;
import com.test.model.Orders;
import com.test.model.Shop;
import com.test.model.Vehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

import static com.test.utils.CommonUtils.throwExceptionIfExceptionExists;
import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.runAsync;

@Service
public class ShopAsyncServiceV1 implements ShopService {
    private static final int THREAD_COUNT = 2;
    private VehicleDao vehicleDaoV1;
    private OrderDao orderDaoV1;
    private Tracer tracer;
    private TraceKeys traceKeys;
    private SpanNamer spanNamer;

    @Autowired
    public ShopAsyncServiceV1(@Qualifier("vehicleAsyncDaoV1") VehicleDao vehicleDaoV1,
                              @Qualifier("orderAsyncDaoV1") OrderDao orderDaoV1,
                              Tracer tracer,
                              TraceKeys traceKeys,
                              SpanNamer spanNamer) {
        this.vehicleDaoV1 = vehicleDaoV1;
        this.orderDaoV1 = orderDaoV1;
        this.tracer = tracer;
        this.traceKeys = traceKeys;
        this.spanNamer = spanNamer;
        this.vehicleDaoV1 = vehicleDaoV1;
        this.orderDaoV1 = orderDaoV1;
    }

    @Override
    public Shop retrieveShop(String shopId) throws Exception {
//        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        Shop shop = new Shop();
        AsyncResult<Vehicles> vehiclesAsyncResult = new AsyncResult<>();
        AsyncResult<Orders> ordersAsyncResult = new AsyncResult<>();

        TraceableExecutorService traceableExecutorService = new TraceableExecutorService(
                Executors.newFixedThreadPool(THREAD_COUNT), tracer, traceKeys, spanNamer);

        allOf(
                async(vehiclesAsyncResult, () -> vehicleDaoV1.retrieveVehiclesForShop(shopId), traceableExecutorService),
                async(ordersAsyncResult, () -> orderDaoV1.retrieveOrdersForShop(shopId), traceableExecutorService))
                .join();

//        async(vehiclesAsyncResult, countDownLatch, () -> vehicleDaoV1.retrieveVehiclesForShop(shopId), traceableExecutorService);
//        async(ordersAsyncResult, countDownLatch, () -> orderDaoV1.retrieveOrdersForShop(shopId), traceableExecutorService);
//
//        countDownLatch.await();

        throwExceptionIfExceptionExists(vehiclesAsyncResult.getException());
        throwExceptionIfExceptionExists(ordersAsyncResult.getException());

        shop.setOrders(ordersAsyncResult.getResult().getOrders());
        shop.setVehicles(vehiclesAsyncResult.getResult().getVehicles());
        return shop;
    }

    public static <E> CompletableFuture<Void> async(AsyncResult<E> asyncResult, Callable<E> callable, Executor executor) {
        return runAsync(() -> {
            try {
                asyncResult.setResult(callable.call());
            } catch (Exception e) {
                asyncResult.setException(e);
            }
        }, executor);
    }
//    public static <E> void async(AsyncResult<E> asyncResult, CountDownLatch countDownLatch, Callable<E> callable, Executor executor) {
//        runAsync(() -> {
//            try {
//                asyncResult.setResult(callable.call());
//            } catch (Exception e) {
//                asyncResult.setException(e);
//            } finally {
//                countDownLatch.countDown();
//            }
//        }, executor);
//    }

}
