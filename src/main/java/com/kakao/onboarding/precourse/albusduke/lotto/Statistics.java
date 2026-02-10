package com.kakao.onboarding.precourse.albusduke.lotto;

import java.util.Map;

public record Statistics(Map<Prize, Integer> counts , double ratio) {
}
