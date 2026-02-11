package com.kakao.onboarding.precourse.albusduke.lotto;

import com.kakao.onboarding.precourse.albusduke.lotto.controller.LottoController;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.RandomLottoNumbersGenerator;
import com.kakao.onboarding.precourse.albusduke.lotto.service.LottoService;
import com.kakao.onboarding.precourse.albusduke.lotto.service.StatisticsService;
import com.kakao.onboarding.precourse.albusduke.lotto.util.Console;
import com.kakao.onboarding.precourse.albusduke.lotto.view.InputConsoleView;
import com.kakao.onboarding.precourse.albusduke.lotto.view.OutputConsoleView;

public class App {
    public static void main(String[] args) {
        LottoController lottoController = createLottoController();
        lottoController.runLottoGame();
    }

    private static LottoController createLottoController() {
        Console console = new Console();
        OutputConsoleView outputConsoleView = new OutputConsoleView(console);
        InputConsoleView inputConsoleView = new InputConsoleView(console);

        LottoService lottoService  = new LottoService(new RandomLottoNumbersGenerator());
        StatisticsService statisticsService = new StatisticsService();

        return new LottoController(inputConsoleView, outputConsoleView, lottoService, statisticsService);
    }
}
