package shop.jtoon.webtoon.request;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import shop.jtoon.common.FileName;
import shop.jtoon.common.ImageType;
import shop.jtoon.webtoon.domain.WebtoonDayOfWeeks;
import shop.jtoon.webtoon.domain.WebtoonGenres;
import shop.jtoon.webtoon.domain.WebtoonInfo;
import shop.jtoon.webtoon.entity.enums.AgeLimit;
import shop.jtoon.webtoon.entity.enums.DayOfWeek;
import shop.jtoon.webtoon.entity.enums.Genre;

@Builder
public record CreateWebtoonReq(
	@NotBlank String title,
	@NotBlank String description,
	@NotNull Set<DayOfWeek> dayOfWeeks,
	@NotNull Set<Genre> genres,
	@NotNull AgeLimit ageLimit,
	@Min(0) int cookieCount
) {

	public WebtoonInfo toWebtoonInfo(String thumbnailUrl) {
		return WebtoonInfo.builder()
			.title(title)
			.description(description)
			.ageLimit(ageLimit)
			.thumbnailUrl(thumbnailUrl)
			.cookieCount(cookieCount)
			.build();
	}

	public WebtoonGenres toWebtoonGenres() {
		return WebtoonGenres.builder().genres(genres).build();
	}

	public WebtoonDayOfWeeks toWebtoonDayOfWeeks() {
		return WebtoonDayOfWeeks.builder().dayOfWeeks(dayOfWeeks).build();
	}


	public UploadImageDto toUploadImageDto(ImageType imageType, MultipartFile image) {
		return UploadImageDto.builder()
			.imageType(imageType)
			.webtoonTitle(title)
			.fileName(FileName.forWebtoon())
			.image(image)
			.build();
	}
}
