package programmers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 달리기_경주Test {

    //https://school.programmers.co.kr/learn/courses/30/lessons/178871?language=java
    @ParameterizedTest
    @MethodSource("달리기_경주_파라미터")
    public void 달리기_경주(String[] players, String[] callings, String[] result) {
        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i<players.length; i++) {
            map.put(players[i], i);
        }

        for(String calling : callings) {
            int i = map.get(calling);

            String pre = players[i-1];
            players[i-1] = players[i];
            players[i] = pre;

            map.put(calling, i-1);
            map.put(pre, i);
        }

        assertArrayEquals(result, players);
    }


    private static Stream<Arguments> 달리기_경주_파라미터() {
        return Stream.of(
            Arguments.of(
                new String[]{"mumu", "soe", "poe", "kai", "mine"},
                new String[]{"kai", "kai", "mine", "mine"},
                new String[]{"mumu", "kai", "mine", "soe", "poe"}
            )
        );
    }
}