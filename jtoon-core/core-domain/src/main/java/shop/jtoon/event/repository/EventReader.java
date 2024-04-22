package shop.jtoon.event.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.event.entity.Event;

@Repository
@RequiredArgsConstructor
public class EventReader {

	private final EventSearchRepository eventSearchRepository;

	public List<Event> readRecentEvent(LocalDateTime now) {
		return eventSearchRepository.findByCreateAtBefore(now);
	}
}
