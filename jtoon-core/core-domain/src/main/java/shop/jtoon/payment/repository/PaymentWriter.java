package shop.jtoon.payment.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.payment.entity.PaymentInfo;

@Repository
@RequiredArgsConstructor
public class PaymentWriter {

	private final PaymentInfoRepository paymentInfoRepository;

	public void write(final PaymentInfo paymentInfo) {
		paymentInfoRepository.save(paymentInfo);
	}
}
