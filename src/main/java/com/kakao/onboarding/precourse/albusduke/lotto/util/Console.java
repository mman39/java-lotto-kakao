package com.kakao.onboarding.precourse.albusduke.lotto.util;

import java.util.Scanner;

public class Console implements Input {
    private static final String NUMBER_FORMAT_ERROR_MSG = "숫자 형식이어야 합니다.";
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readNext() {
        return scanner.nextLine().trim();
    }

    @Override
    public int readInt() {
        try {
            String next = readNext();
            return Integer.parseInt(next.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR_MSG);
        }
    }
}
