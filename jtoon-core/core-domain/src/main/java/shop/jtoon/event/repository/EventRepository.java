package shop.jtoon.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.jtoon.event.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
