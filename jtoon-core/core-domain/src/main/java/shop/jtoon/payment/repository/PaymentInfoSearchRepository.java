package shop.jtoon.payment.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import shop.jtoon.member.entity.QMember;
import shop.jtoon.payment.entity.PaymentInfo;
import shop.jtoon.payment.entity.QPaymentInfo;
import shop.jtoon.util.DynamicQuery;

@Repository
@RequiredArgsConstructor
public class PaymentInfoSearchRepository {

    private final JPAQueryFactory queryFactory;

    public List<PaymentInfo> searchByMerchantsUidAndEmail(List<String> merchantsUid, String email) {
        return queryFactory.selectFrom(QPaymentInfo.paymentInfo)
                .join(QPaymentInfo.paymentInfo.member, QMember.member).fetchJoin()
                .where(
                        DynamicQuery.filterCondition(merchantsUid, QPaymentInfo.paymentInfo.merchantUid::in),
                        DynamicQuery.generateEq(email, QPaymentInfo.paymentInfo.member.email::eq)
                )
                .fetch();
    }
}
