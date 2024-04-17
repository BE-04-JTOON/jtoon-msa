package shop.jtoon.payment.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.jtoon.member.entity.Member;
import shop.jtoon.payment.domain.Buyer;
import shop.jtoon.payment.domain.Item;
import shop.jtoon.payment.domain.Pay;
import shop.jtoon.payment.entity.CookieItem;
import shop.jtoon.payment.entity.PaymentInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PayMapper {

	public static PaymentInfo toPaymentInfo(Pay pay, Item item, Member member) {
		return PaymentInfo.builder()
			.impUid(pay.impUid())
			.merchantUid(pay.merchantUid())
			.payMethod(pay.payMethod())
			.cookieItem(CookieItem.from(item.itemName()))
			.amount(item.amount())
			.member(member)
			.build();
	}
}
