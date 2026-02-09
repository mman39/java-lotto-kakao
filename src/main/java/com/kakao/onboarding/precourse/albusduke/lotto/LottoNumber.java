package com.kakao.onboarding.precourse.albusduke.lotto;

public class LottoNumber {
    private final static int MIN_NUMBER = 1;
    private final static int MAX_NUMBER = 45;

    public LottoNumber(int number) {
        validateRange(number);
    }

    private void validateRange(int number) {
        if (number > MAX_NUMBER || number < MIN_NUMBER) {
            throw new IllegalArgumentException("1 미만 또는 45 초과 숫자는 입력할 수 없습니다.");
        }
    }
}
