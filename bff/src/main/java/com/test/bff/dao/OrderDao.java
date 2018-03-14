package com.test.bff.dao;

import com.test.bff.exception.DaoException;
import com.test.bff.model.Orders;

public interface OrderDao {
    Orders retrieveOrdersForShop(String shopId) throws DaoException;
}
