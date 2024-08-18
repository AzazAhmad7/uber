package com.project.uber.uber.services.Impl;

import com.project.uber.uber.Strategies.StrategyManager;
import com.project.uber.uber.dto.DriverDto;
import com.project.uber.uber.dto.RideDto;
import com.project.uber.uber.dto.RideRequestDto;
import com.project.uber.uber.dto.RiderDto;
import com.project.uber.uber.entities.Driver;
import com.project.uber.uber.entities.RideRequest;
import com.project.uber.uber.entities.Rider;
import com.project.uber.uber.entities.User;
import com.project.uber.uber.entities.enums.RideRequestStatus;
import com.project.uber.uber.exceptions.ResourceNotFoundException;
import com.project.uber.uber.repositories.RideRequestRepository;
import com.project.uber.uber.repositories.RiderRepository;
import com.project.uber.uber.services.RiderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final StrategyManager strategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        log.info("Ride request started");
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        Rider rider = getCurrentRider();
        log.info("Rider "+rider);

        double fare = strategyManager.rideFareCalculationStrategies().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        rideRequest.setRider(rider);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers = strategyManager.driverMatchingStrategies(rider.getRating()).findMatchingDrivers(rideRequest);
        //TODO send the notification to all the drivers about this ride request
        
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public List<RiderDto> getMyAllRides() {
        return List.of();
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public Rider createRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
       //TODO implement spring security
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found with id "+1));
    }
}
