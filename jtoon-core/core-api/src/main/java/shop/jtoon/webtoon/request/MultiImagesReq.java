package shop.jtoon.webtoon.request;

import static shop.jtoon.common.ImageType.*;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;

@Builder
public record MultiImagesReq(
	List<MultipartFile> mainImages
) {

	public List<ImageEvent> toMultiImageEvent(CreateEpisodeReq request, String webtoonTitle) {
		return mainImages.stream()
			.map(mainImage -> request.toUploadImageDto(EPISODE_MAIN, webtoonTitle, mainImage).toImageEvent())
			.toList();
	}
}
