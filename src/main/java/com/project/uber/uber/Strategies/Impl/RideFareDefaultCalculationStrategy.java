package com.project.uber.uber.Strategies.Impl;

import com.project.uber.uber.Strategies.RideFareCalculationStrategies;
import com.project.uber.uber.entities.RideRequest;
import com.project.uber.uber.services.DistanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RideFareDefaultCalculationStrategy implements RideFareCalculationStrategies {
    private final DistanceService distanceService;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        log.info("calculating fare for {}", rideRequest);
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDestinationLocation());
        log.info("distance: {}", distance);
        return (distance/1000)*RIDE_FARE_MULTIPLIER;
    }
}
