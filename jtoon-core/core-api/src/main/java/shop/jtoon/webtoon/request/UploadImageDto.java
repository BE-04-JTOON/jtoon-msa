package shop.jtoon.webtoon.request;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import shop.jtoon.common.FileName;
import shop.jtoon.common.ImageType;
import shop.jtoon.exception.NotFoundException;
import shop.jtoon.type.ErrorStatus;

@Builder
public record UploadImageDto(
	ImageType imageType,
	String webtoonTitle,
	FileName fileName,
	MultipartFile image
) {

	public String toKey() {
		return imageType.getPath(webtoonTitle, fileName.getValue());
	}

	public ImageEvent toImageEvent() {
		try {
			return ImageEvent.builder()
				.key(toKey())
				.data(image.getBytes())
				.build();
		} catch (IOException exception) {
			throw new NotFoundException(ErrorStatus.DATA_NOT_FOUND);
		}
	}
}
