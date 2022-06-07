package com.example.gpslocation.location.application;

import com.example.gpslocation.location.application.port.CreateLocationUseCase;
import com.example.gpslocation.location.application.port.CreateLocationUseCase.CreateLocationCommand;
import com.example.gpslocation.location.db.LocationRepository;
import com.example.gpslocation.location.entity.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CreateLocationServiceTest {

    @Autowired
    private CreateLocationUseCase service;
    @Autowired
    private LocationRepository repository;

    @Test
    void createNewLocation() {
        //GIVEN
        CreateLocationCommand location = givenLocation();

        //THEN
        Long locationId = service.createNewLocation(location);

        //WHEN
        Optional<Location> foundLocation = repository.findById(locationId);

        if (foundLocation.isPresent()) {
            assertEquals(location.getDeviceId(), foundLocation.get().getDeviceId());
            assertEquals(location.getLongitude(), foundLocation.get().getLongitude());
            assertEquals(location.getLatitude(), foundLocation.get().getLatitude());
        }
    }

    private CreateLocationCommand givenLocation() {
        return new CreateLocationCommand(123412L, 213324L, 54314L);
    }
}