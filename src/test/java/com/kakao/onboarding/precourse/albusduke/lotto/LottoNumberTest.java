package com.kakao.onboarding.precourse.albusduke.lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoNumberTest {
    @Test
    void 숫자_범위_초과인_숫자로_로또_번호를_생성할_수_없다() {
        assertThatThrownBy(() ->
                new LottoNumber(46)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자_범위_미만인_숫자로_로또_번호를_생성할_수_없다() {
        assertThatThrownBy(() ->
                new LottoNumber(0)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위_이내인_숫자로_로또_번호를_생성할_수_있다() {
        assertThatCode(() ->
                new LottoNumber(1)
        ).doesNotThrowAnyException();
    }


}
