package programmers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class 붕대감기Test {

    //https://school.programmers.co.kr/learn/courses/30/lessons/250137?language=java
    @ParameterizedTest
    @MethodSource("붕대감기_파라미터")
    public void 붕대감기(int[] bandage, int health, int[][] attacks, int result) {
        int time = bandage[0];
        int increase = bandage[1];
        int bonus = bandage[2];

        int answer = health;
        int cnt = 0;

        int last = attacks[attacks.length-1][0];
        int attIdx = 0;

        for(int i=1; i<=last; i++){
            //공격
            if(attacks[attIdx][0] == i) {
                answer -=  attacks[attIdx][1];
                attIdx++;
                cnt = 0;

                if(answer <= 0) {
                    answer = -1;
                    break;
                }
                continue;
            }

            // 붕대 감기
            if(answer < health) {
                cnt++;
                answer += increase;
                // 연속 성공 시 보너스
                if(cnt == time) {
                    answer += bonus;
                    cnt = 0;
                }
                // 체력을 초과하면 최대값으로
                if (answer > health) {
                    answer = health;
                }
            }
        }

        // Then
        assertEquals(result, answer);
    }
    
    private static Stream<Arguments> 붕대감기_파라미터() {
        return Stream.of(
            Arguments.of(
                new int[]{5, 1, 5}, 
                30, 
                new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}}, 
                5
            ),
            Arguments.of(
                new int[]{3, 2, 7}, 
                20, 
                new int[][]{{1, 15}, {5, 16}, {8, 6}}, 
                -1
            )
        );
    }
}