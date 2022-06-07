package com.example.gpslocation.location.application.port;

import lombok.Value;

public interface CreateLocationUseCase {

    void createNewLocation(CreateLocationCommand command);

    @Value
    class CreateLocationCommand {
        Long latitude;
        Long longitude;
        Long deviceId;
    }
}
