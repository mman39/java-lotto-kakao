package com.kakao.onboarding.precourse.albusduke.lotto;

import com.kakao.onboarding.precourse.albusduke.lotto.controller.LottoController;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.RandomLottoNumbersGenerator;
import com.kakao.onboarding.precourse.albusduke.lotto.service.LottoService;
import com.kakao.onboarding.precourse.albusduke.lotto.service.StatisticsService;
import com.kakao.onboarding.precourse.albusduke.lotto.util.Console;
import com.kakao.onboarding.precourse.albusduke.lotto.view.InputView;
import com.kakao.onboarding.precourse.albusduke.lotto.view.OutputView;

public class App {
    public static void main(String[] args) {
        LottoController lottoController = createLottoController();
        lottoController.runLottoGame();
    }

    private static LottoController createLottoController() {
        Console console = new Console();

        OutputView outputConsoleView = new OutputView(console);
        InputView inputConsoleView = new InputView(console);

        LottoService lottoService  = new LottoService(new RandomLottoNumbersGenerator());
        StatisticsService statisticsService = new StatisticsService();

        return new LottoController(inputConsoleView, outputConsoleView, lottoService, statisticsService);
    }
}
