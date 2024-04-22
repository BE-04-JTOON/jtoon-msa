package shop.jtoon.event.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import shop.jtoon.event.entity.Event;
import shop.jtoon.event.entity.EventStatus;

@Getter
public class ImagePublish {
	private Long id;
	private LocalDateTime publishDate;
	private ImagePayload imagePayload;
	private EventStatus eventStatus;

	@Builder
	public ImagePublish(ImagePayload imagePayload, EventStatus eventStatus, Long id, LocalDateTime publishDate) {
		this.id = id;
		this.imagePayload = imagePayload;
		this.eventStatus = eventStatus;
		this.publishDate = publishDate;
	}

	public static Event toEvent(ImagePublish imagePublish) {
		return Event.builder()
			.id(imagePublish.id)
			.status(imagePublish.eventStatus)
			.payload(imagePublish.imagePayload)
			.build();
	}

	public static List<ImagePublish> toImagePublishes(List<Event> events) {
		return events.stream()
			.map(event -> ImagePublish.builder()
				.id(event.getId())
				.eventStatus(event.getStatus())
				.imagePayload(event.getPayload())
				.publishDate(event.getCreatedAt())
				.build())
			.toList();
	}

	public void updateStatus() {
		this.eventStatus = EventStatus.OK;
	}
}
