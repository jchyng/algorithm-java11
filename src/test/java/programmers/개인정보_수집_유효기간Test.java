package programmers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 개인정보_수집_유효기간Test {

    //https://school.programmers.co.kr/learn/courses/30/lessons/150370
    @ParameterizedTest
    @MethodSource("개인정보_수집_유효기간_파라미터")
    public void 개인정보_수집_유효기간(String today, String[] terms, String[] privacies, int[] expected) {
        List<Integer> answer = new ArrayList<>();
        int todayInDays = toDays(today);

        Map<String, Integer> termsMap = new HashMap<>();
        for (String term : terms) {
            String[] t = term.split(" ");
            termsMap.put(t[0], Integer.parseInt(t[1]));
        }

        for (int i = 0; i < privacies.length; i++) {
            String[] p = privacies[i].split(" ");
            String date = p[0];
            String type = p[1];

            int collectionDays = toDays(date);
            int validityMonths = termsMap.get(type);
            int expirationDateInDays = collectionDays + validityMonths * 28;

            if (todayInDays >= expirationDateInDays) {
                answer.add(i + 1);
            }
        }
        assertArrayEquals(expected, answer.stream().mapToInt(Integer::intValue).toArray());
    }

    private int toDays(String date) {
        String[] ymd = date.split("\\.");
        int year = Integer.parseInt(ymd[0]);
        int month = Integer.parseInt(ymd[1]);
        int day = Integer.parseInt(ymd[2]);
        return (year * 12 * 28) + ((month - 1) * 28) + day;
    }


    private static Stream<Arguments> 개인정보_수집_유효기간_파라미터() {
        return Stream.of(
                Arguments.of(
                        "2022.05.19",
                        new String[]{"A 6", "B 12", "C 3"},
                        new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"},
                        new int[]{1, 3}
                ),
                Arguments.of(
                        "2020.01.01",
                        new String[]{"Z 3", "D 5"},
                        new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"},
                        new int[]{1, 4, 5}
                )
        );
    }
}
