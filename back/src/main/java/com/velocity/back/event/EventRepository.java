package com.velocity.back.event;

import com.velocity.back.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOwner(AppUser owner);
}
