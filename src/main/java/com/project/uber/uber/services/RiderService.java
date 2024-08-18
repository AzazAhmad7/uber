package com.project.uber.uber.services;

import com.project.uber.uber.dto.DriverDto;
import com.project.uber.uber.dto.RideDto;
import com.project.uber.uber.dto.RideRequestDto;
import com.project.uber.uber.dto.RiderDto;
import com.project.uber.uber.entities.Rider;
import com.project.uber.uber.entities.User;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    RideDto cancelRide(Long rideId);
    DriverDto rateDriver(Long rideId, Integer rating);
    List<RiderDto> getMyAllRides();
    RiderDto getMyProfile();
    Rider createRider(User user);
    Rider getCurrentRider();
}
