package com.example.gpslocation.location.web;

import com.example.gpslocation.location.db.LocationRepository;
import com.example.gpslocation.location.entity.Location;
import com.example.gpslocation.location.web.LocationController.RestLocationCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class LocationControllerIT {

    @Autowired
    LocationController controller;

    @Autowired
    LocationRepository repository;

    @Test
    void createLocation() {
        //GIVEN
        RestLocationCommand command = givenRestLocation();

        //WHEN
        final ResponseEntity<?> location = controller.createLocation(command);
        URI uri = location.getHeaders().getLocation();
        Long id = getLocationId(uri);
        Optional<Location> locationFromDb = repository.findById(id);

        //THEN
        assertEquals(1L, id);
        assertTrue(locationFromDb.isPresent());
        assertEquals(id, locationFromDb.get().getId());
        assertEquals(command.getDeviceId(), locationFromDb.get().getDeviceId());
        assertEquals(command.getLatitude(), locationFromDb.get().getLatitude());
        assertEquals(command.getLongitude(), locationFromDb.get().getLongitude());
        assertNotNull(locationFromDb.get().getCreatedAt());
    }

    private Long getLocationId(URI uri) {
        String path = uri.getPath();
        String idString = path.substring(path.lastIndexOf('/') + 1);
        Long id = Long.parseLong(idString);
        return id;
    }

    private RestLocationCommand givenRestLocation() {
        RestLocationCommand command = new RestLocationCommand();
        command.setDeviceId(12342L);
        command.setLatitude("781727631");
        command.setLongitude("781727631");
        return command;
    }
}