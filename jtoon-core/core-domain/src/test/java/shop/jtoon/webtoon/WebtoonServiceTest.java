package shop.jtoon.webtoon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import shop.jtoon.member.entity.Gender;
import shop.jtoon.member.entity.LoginType;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.entity.Role;
import shop.jtoon.member.repository.MemberRepository;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.entity.enums.AgeLimit;
import shop.jtoon.webtoon.repository.DayOfWeekWebtoonRepository;
import shop.jtoon.webtoon.repository.GenreWebtoonRepository;
import shop.jtoon.webtoon.repository.WebtoonRepository;
import shop.jtoon.webtoon.repository.WebtoonWriter;
import shop.jtoon.webtoon.service.WebtoonDomainService;

@EnableJpaAuditing
@DataJpaTest
class WebtoonServiceTest {

	@Autowired
	WebtoonRepository webtoonRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	DayOfWeekWebtoonRepository dayOfWeekWebtoonRepository;

	@Autowired
	GenreWebtoonRepository genreWebtoonRepository;

	WebtoonDomainService webtoonDomainService;

	@BeforeEach
	void init() {
		WebtoonWriter webtoonWriter = new WebtoonWriter(webtoonRepository, dayOfWeekWebtoonRepository,
			genreWebtoonRepository);
		webtoonDomainService = new WebtoonDomainService(null, null, webtoonWriter, null);
	}

	@Test
	void update_bulk_query() {

		List<Member> members = new ArrayList<>();
		List<Webtoon> webtoons = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			members.add(memberFixture());
			webtoons.add(webtoonFixture(members.get(i)));
		}

		memberRepository.saveAll(members);
		memberRepository.flush();
		webtoonRepository.saveAll(webtoons);
		webtoonRepository.flush();

		webtoonDomainService.updateWebtoonStatus(webtoons.stream().map(Webtoon::getId).toList());
		webtoonRepository.flush();

	}

	Webtoon webtoonFixture(Member member) {
		return Webtoon.builder()
			.title(UUID.randomUUID().toString().substring(0, 10))
			.description("description")
			.ageLimit(AgeLimit.AGE_12)
			.thumbnailUrl("thumbnailUrl")
			.cookieCount(4)
			.author(member)
			.build();
	}

	Member memberFixture() {
		return Member.builder()
			.name("n")
			.role(Role.USER)
			.phone(UUID.randomUUID().toString().substring(0, 10))
			.email(UUID.randomUUID().toString().substring(0, 10))
			.nickname(UUID.randomUUID().toString().substring(0, 10))
			.loginType(LoginType.KAKAO)
			.password("1234")
			.gender(Gender.MALE)
			.build();
	}
}
