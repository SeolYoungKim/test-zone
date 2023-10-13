package init;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JumpingCouponInfo {
    enum JumpingCouponNotiType {
        NONE,
    }

    private final boolean isChanged;

    private final JumpingCouponNotiType notiType;

    private final int couponChargeCondition;

    private final int accumulatedPaymentAmount;

    private final int couponChargeCount;

    @Builder(access = AccessLevel.PRIVATE)
    private JumpingCouponInfo(
            boolean isChanged,
            JumpingCouponNotiType notiType,
            int couponChargeCondition,
            int accumulatedPaymentAmount,
            int couponChargeCount
    ) {
        this.isChanged = isChanged;
        this.notiType = notiType;
        this.couponChargeCondition = couponChargeCondition;
        this.accumulatedPaymentAmount = accumulatedPaymentAmount;
        this.couponChargeCount = couponChargeCount;
    }

    public static JumpingCouponInfo createWithDefaultValues() {
        return JumpingCouponInfo.builder()
                .isChanged(false)
                .notiType(JumpingCouponNotiType.NONE)
                .couponChargeCondition(0)
                .accumulatedPaymentAmount(0)
                .couponChargeCount(0)
                .build();
    }

    public static JumpingCouponInfo create() {
        return JumpingCouponInfo.builder()
                .notiType(JumpingCouponNotiType.NONE)
                .build();
    }
}

