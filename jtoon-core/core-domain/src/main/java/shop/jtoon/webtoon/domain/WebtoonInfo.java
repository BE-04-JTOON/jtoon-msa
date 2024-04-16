package shop.jtoon.webtoon.domain;

import lombok.Builder;
import shop.jtoon.member.entity.Member;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.entity.enums.AgeLimit;

@Builder
public record WebtoonInfo(
	String title,
	String description,
	String thumbnailUrl,
	AgeLimit ageLimit,
	int cookieCount
) {
	public Webtoon toWebtoonEntity(Member member) {
		return Webtoon.builder()
			.title(title)
			.description(description)
			.ageLimit(ageLimit)
			.thumbnailUrl(thumbnailUrl)
			.cookieCount(cookieCount)
			.author(member)
			.build();
	}
}
