package com.kakao.onboarding.precourse.albusduke.lotto;

public class WinningNumbers {

    public WinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        validateBonusInNumbers(lottoNumbers, bonusNumber);
    }

    private static void validateBonusInNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if(lottoNumbers.hasNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호에 포함될 수 없습니다.");
        }
    }
}
