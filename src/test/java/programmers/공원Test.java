package programmers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

public class 공원Test {

    //https://school.programmers.co.kr/learn/courses/30/lessons/340198
    @ParameterizedTest
    @MethodSource("공원_파라미터")
    public void 공원(int[] mats, String[][] park, int result) {
        mats = Arrays.stream(mats).sorted().toArray();  //작은 돗자리부터 시작하도록 정렬

        int answer = -1;    //돗자리를 깔 수 있는 자리가 없다면 -1이기 때문에 초기값을 -1로 설정
        int m_idx = 0;      //현재 검사 중인 돗자리 인덱스

        for(int i=0; i< park.length; i++){
            for (int j=0; j< park[i].length; j++){
                if (park[i][j].equals("-1")){       //현 위치가 빈자리라면
                    while(m_idx < mats.length){     //가장 큰 돗자리를 사용했거나, 펼칠 수 없다면 패스
                        int size = availableSize(park, mats[m_idx], i, j);

                        if(size == -1) break;

                        answer = size;      //현재 깔 수 있는 돗자리 최대 크기
                        m_idx++;            //다음 돗자리
                    }
                }
                if(m_idx >= mats.length) break; //이미 최대 크기의 돗자리를 깔 수 있다면 더 이상 반복할 필요 없음
            }
        }

        // Then
        assertEquals(result, answer);
    }

    private int availableSize(String[][] park, int size, int y, int x){
        for(int i=y; i<y+size; i++){          
            for(int j=x; j<x+size; j++){
                if(i > park.length - 1 || j > park[0].length - 1) return -1; //돗자리 범위를 넘어간 경우
                if(!park[i][j].equals("-1")) return -1; //이미 다른 돗자리가 존재하는 경우
            }
        }
        return size;
    }

    private static Stream<Arguments> 공원_파라미터() {
        return Stream.of(
            Arguments.of(
                new int[]{5, 3, 2},
                new String[][]{
                    {"A", "A", "-1", "B", "B", "B", "B", "-1"},
                    {"A", "A", "-1", "B", "B", "B", "B", "-1"},
                    {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                    {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
                    {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"},
                    {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}
                },
                3
            ),
            Arguments.of(
                    new int[]{1},
                    new String[][]{
                            {"D"}
                    },
                    -1
            )
        );
    }
}