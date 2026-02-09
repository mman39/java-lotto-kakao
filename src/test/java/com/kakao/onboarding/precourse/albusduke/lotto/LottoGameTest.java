package com.kakao.onboarding.precourse.albusduke.lotto;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoGameTest {
    @Test
    void 숫자_6개_미만으로_생성할_수_없다() {
        assertThatThrownBy(() ->
                new LottoGame(List.of(1, 2, 3, 4, 5))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자_6개_초과로_생성할_수_없다() {
        assertThatThrownBy(() ->
                new LottoGame(List.of(1, 2, 3, 4, 5, 6, 7))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위_외_숫자로_생성할_수_없다() {
        assertThatThrownBy(() ->
                new LottoGame(List.of(50, 2, 3, 4, 5, 6))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위_내_숫자_6개로_생성할_수_있다() {
        assertThatCode(() ->
                new LottoGame(List.of(1, 2, 3, 4, 5, 6))
        ).doesNotThrowAnyException();
    }

    @Test
    void 중복된_숫자로_생성할_수_없다() {
        assertThatThrownBy(() ->
                new LottoGame(List.of(1, 1, 3, 4, 5, 6))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
