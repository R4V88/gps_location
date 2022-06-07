package com.example.gpslocation.location.application.port;

import lombok.Value;

public interface CreateLocationUseCase {

    Long createNewLocation(CreateLocationCommand command);

    @Value
    class CreateLocationCommand {
        Long latitude;
        Long longitude;
        Long deviceId;
    }
}
