package com.example.gpslocation.location.application;

import com.example.gpslocation.location.application.port.CreateLocationUseCase;
import com.example.gpslocation.location.db.LocationRepository;
import com.example.gpslocation.location.entity.Location;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
@Slf4j
public class CreateLocationService implements CreateLocationUseCase {

    private final LocationRepository repository;

    @Override
    public Location createNewLocation(CreateLocationCommand command) {
        createLocationCommandValidator(command);
        Location location = locationParser(command);
        Location saved = repository.save(location);
        log.info("Saved new location with id: " + saved.getId());
        return saved;
    }

    private Location locationParser(CreateLocationCommand command) {
        final String longitude = command.getLongitude().substring(0, 9);
        final String latitude = command.getLatitude().substring(0, 9);
        Long deviceId = command.getDeviceId();

        return new Location(deviceId, latitude, longitude);
    }

    private void createLocationCommandValidator(CreateLocationCommand command) {
        if (command.getDeviceId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please insert valid device id");
        }
        if (command.getDeviceId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "device id must be larger than 0");
        }
        if (command.getLatitude() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please insert valid latitude");
        }
        if (command.getLongitude() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please insert valid longitude");
        }
    }
}
