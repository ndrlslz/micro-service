package com.test.bff.dao;

import com.test.bff.exception.DaoException;
import com.test.bff.model.Vehicles;

public interface VehicleDao {
    Vehicles retrieveVehiclesForShop(String shopId) throws DaoException;
}
