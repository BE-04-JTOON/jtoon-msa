package shop.jtoon.service;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImagePublishData;
import shop.jtoon.repository.ListRedisRepository;

@Service
@RequiredArgsConstructor
public class EventRedisService {

	private static final String EVENT_KEY = "EVENT";
	private static final int EXPIRE_MINUTES = 1;

	private final ListRedisRepository listRedisRepository;

	public void publish(ImagePublishData imagePublishData) {
		listRedisRepository.push(EVENT_KEY, imagePublishData, Duration.ofMinutes(EXPIRE_MINUTES));
	}

	public List<ImagePublishData> consume() {
		return listRedisRepository.pop(EVENT_KEY, listRedisRepository.size(EVENT_KEY))
			.stream()
			.map(ImagePublishData.class::cast)
			.toList();
	}
}
