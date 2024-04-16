package shop.jtoon.webtoon.application;

import static shop.jtoon.common.ImageType.*;
import static shop.jtoon.type.ErrorStatus.*;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.UploadImageDto;

import shop.jtoon.exception.InvalidRequestException;

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

	public void createWebtoon(Long memberId, MultipartFile thumbnailImage, CreateWebtoonReq request) {
		webtoonDomainService.validateDuplicateTitle(request.title());
		String thumbnailUrl = webtoonClientService.upload(request.toUploadImageDto(WEBTOON_THUMBNAIL, thumbnailImage));

		try {
			webtoonDomainService.createWebtoon(memberId,
				request.toWebtoonInfo(thumbnailUrl),
				request.toWebtoonGenres(),
				request.toWebtoonDayOfWeeks());
		} catch (RuntimeException e) {
			webtoonClientService.deleteImage(thumbnailUrl);
			throw new InvalidRequestException(WEBTOON_CREATE_FAIL);
		}
	}

	public Map<DayOfWeek, List<WebtoonItemRes>> getWebtoons(GetWebtoonsReq request) {
		return WebtoonItemRes.from(webtoonDomainService.readWebtoons(request.toSearchWebtoon()));
	}

	public WebtoonInfoRes getWebtoon(Long webtoonId) {
		WebtoonDetail detail = webtoonDomainService.readWebtoonDetail(webtoonId);

		return WebtoonInfoRes.of(detail);
	}
}
