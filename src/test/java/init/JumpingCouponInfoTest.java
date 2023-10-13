package init;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JumpingCouponInfoTest {
    @Test
    void 값을_할당하지_않아도_기본_타입은_알아서_초기화된다() {
        JumpingCouponInfo jumpingCouponInfo = JumpingCouponInfo.create();

        assertThat(jumpingCouponInfo.isChanged()).isFalse();
        assertThat(jumpingCouponInfo.getCouponChargeCondition()).isEqualTo(0);
        assertThat(jumpingCouponInfo.getAccumulatedPaymentAmount()).isEqualTo(0);
        assertThat(jumpingCouponInfo.getCouponChargeCount()).isEqualTo(0);
    }
}