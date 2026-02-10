package com.kakao.onboarding.precourse.albusduke.lotto;

public class WinningNumbers {

    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        validateBonusInNumbers(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private static void validateBonusInNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if(lottoNumbers.hasNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호에 포함될 수 없습니다.");
        }
    }

    public GameResult calculateResult(LottoNumbers game) {
        int matchingCount = game.countMatchingNumbers(winningNumbers);
        int bonusMatchingCount = game.hasNumber(bonusNumber) ? 1 : 0;

        return new GameResult(matchingCount, bonusMatchingCount);
    }
}
