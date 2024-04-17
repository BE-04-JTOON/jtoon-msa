package shop.jtoon.payment.domain;

import lombok.Builder;

@Builder
public record Buyer(
	String buyerEmail,
	String buyerName,
	String buyerPhone
) {

}
