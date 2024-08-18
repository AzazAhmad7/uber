package com.project.uber.uber.controllers;

import com.project.uber.uber.dto.RideDto;
import com.project.uber.uber.dto.StartRideDto;
import com.project.uber.uber.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;
    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideId, @RequestBody StartRideDto startRideDto) {
        return ResponseEntity.ok(driverService.startRide(rideId, startRideDto.getOtp()));
    }

}
