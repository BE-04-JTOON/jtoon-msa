package shop.jtoon.payment.domain;

import lombok.Builder;

@Builder
public record Pay(
	String impUid,
	String merchantUid,
	String payMethod
) {

}
