package shop.jtoon.webtoon.application;

import static shop.jtoon.common.ImageType.*;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.jtoon.common.ImageType;
import shop.jtoon.dto.UploadImageDto;
import shop.jtoon.service.S3Service;
import shop.jtoon.webtoon.request.CreateEpisodeReq;
import shop.jtoon.webtoon.request.CreateWebtoonReq;

@Service
@RequiredArgsConstructor
public class WebtoonClientService {

	private final S3Service s3Service;

	public String upload(ImageType imageType, CreateWebtoonReq request, MultipartFile thumbnailImage) {
		UploadImageDto uploadImageDto = request.toUploadImageDto(imageType, thumbnailImage);

		return s3Service.uploadImage(uploadImageDto);
	}

	public String upload(UploadImageDto uploadImageDto) {
		return s3Service.uploadImage(uploadImageDto);
	}

	public void deleteImage(String thumbnailUrl) {
		s3Service.deleteImage(thumbnailUrl);
	}
}
