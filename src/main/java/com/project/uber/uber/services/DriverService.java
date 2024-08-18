package com.project.uber.uber.services;

import com.project.uber.uber.dto.DriverDto;
import com.project.uber.uber.dto.RideDto;
import com.project.uber.uber.dto.RiderDto;
import com.project.uber.uber.entities.Driver;

import java.util.List;

public interface DriverService {
    RideDto acceptRide(Long rideId);
    RideDto cancelRide(Long rideId);
    RideDto endRide(Long rideId);
    RideDto startRide(Long rideId, String otp);
    RiderDto rateRider(Long rideId, Integer rating);
    DriverDto getMyProfile();
    List<RiderDto> getMyAllRides();
    Driver getCurrentDriver();
}
