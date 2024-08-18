package com.project.uber.uber.Strategies.Impl;

import com.project.uber.uber.Strategies.RideFareCalculationStrategies;
import com.project.uber.uber.entities.RideRequest;
import com.project.uber.uber.services.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareSurgePricingCalculationStrategy implements RideFareCalculationStrategies {
    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDestinationLocation());
        return (distance/1000)*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
