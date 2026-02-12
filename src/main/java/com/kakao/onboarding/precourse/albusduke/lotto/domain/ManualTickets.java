package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import java.util.List;

public class ManualTickets {
    private final List<Integer> ticketNumbers;

    public ManualTickets(List<Integer> manualPurchaseGames) {
        this.ticketNumbers = manualPurchaseGames;
    }

    public List<Integer> getTicketNumbers() {
        return ticketNumbers;
    }
}
