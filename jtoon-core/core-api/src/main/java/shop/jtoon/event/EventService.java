package shop.jtoon.event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImagePublishData;
import shop.jtoon.event.domain.ImagePublish;
import shop.jtoon.event.service.EventDomainService;
import shop.jtoon.service.EventRedisService;
import shop.jtoon.webtoon.application.WebtoonClientService;
import shop.jtoon.webtoon.request.ImageEvent;
import shop.jtoon.webtoon.service.WebtoonDomainService;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventDomainService eventDomainService;
	private final WebtoonClientService webtoonClientService;
	private final EventRedisService eventRedisService;
	private final WebtoonDomainService webtoonDomainService;

	@Scheduled(cron = "0/10 * * * * *")
	@Transactional
	public void publish() {
		LocalDateTime now = LocalDateTime.now();

		List<ImagePublish> publishes = eventDomainService.readRecentEvent(now).stream()
			.map(imagePublish -> {
					webtoonClientService.upload(ImageEvent.toImageEvent(imagePublish.getImagePayload()).toImageUpload());
					imagePublish.updateStatus();

					return imagePublish;
				})
			.toList();

		eventDomainService.update(publishes);
	}

	@Scheduled(cron = "0/10 * * * * *")
	@Transactional
	public void eventExecute() {
		List<Long> webtoonIds = new ArrayList<>();
		List<ImagePublish> publishes = eventRedisService.consume().stream()
			.parallel()
			.map(imagePublishData -> updateEvent(imagePublishData, webtoonIds))
			.filter(Objects::nonNull)
			.map(ImageEvent::toImagePublish)
			.toList();

		eventDomainService.update(publishes);
		webtoonDomainService.updateWebtoonStatus(webtoonIds);
	}

	private ImageEvent updateEvent(ImagePublishData imagePublishData, List<Long> wetoonIds) {
		ImageEvent imageEvent = ImageEvent.toImageEvent(imagePublishData);

		try {
			webtoonClientService.upload(imageEvent.toImageUpload());
			wetoonIds.add(imagePublishData.id());

			return null;
		} catch (Exception e) {
			return imageEvent;
		}
	}
}
