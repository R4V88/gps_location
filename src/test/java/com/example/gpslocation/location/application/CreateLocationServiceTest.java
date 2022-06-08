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
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        CreateLocationCommand command = givenLocationCommand();

        //WHEN
        Location saved = service.createNewLocation(command);
        final Long locationId = saved.getId();

        //THEN
        Optional<Location> foundLocation = repository.findById(locationId);

        assertEquals(command.getDeviceId(), foundLocation.get().getDeviceId());
        assertEquals(command.getLongitude(), foundLocation.get().getLongitude());
        assertEquals(command.getLatitude(), foundLocation.get().getLatitude());
        assertNotNull(foundLocation.get().getId());
        assertNotNull(foundLocation.get().getCreatedAt());
    }

    @Test
    void savedLocationShouldBeEqualToFound() {
        //GIVEN
        Location givenLocation = givenLocation();
        final Long locationId = givenLocation.getId();

        //WHEN
        Optional<Location> foundLocation = repository.findById(locationId);

        //THEN
        assertEquals(givenLocation.getDeviceId(), foundLocation.get().getDeviceId());
        assertEquals(givenLocation.getLongitude(), foundLocation.get().getLongitude());
        assertEquals(givenLocation.getLatitude(), foundLocation.get().getLatitude());
        assertNotNull(foundLocation.get().getId());
        assertNotNull(foundLocation.get().getCreatedAt());
    }

    @Test
    void tooLongLongitudeAndLatitudeWillBeShortened() {
        //GIVEN
        CreateLocationCommand command = new CreateLocationCommand(123412L, "21332433242352343", "543143333242341243");

        //WHEN
        final Location newLocation = service.createNewLocation(command);

        //THEN
        assertEquals("213324332", newLocation.getLatitude());
        assertEquals("543143333", newLocation.getLongitude());
    }

    private CreateLocationCommand givenLocationCommand() {
        return new CreateLocationCommand(123412L, "213324333", "543143333");
    }

    private Location givenLocation() {
        Location location = new Location(123412L, "213324333", "543143333");
        return repository.save(location);
    }
}