package shop.jtoon.webtoon.request;

import java.time.LocalDateTime;

import lombok.Builder;
import shop.jtoon.dto.ImagePublishData;
import shop.jtoon.dto.ImageUpload;
import shop.jtoon.event.domain.ImagePayload;
import shop.jtoon.event.domain.ImagePublish;
import shop.jtoon.event.entity.EventStatus;

@Builder
public record ImageEvent(
	String key,
	byte[] data
) {

	public static ImageEvent toImageEvent(ImagePayload imagePayload) {
		return ImageEvent.builder()
			.key(imagePayload.key())
			.data(imagePayload.data())
			.build();
	}

	public static ImageEvent toImageEvent(ImagePublishData imagePublishData) {
		return ImageEvent.builder()
			.key(imagePublishData.key())
			.data(imagePublishData.data())
			.build();
	}

	public ImageUpload toImageUpload() {
		return ImageUpload.builder()
			.key(key)
			.data(data)
			.build();
	}

	public ImagePayload toImagePayload() {
		return ImagePayload.builder()
			.key(key)
			.data(data)
			.build();
	}

	public ImagePublish toImagePublish() {
		return ImagePublish.builder()
			.eventStatus(EventStatus.READY)
			.imagePayload(this.toImagePayload())
			.publishDate(LocalDateTime.now())
			.build();
	}

	public ImagePublishData imagePublishData(Long id) {
		return ImagePublishData.builder()
			.id(id)
			.key(key)
			.data(data)
			.build();
	}
}
