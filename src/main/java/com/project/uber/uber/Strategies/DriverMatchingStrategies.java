package com.project.uber.uber.Strategies;

import com.project.uber.uber.entities.Driver;
import com.project.uber.uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategies {
    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
