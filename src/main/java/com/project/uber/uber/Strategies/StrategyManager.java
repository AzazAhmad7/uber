package com.project.uber.uber.Strategies;

import com.project.uber.uber.Strategies.Impl.DriverMatchingHighestRatedDriverStrategy;
import com.project.uber.uber.Strategies.Impl.DriverMatchingNearestDriverStrategy;
import com.project.uber.uber.Strategies.Impl.RideFareDefaultCalculationStrategy;
import com.project.uber.uber.Strategies.Impl.RideFareSurgePricingCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class StrategyManager {

    public final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    public final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    public final RideFareSurgePricingCalculationStrategy surgePricingCalculationStrategy;
    public final RideFareDefaultCalculationStrategy defaultCalculationStrategy;

    public DriverMatchingStrategies driverMatchingStrategies(double riderRating) {
        if(riderRating > 4.8) {
            return highestRatedDriverStrategy;
        }else{
            return nearestDriverStrategy;
        }
    }
    public RideFareCalculationStrategies rideFareCalculationStrategies() {
        LocalTime surgeStartTime = LocalTime.of(18, 0);
        LocalTime surgeEndTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurge = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if(isSurge) {
            return surgePricingCalculationStrategy;
        }else{
            return defaultCalculationStrategy;
        }
    }
}
