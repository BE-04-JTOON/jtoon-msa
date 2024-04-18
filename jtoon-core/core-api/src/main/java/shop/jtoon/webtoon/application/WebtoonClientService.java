package shop.jtoon.webtoon.application;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.jtoon.common.ImageType;
import shop.jtoon.dto.ImageUploadEvent;
import shop.jtoon.dto.UploadImageDto;
import shop.jtoon.service.S3Service;
import shop.jtoon.webtoon.request.CreateWebtoonReq;

@Service
@RequiredArgsConstructor
public class WebtoonClientService {

	private final S3Service s3Service;

	public String upload(ImageType imageType, CreateWebtoonReq request, MultipartFile thumbnailImage) {
		UploadImageDto uploadImageDto = request.toUploadImageDto(imageType, thumbnailImage);

		return s3Service.uploadImage(uploadImageDto);
	}

	public String uploadUrl(ImageUploadEvent imageUpload) {
		return s3Service.uploadUrl(imageUpload.key());
	}

	public String upload(ImageUploadEvent imageUpload) {
		return s3Service.uploadImage(imageUpload);
	}

	public void deleteImage(String thumbnailUrl) {
		s3Service.deleteImage(thumbnailUrl);
	}
}
