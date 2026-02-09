package com.kakao.onboarding.precourse.albusduke.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {
    private static final int NUMBER_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoGame(List<Integer> numbers) {
        if (numbers.size() != NUMBER_SIZE) {
            throw new IllegalArgumentException("숫자 6개로 생성할 수 있습니다.");
        }

        lottoNumbers = new ArrayList<>();

        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
    }
}
