package shop.jtoon.webtoon.request;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import shop.jtoon.common.FileName;
import shop.jtoon.common.ImageType;
import shop.jtoon.dto.UploadImageDto;
import shop.jtoon.member.entity.Member;
import shop.jtoon.webtoon.entity.DayOfWeekWebtoon;
import shop.jtoon.webtoon.entity.GenreWebtoon;
import shop.jtoon.webtoon.entity.Webtoon;
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

	public Webtoon toWebtoonEntity(Member member, String thumbnailUrl) {
		return Webtoon.builder()
			.title(title)
			.description(description)
			.ageLimit(ageLimit)
			.thumbnailUrl(thumbnailUrl)
			.cookieCount(cookieCount)
			.author(member)
			.build();
	}

	public List<DayOfWeekWebtoon> toDayOfWeekWebtoonEntity(Webtoon webtoon) {
		return dayOfWeeks.stream()
			.map(dayOfWeek -> DayOfWeekWebtoon.create(dayOfWeek, webtoon))
			.toList();
	}

	public List<GenreWebtoon> toGenreWebtoonEntity(Webtoon webtoon) {
		return genres.stream()
			.map(genre -> GenreWebtoon.create(genre, webtoon))
			.toList();
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
