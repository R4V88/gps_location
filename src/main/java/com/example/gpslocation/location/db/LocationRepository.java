package com.example.gpslocation.location.db;

import com.example.gpslocation.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
