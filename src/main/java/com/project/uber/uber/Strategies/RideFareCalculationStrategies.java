package com.project.uber.uber.Strategies;

import com.project.uber.uber.entities.RideRequest;

public interface RideFareCalculationStrategies {
    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}
