package shop.jtoon.webtoon.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.repository.MemberReader;
import shop.jtoon.payment.service.CookieManager;
import shop.jtoon.webtoon.domain.EpisodeMainInfo;
import shop.jtoon.webtoon.domain.EpisodeSchema;
import shop.jtoon.webtoon.domain.SearchEpisode;
import shop.jtoon.webtoon.entity.Episode;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.repository.EpisodeReader;
import shop.jtoon.webtoon.repository.EpisodeWriter;
import shop.jtoon.webtoon.repository.WebtoonReader;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EpisodeDomainService {

	private final WebtoonManger webtoonManger;
	private final CookieManager cookieManager;

	private final WebtoonReader webtoonReader;
	private final EpisodeReader episodeReader;
	private final MemberReader memberReader;
	private final EpisodeWriter episodeWriter;


	public Webtoon readWebtoon(Long webtoonId, Long memberId, int no) {
		Webtoon webtoon = webtoonReader.read(webtoonId);
		webtoonManger.validationWebtoon(webtoon, memberId, no);

		return webtoon;
	}

	public void createEpisode(EpisodeSchema episodeSchema,Webtoon webtoon, String mainUrl, String thumbnailUrl) {
		Episode episode = episodeSchema.toEpisode(webtoon,mainUrl,thumbnailUrl);
		episodeWriter.write(episode);
	}

	public EpisodeMainInfo readEpisode(Long episodeId) {
		Episode episode = episodeReader.read(episodeId);

		return EpisodeMainInfo.of(episode);
	}

	@Transactional
	public void purchaseEpisode(Long memberId, Long episodeId) {
		Member member = memberReader.read(memberId);
		Episode episode = episodeReader.read(episodeId);
		cookieManager.useCookie(episode.getCookieCount(), member);
		webtoonManger.purchase(episode, member);
	}

	public List<SearchEpisode> readEpisodes(Long webtoonId, int size, Long offset) {
		return episodeWriter.readEpisodes(webtoonId, size, offset);
	}
}
