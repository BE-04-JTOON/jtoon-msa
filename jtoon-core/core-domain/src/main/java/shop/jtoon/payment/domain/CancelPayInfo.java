package shop.jtoon.payment.domain;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record CancelPayInfo(
	String impUid,
	String merchantUid,
	String reason,
	BigDecimal checksum,
	String refundHolder
) {

}
