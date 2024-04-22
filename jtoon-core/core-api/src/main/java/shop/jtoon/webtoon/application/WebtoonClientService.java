package shop.jtoon.webtoon.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImageUpload;
import shop.jtoon.service.S3Service;

@Service
@RequiredArgsConstructor
public class WebtoonClientService {

	private final S3Service s3Service;

	public String parseUrl(ImageUpload imageUpload) {
		return s3Service.uploadUrl(imageUpload.key());
	}

	public ImageUpload upload(ImageUpload imageUpload) {
		return s3Service.uploadImage(imageUpload);
	}

	public void deleteImage(String thumbnailUrl) {
		s3Service.deleteImage(thumbnailUrl);
	}
}
