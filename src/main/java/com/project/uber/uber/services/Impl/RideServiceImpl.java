package com.project.uber.uber.services.Impl;

import com.project.uber.uber.dto.RideRequestDto;
import com.project.uber.uber.entities.Driver;
import com.project.uber.uber.entities.Ride;
import com.project.uber.uber.entities.RideRequest;
import com.project.uber.uber.entities.enums.RideRequestStatus;
import com.project.uber.uber.entities.enums.RideStatus;
import com.project.uber.uber.exceptions.ResourceNotFoundException;
import com.project.uber.uber.repositories.RideRepository;
import com.project.uber.uber.services.RideRequestService;
import com.project.uber.uber.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(()-> new ResourceNotFoundException("Ride not found with id: " + rideId));
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {

        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);
        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    private String generateRandomOTP(){
        Random random = new Random();
        int otpInt = random.nextInt(10000); //0 - 9999
        return String.format("%04d",otpInt);
    }
}
