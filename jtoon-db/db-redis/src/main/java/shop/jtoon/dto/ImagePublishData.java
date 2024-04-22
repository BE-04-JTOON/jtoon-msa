package shop.jtoon.dto;

import lombok.Builder;

@Builder
public record ImagePublishData(
	Long id,
	String key,
	byte[] data
) {

}
