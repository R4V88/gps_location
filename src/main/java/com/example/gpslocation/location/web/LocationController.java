package com.example.gpslocation.location.web;

import com.example.gpslocation.location.application.port.CreateLocationUseCase;
import com.example.gpslocation.location.application.port.CreateLocationUseCase.CreateLocationCommand;
import com.example.gpslocation.location.entity.Location;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

    private final CreateLocationUseCase service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Long createLocation(@Valid @RequestBody RestLocationCommand command) {
        Location location = service.createNewLocation(command.toCreateLocationCommand());
        return location.getId();
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class RestLocationCommand {
        @NotNull
        Long deviceId;
        @NotNull
        Long latitude;
        @NotNull
        Long longitude;

        CreateLocationCommand toCreateLocationCommand() {
            return new CreateLocationCommand(deviceId, latitude, longitude);
        }
    }
}
