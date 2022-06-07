package com.example.gpslocation.location.application.port;

import com.example.gpslocation.location.entity.Location;
import lombok.Value;

public interface CreateLocationUseCase {

    Location createNewLocation(CreateLocationCommand command);

    @Value
    class CreateLocationCommand {
        Long deviceId;
        String latitude;
        String longitude;
    }
}
