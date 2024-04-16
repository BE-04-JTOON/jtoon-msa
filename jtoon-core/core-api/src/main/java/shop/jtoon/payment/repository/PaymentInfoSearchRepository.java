package shop.jtoon.payment.repository;
import static shop.jtoon.member.entity.QMember.*;
import static shop.jtoon.payment.entity.QPaymentInfo.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import shop.jtoon.global.util.DynamicQuery;
import shop.jtoon.payment.entity.PaymentInfo;

@Repository
@RequiredArgsConstructor
public class PaymentInfoSearchRepository {

    private final JPAQueryFactory queryFactory;
    private final PaymentInfoRepository paymentInfoRepository;

    public List<PaymentInfo> searchByMerchantsUidAndEmail(List<String> merchantsUid, String email) {
        return queryFactory.selectFrom(paymentInfo)
                .join(paymentInfo.member, member).fetchJoin()
                .where(
                        DynamicQuery.filterCondition(merchantsUid, paymentInfo.merchantUid::in),
                        DynamicQuery.generateEq(email, paymentInfo.member.email::eq)
                )
                .fetch();
    }
}
