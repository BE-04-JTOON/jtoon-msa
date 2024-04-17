package shop.jtoon.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.repository.MemberReader;
import shop.jtoon.payment.domain.Item;
import shop.jtoon.payment.domain.Pay;
import shop.jtoon.payment.domain.Receipt;
import shop.jtoon.payment.entity.PaymentInfo;
import shop.jtoon.payment.repository.PaymentReader;
import shop.jtoon.payment.repository.PaymentWriter;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PayService {

	private final MemberReader memberReader;
	private final PaymentManager paymentManager;
	private final CookieManager cookieManager;
	private final PaymentReader paymentReader;
	private final PaymentWriter paymentWriter;

	@Transactional
	public void createPaymentInfo(Pay pay, Item item, Member member) {
		createPayInfo(pay, item, member);
		createCookie(item, member);
	}

	public List<Receipt> readPayments(List<String> merchantsUid, String email) {
		Member member = memberReader.readByEmail(email);
		List<PaymentInfo> paymentInfos = paymentReader.searchByMerchantsUidAndEmail(
			merchantsUid,
			member.getEmail()
		);

		return paymentInfos.stream()
			.map(Receipt::toReceipt)
			.toList();
	}

	private void createPayInfo(Pay pay, Item item, Member member) {
		paymentManager.validatePayItem(pay, item);
		paymentWriter.write(PayMapper.toPaymentInfo(pay, item, member));
	}

	private void createCookie(Item item, Member member) {
		cookieManager.bakeCookie(item, member);
	}
}
