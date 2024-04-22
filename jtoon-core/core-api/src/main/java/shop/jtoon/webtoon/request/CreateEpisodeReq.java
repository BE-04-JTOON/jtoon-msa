package shop.jtoon.webtoon.request;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import shop.jtoon.common.FileName;
import shop.jtoon.common.ImageType;
import shop.jtoon.webtoon.domain.EpisodeSchema;

@Builder
public record CreateEpisodeReq(
	@Min(1) int no,
	@NotBlank @Size(max = 30) String title,
	boolean hasComment,
	@NotNull LocalDateTime openedAt
) {

	public EpisodeSchema toEpisodeSchema() {
		return EpisodeSchema.builder()
			.no(no)
			.title(title)
			.hasComment(hasComment)
			.openedAt(openedAt)
			.build();
	}

	public UploadImageDto toUploadImageDto(ImageType imageType, String webtoonTitle, MultipartFile image) {
		return UploadImageDto.builder()
			.imageType(imageType)
			.webtoonTitle(webtoonTitle)
			.fileName(FileName.forEpisode(no))
			.image(image)
			.build();
	}
}
