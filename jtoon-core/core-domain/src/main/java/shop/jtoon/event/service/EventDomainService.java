package shop.jtoon.event.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.jtoon.event.domain.ImagePublish;
import shop.jtoon.event.repository.EventReader;
import shop.jtoon.event.repository.EventWriter;

@Service
@RequiredArgsConstructor
public class EventDomainService {

	private final EventWriter eventWriter;
	private final EventReader eventReader;

	public List<ImagePublish> readRecentEvent(LocalDateTime now) {
		return ImagePublish.toImagePublishes(eventReader.readRecentEvent(now));
	}

	@Transactional
	public void update(List<ImagePublish> publishes) {
		eventWriter.writeEvnets(publishes);
	}
}
