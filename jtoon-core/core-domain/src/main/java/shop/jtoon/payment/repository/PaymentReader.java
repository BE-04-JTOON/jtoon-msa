package shop.jtoon.payment.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.payment.entity.PaymentInfo;

@Repository
@RequiredArgsConstructor
public class PaymentReader {

	private final PaymentInfoRepository paymentInfoRepository;
	private final PaymentInfoSearchRepository paymentInfoSearchRepository;

	public boolean existsImpUid(String impUid) {
		return paymentInfoRepository.existsByImpUid(impUid);
	}

	public boolean existsMerchantUid(String merchantUid) {
		return paymentInfoRepository.existsByMerchantUid(merchantUid);
	}

	public List<PaymentInfo> searchByMerchantsUidAndEmail(List<String> merchantsUid, String email) {
		return paymentInfoSearchRepository.searchByMerchantsUidAndEmail(merchantsUid, email);
	}
}
