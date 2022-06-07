package com.example.gpslocation.location.web;

import com.example.gpslocation.location.application.port.CreateLocationUseCase;
import com.example.gpslocation.location.application.port.CreateLocationUseCase.CreateLocationCommand;
import com.example.gpslocation.location.entity.Location;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

    private final CreateLocationUseCase service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<?> createLocation(@Valid @RequestBody RestLocationCommand command) {
        Location location = service.createNewLocation(command.toCreateLocationCommand());
        return ResponseEntity.created(toUri(location.getId())).build();
    }

    private URI toUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + id.toString()).build().toUri();
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class RestLocationCommand {
        @NotNull(message = "Please provide valid device id")
        @DecimalMin("0.00")
        Long deviceId;
        @NotBlank(message = "Please provide valid latitude")
        @Size(min = 9, message = "Latitude must contain minimum 9 digits")
        String latitude;
        @NotBlank(message = "Please provide valid longitude")
        @Size(min = 9, message = "Longitude  must contain minimum 9 digits")
        String longitude;

        CreateLocationCommand toCreateLocationCommand() {
            return new CreateLocationCommand(deviceId, latitude, longitude);
        }
    }
}
