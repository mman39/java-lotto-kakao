package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.GameResult;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Prize;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class PrizeTest {

    @Test
    void 당첨_조건에_따라_Prize를_생성할_수_있다() {
        assertThat(Prize.of(new GameResult(6, false))).isEqualTo(Prize.FIRST);

        assertThat(Prize.of(new GameResult(5, true))).isEqualTo(Prize.SECOND);

        assertThat(Prize.of(new GameResult(5, false))).isEqualTo(Prize.THIRD);

        assertThat(Prize.of(new GameResult(4, true))).isEqualTo(Prize.FORTH);
        assertThat(Prize.of(new GameResult(4, false))).isEqualTo(Prize.FORTH);

        assertThat(Prize.of(new GameResult(3, true))).isEqualTo(Prize.FIFTH);
        assertThat(Prize.of(new GameResult(3, false))).isEqualTo(Prize.FIFTH);
    }

    @Test
    void 당첨_조건에_포함되지_않으면_NONE을_생성한다() {
        assertThat(Prize.of(new GameResult(2, true))).isEqualTo(Prize.NONE);
        assertThat(Prize.of(new GameResult(2, false))).isEqualTo(Prize.NONE);
        assertThat(Prize.of(new GameResult(1, true))).isEqualTo(Prize.NONE);
        assertThat(Prize.of(new GameResult(1, false))).isEqualTo(Prize.NONE);
        assertThat(Prize.of(new GameResult(0, false))).isEqualTo(Prize.NONE);
    }

}
