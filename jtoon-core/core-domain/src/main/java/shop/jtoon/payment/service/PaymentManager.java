package shop.jtoon.payment.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.exception.DuplicatedException;
import shop.jtoon.exception.InvalidRequestException;
import shop.jtoon.payment.domain.Item;
import shop.jtoon.payment.domain.Pay;
import shop.jtoon.payment.entity.CookieItem;
import shop.jtoon.payment.repository.PaymentReader;
import shop.jtoon.type.ErrorStatus;

@Service
@RequiredArgsConstructor
public class PaymentManager {

	private final PaymentReader paymentReader;

	public void validatePayItem(Pay pay, Item item) {
		validatePaymentInfo(item.itemName(), item.amount());
		validateImpUid(pay.impUid());
		validateMerchantUid(pay.merchantUid());
	}

	public void validatePaymentInfo(String itemName, BigDecimal amount) {
		CookieItem cookieItem = CookieItem.from(itemName);
		validateAmount(amount, cookieItem.getAmount());
	}

	private void validateAmount(BigDecimal amount, BigDecimal cookieAmount) {
		if (!amount.equals(cookieAmount)) {
			throw new InvalidRequestException(ErrorStatus.PAYMENT_AMOUNT_INVALID);
		}
	}

	private void validateImpUid(String impUid) {
		if (paymentReader.existsImpUid(impUid)) {
			throw new DuplicatedException(ErrorStatus.PAYMENT_IMP_UID_DUPLICATED);
		}
	}

	private void validateMerchantUid(String merchantUid) {
		if (paymentReader.existsMerchantUid(merchantUid)) {
			throw new DuplicatedException(ErrorStatus.PAYMENT_MERCHANT_UID_DUPLICATED);
		}
	}
}
