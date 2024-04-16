package shop.jtoon.webtoon.domain;

import lombok.Builder;
import shop.jtoon.member.entity.Member;

@Builder
public record Author(
	Long id,
	String nickname
) {

	public static Author from(Member author) {
		return Author.builder()
			.id(author.getId())
			.nickname(author.getNickname())
			.build();
	}
}
