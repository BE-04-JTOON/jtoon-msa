package shop.jtoon.event;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.jtoon.event.domain.ImagePublish;
import shop.jtoon.event.service.EventDomainService;
import shop.jtoon.webtoon.application.WebtoonClientService;
import shop.jtoon.webtoon.request.ImageEvent;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventDomainService eventDomainService;
	private final WebtoonClientService webtoonClientService;

	@Scheduled(cron = "0/10 * * * * *")
	@Transactional
	public void publish() {
		LocalDateTime now = LocalDateTime.now();

		eventDomainService.readRecentEvent(now).stream()
			.peek(imagePublish ->
				webtoonClientService.upload(ImageEvent.toImageEvent(imagePublish.getImagePayload()).toImageUpload())
			)
			.peek(ImagePublish::updateStatus)
			.forEach(eventDomainService::updateEvent);
	}
}
