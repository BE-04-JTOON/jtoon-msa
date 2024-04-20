package shop.jtoon.webtoon.request;

import lombok.Builder;
import shop.jtoon.dto.ImageUpload;
import shop.jtoon.event.domain.ImagePayload;

@Builder
public record ImageEvent(
	String key,
	byte[] data
) {

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

	public static ImageEvent toImageEvent(ImagePayload imagePayload) {
		return ImageEvent.builder()
			.key(imagePayload.key())
			.data(imagePayload.data())
			.build();
	}
}
