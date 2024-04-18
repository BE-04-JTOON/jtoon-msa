package shop.jtoon.webtoon.service;

import static shop.jtoon.type.ErrorStatus.*;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.exception.DuplicatedException;
import shop.jtoon.member.entity.Member;
import shop.jtoon.webtoon.domain.EpisodeSchema;
import shop.jtoon.webtoon.entity.Episode;
import shop.jtoon.webtoon.entity.PurchasedEpisode;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.repository.EpisodeReader;
import shop.jtoon.webtoon.repository.EpisodeWriter;
import shop.jtoon.webtoon.repository.WebtoonReader;

@Service
@RequiredArgsConstructor
public class WebtoonManger {

	private final WebtoonReader webtoonReader;
	private final EpisodeReader episodeReader;
	private final EpisodeWriter episodeWriter;

	public void validationTitle(String title) {
		if (webtoonReader.exsistsTitie(title)) {
			throw new DuplicatedException(WEBTOON_TITLE_DUPLICATED);
		}
	}

	public void validationWebtoon(Webtoon webtoon, Long memberId, int no) {
		webtoon.validateAuthor(memberId);
		validateDuplicateNo(webtoon, no);
	}

	private void validateDuplicateNo(Webtoon webtoon, int no) {
		if (episodeReader.validateDuplicateNo(webtoon, no)) {
			throw new DuplicatedException(EPISODE_NUMBER_DUPLICATED);
		}
	}

	public void purchase(Episode episode, Member member) {
		PurchasedEpisode purchasedEpisode = PurchasedEpisode.create(member, episode);
		episodeReader.save(purchasedEpisode);
	}

	public void uploadEpisode(EpisodeSchema episodeSchema, Webtoon webtoon, List<String> mainUrls, String thumbnailUrl) {
		Episode episode = episodeSchema.toEpisode(webtoon,mainUrls,thumbnailUrl);
		episodeWriter.write(episode);
	}
}
