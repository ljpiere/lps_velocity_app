package com.velocity.back.bike;

import com.velocity.back.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Long> {
    List<Bike> findByOwner(AppUser owner);
}
