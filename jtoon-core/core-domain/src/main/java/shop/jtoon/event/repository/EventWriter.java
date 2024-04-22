package shop.jtoon.event.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.event.domain.ImagePublish;
import shop.jtoon.event.entity.Event;

@Repository
@RequiredArgsConstructor
public class EventWriter {

	private final EventRepository eventRepository;

	public void write(Event event) {
		eventRepository.save(event);
	}

	public void writeEvnets(List<ImagePublish> publishes) {
		eventRepository.saveAll(publishes.stream()
			.map(ImagePublish::toEvent)
			.toList());
	}
}
