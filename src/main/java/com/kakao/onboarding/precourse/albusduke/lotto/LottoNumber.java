package com.kakao.onboarding.precourse.albusduke.lotto;

public class LottoNumber {

    public LottoNumber(String string) {
        try {
            int number = Integer.parseInt(string);

            if (number > 45) {
                throw new IllegalArgumentException("45 초과 숫자는 입력할 수 없습니다.");
            }

            if (number < 1) {
                throw new IllegalArgumentException("1 미만 숫자는 입력할 수 없습니다.");
            }

        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("빈 문자열은 입력할 수 없습니다.");
        }
    }
}
