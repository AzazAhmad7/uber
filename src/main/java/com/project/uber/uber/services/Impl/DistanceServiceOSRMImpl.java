package com.project.uber.uber.services.Impl;

import com.project.uber.uber.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    private static final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";
    @Override
    public Double calculateDistance(Point src, Point dest) {
        String uri = src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
        try {
            OSRMResponseDto osrmResponseDto = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);
            //call the third party api called OSRM to fetch the distance
            return osrmResponseDto.getRoutes().get(0).getDistance();
        }catch (Exception e){
            throw new RuntimeException("ERROR getting distance from OSRM "+e.getMessage());
        }
    }
}

@Data
class OSRMResponseDto{
    private List<OSRMRoutes> routes;
}
@Data
class OSRMRoutes{
    private Double distance;
}
