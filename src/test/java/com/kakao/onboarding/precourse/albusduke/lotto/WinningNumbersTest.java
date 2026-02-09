package com.kakao.onboarding.precourse.albusduke.lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WinningNumbersTest {

    @Test
    void LottoNumber_와_보너스번호로_생성할_수_있다() {
        assertThatCode(() ->
                new WinningNumbers(new LottoNumbers(List.of(1, 2, 3, 4, 5 ,6)), new LottoNumber(7))
        ).doesNotThrowAnyException();
    }

    @Test
    void LottoNumbers_와_보너스_숫자가_겹칠_수_없다() {
        assertThatThrownBy(() ->
                new WinningNumbers(new LottoNumbers(List.of(1, 2, 3, 4, 5 ,6)), new LottoNumber(1))
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
