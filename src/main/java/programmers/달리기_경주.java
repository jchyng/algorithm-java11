package programmers;

import java.util.Map;
import java.util.HashMap;

public class 달리기_경주 {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> ranking = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            ranking.put(players[i], i);
        }

        for (String calling : callings) {
            int rank = ranking.get(calling);
            if (rank > 0) {
                String frontPlayer = players[rank - 1];

                players[rank - 1] = calling;
                players[rank] = frontPlayer;

                ranking.put(calling, rank - 1);
                ranking.put(frontPlayer, rank);
            }
        }

        return players;
    }
}
