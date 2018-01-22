package com.test.dao;

import com.test.exception.DaoException;
import com.test.model.Orders;

public interface OrderDao {
    Orders retrieveOrdersForShop(String shopId) throws DaoException;
}
