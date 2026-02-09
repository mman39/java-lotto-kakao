package com.kakao.onboarding.precourse.albusduke.lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {
    private static final int NUMBER_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<Integer> numbers) {
        if (numbers.size() != NUMBER_SIZE) {
            throw new IllegalArgumentException("숫자 6개로 생성할 수 있습니다.");
        }

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("중복된 숫자로 생성할 수 없습니다.");
        }

        lottoNumbers = new ArrayList<>();

        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

    }

    public boolean hasNumber(LottoNumber number) {
        for (LottoNumber lottoNumber : lottoNumbers) {
            if (lottoNumber.equals(number)) {
                return true;
            }
        }

        return false;
    }

    public int countMatchingNumbers(LottoNumbers otherNumbers) {
        int count = 0;

        for (LottoNumber number : otherNumbers.lottoNumbers) {
            if (hasNumber(number)) {
                count++;
            }
        }

        return count;
    }


}
