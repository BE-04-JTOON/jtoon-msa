package shop.jtoon.dto;

import lombok.Builder;

@Builder
public record ImageUploadEvent(
	String key,
	byte[] data
) {

}
