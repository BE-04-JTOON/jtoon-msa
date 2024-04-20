package shop.jtoon.event.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.event.entity.Event;

@Repository
@RequiredArgsConstructor
public class EventWriter {

	private final EventRepository eventRepository;

	public void write(Event event) {
		eventRepository.save(event);
	}
}
