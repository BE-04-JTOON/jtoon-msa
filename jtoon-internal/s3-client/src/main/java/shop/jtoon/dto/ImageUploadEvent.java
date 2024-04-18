package shop.jtoon.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;

@Builder
public record ImageUploadEvent(
	String key,
	MultipartFile multipartFile
) {

}
