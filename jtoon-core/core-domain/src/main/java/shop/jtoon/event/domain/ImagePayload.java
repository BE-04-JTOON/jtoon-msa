package shop.jtoon.event.domain;

import lombok.Builder;
import shop.jtoon.event.entity.Event;
import shop.jtoon.event.entity.EventStatus;

@Builder
public record ImagePayload(
	String key,
	byte[] data
) {

	public Event toEvent() {
		return Event.builder()
			.status(EventStatus.READY)
			.payload(this)
			.build();
	}
}
