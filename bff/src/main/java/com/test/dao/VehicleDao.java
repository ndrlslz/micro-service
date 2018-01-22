package com.test.dao;

import com.test.exception.DaoException;
import com.test.model.Vehicles;

public interface VehicleDao {
    Vehicles retrieveVehiclesForShop(String shopId) throws DaoException;
}
