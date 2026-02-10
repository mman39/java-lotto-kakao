package com.kakao.onboarding.precourse.albusduke.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    public PurchaseAmountDto inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = scanner.nextInt();

        if (purchaseAmount < 0 || 100_000_000 < purchaseAmount) {
            throw new IllegalArgumentException("구매금액은 0이상 100,000,000 이상이어야 합니다");
        }
        return new PurchaseAmountDto(purchaseAmount);
    }

    public PreviousWinningNumbersDto inputPreviousWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).toList();
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();

        return new PreviousWinningNumbersDto(winningNumbers, bonusNumber);
    }
}
