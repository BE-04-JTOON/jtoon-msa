package shop.jtoon.webtoon.response;

import lombok.Builder;
import shop.jtoon.member.entity.Member;
import shop.jtoon.webtoon.domain.Author;

@Builder
public record AuthorRes(
	Long id,
	String nickname
) {

	public static AuthorRes from(Author author) {
		return AuthorRes.builder()
			.id(author.id())
			.nickname(author.nickname())
			.build();
	}
}
