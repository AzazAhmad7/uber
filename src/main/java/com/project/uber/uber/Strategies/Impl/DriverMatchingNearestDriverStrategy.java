package com.project.uber.uber.Strategies.Impl;

import com.project.uber.uber.Strategies.DriverMatchingStrategies;
import com.project.uber.uber.entities.Driver;
import com.project.uber.uber.entities.RideRequest;
import com.project.uber.uber.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategies {
    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
       return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
