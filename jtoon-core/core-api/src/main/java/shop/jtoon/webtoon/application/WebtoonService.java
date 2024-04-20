package shop.jtoon.webtoon.application;

import static shop.jtoon.common.ImageType.*;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImageUploadEvent;
import shop.jtoon.webtoon.domain.WebtoonDetail;
import shop.jtoon.webtoon.entity.enums.DayOfWeek;
import shop.jtoon.webtoon.request.CreateWebtoonReq;
import shop.jtoon.webtoon.request.GetWebtoonsReq;
import shop.jtoon.webtoon.response.WebtoonInfoRes;
import shop.jtoon.webtoon.response.WebtoonItemRes;
import shop.jtoon.webtoon.service.WebtoonDomainService;

@Service
@RequiredArgsConstructor
public class WebtoonService {

	private final WebtoonClientService webtoonClientService;
	private final WebtoonDomainService webtoonDomainService;
	private final ApplicationEventPublisher publisher;

	public void createWebtoon(Long memberId, MultipartFile thumbnailImage, CreateWebtoonReq request) {
		ImageUploadEvent imageUploadEvent = request.toUploadImageDto(WEBTOON_THUMBNAIL, thumbnailImage)
			.toImageUploadEvent();
		String thumbnailUrl = webtoonClientService.parseUrl(imageUploadEvent);

		webtoonDomainService.validateDuplicateTitle(request.title());
		webtoonDomainService.createWebtoon(memberId,
			request.toWebtoonInfo(thumbnailUrl),
			request.toWebtoonGenres(),
			request.toWebtoonDayOfWeeks());

		publisher.publishEvent(imageUploadEvent);
	}

	public Map<DayOfWeek, List<WebtoonItemRes>> getWebtoons(GetWebtoonsReq request) {
		return WebtoonItemRes.from(webtoonDomainService.readWebtoons(request.toSearchWebtoon()));
	}

	public WebtoonInfoRes getWebtoon(Long webtoonId) {
		WebtoonDetail detail = webtoonDomainService.readWebtoonDetail(webtoonId);

		return WebtoonInfoRes.of(detail);
	}
}
