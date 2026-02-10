package com.kakao.onboarding.precourse.albusduke.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputConsoleView {

    private static final String PURCHASE_AMOUNT_REQUEST = "구입금액을 입력해 주세요.";
    private static final String ILLEGAL_PURCHASE_AMOUNT_ERR_MSG = "구매금액은 0이상 100,000,000 이상이어야 합니다";
    private static final String WINNING_NUMBERS_REQUEST = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_REQUEST = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner = new Scanner(System.in);

    public PurchaseAmountDto inputPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_REQUEST);
        int purchaseAmount = scanner.nextInt();

        if (purchaseAmount < 0 || 100_000_000 < purchaseAmount) {
            throw new IllegalArgumentException(ILLEGAL_PURCHASE_AMOUNT_ERR_MSG);
        }
        return new PurchaseAmountDto(purchaseAmount);
    }

    public PreviousWinningNumbersDto inputPreviousWinningNumbers() {
        scanner.nextLine();
        System.out.println(WINNING_NUMBERS_REQUEST);
        List<Integer> winningNumbers = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).toList();
        System.out.println(BONUS_NUMBER_REQUEST);
        int bonusNumber = scanner.nextInt();

        return new PreviousWinningNumbersDto(winningNumbers, bonusNumber);
    }
}
