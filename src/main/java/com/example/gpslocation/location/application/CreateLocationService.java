package com.example.gpslocation.location.application;

import com.example.gpslocation.location.application.port.CreateLocationUseCase;
import com.example.gpslocation.location.db.LocationRepository;
import com.example.gpslocation.location.entity.Location;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class CreateLocationService implements CreateLocationUseCase {

    private final LocationRepository repository;

    @Override
    public Long createNewLocation(CreateLocationCommand command) {
        Location location = new Location(command.getDeviceId(), command.getLatitude(), command.getLongitude());
        Location saved = repository.save(location);
        log.info("Saved new location with id: " + saved.getId());
        return saved.getId();
    }
}
