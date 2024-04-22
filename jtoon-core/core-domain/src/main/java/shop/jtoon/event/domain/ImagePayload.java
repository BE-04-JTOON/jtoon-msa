package shop.jtoon.event.domain;

import lombok.Builder;

@Builder
public record ImagePayload(
	String key,
	byte[] data
) {

}
