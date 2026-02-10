package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private final static int MIN_NUMBER = 1;
    private final static int MAX_NUMBER = 45;
    private final static String NUMBER_OUT_OF_RANGE_ERR_MSG = "1 미만 또는 45 초과 숫자는 입력할 수 없습니다.";

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number > MAX_NUMBER || number < MIN_NUMBER) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE_ERR_MSG);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}
