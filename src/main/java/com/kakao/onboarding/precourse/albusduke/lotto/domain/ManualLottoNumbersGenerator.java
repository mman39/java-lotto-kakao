package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import java.util.List;

public class ManualLottoNumbersGenerator implements LottoNumbersGenerator {
    private final List<Integer> numbers;

    public ManualLottoNumbersGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public LottoNumbers generate() {
        return new LottoNumbers(numbers);
    }
}
