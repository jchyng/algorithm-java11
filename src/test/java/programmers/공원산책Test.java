package programmers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Stream;

class 공원산책Test {
    static int[][] map;
    static int[] pos = {0, 0};
    static Map<String, int[]> direct = Map.of(
            "N", new int[]{-1, 0},
            "S", new int[]{1, 0},
            "W", new int[]{0, -1},
            "E", new int[]{0, 1}
    );
    static int H = 0;
    static int W = 0;

    //https://school.programmers.co.kr/learn/courses/30/lessons/172928
    @ParameterizedTest
    @MethodSource("공원산책_파라미터")
    void 공원산책(String[] park, String[] routes, int[] result) {
        H = park.length;
        W = park[0].length();
        map = new int[H][W];

        //지도 생성
        for(int i=0; i<H; i++){
            String[] p = park[i].split("");
            for(int j=0; j<W; j++){
                if(p[j].equals("S")){
                    map[i][j] = 1;
                    pos = new int[]{i, j};
                }
                else if(p[j].equals("X")) map[i][j] = -1;
            }
        }

        //이동
        for(String route: routes) {
            String[] cmd = route.split(" ");
            move(cmd);
        }

        assertArrayEquals(pos, result);
    }

    private void move(String[] cmd){
        int dist = Integer.parseInt(cmd[1]);
        int[] d = direct.get(cmd[0]);

        int y = pos[0] + d[0]*dist;
        int x = pos[1] + d[1]*dist;

        //공원 범위 체크
        if(x >= 0 && x < W && y >= 0 && y < H) {
            //벽 체크
            boolean success = true;
            for(int i=1; i<=dist; i++){
                int i_y = pos[0] + d[0] * i;
                int i_x = pos[1] + d[1] * i;
                if(map[i_y][i_x] == -1){
                    success = false;
                    break;
                }
            }
            if(success) {
                pos[0] = y;
                pos[1] = x;
            }
        }
    }

    static Stream<Arguments> 공원산책_파라미터() {
        return Stream.of(
            Arguments.of(
                new String[]{"SOO", "OOO", "OOO"},
                new String[]{"E 2", "S 2", "W 1"},
                new int[]{2, 1}
            ),
            Arguments.of(
                new String[]{"SOO", "OXX", "OOO"}, 
                new String[]{"E 2", "S 2", "W 1"}, 
                new int[]{0, 1}
            ),
            Arguments.of(
                new String[]{"OSO", "OOO", "OXO", "OOO"}, 
                new String[]{"E 2", "S 3", "W 1"}, 
                new int[]{0, 0}
            )
        );
    }
}