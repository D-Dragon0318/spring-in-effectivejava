package ds.inaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 排行榜功能
 */
public class Leaderboard {
	private final ConcurrentSkipListSet<PlayerScore> leaderboard = new ConcurrentSkipListSet<>();
	
	/**
	 * 添加玩家：
	 * 
	 * @param playerId 玩家ID
	 * @param score 分数
	 * @param timestamp 自增ID
	 */
	public void addScore(String playerId, int score, int timestamp) {
		leaderboard.add(new PlayerScore(playerId, score, timestamp));
	}
	
	/**
	 * 获取TOPN：
	 * 
	 * @param limit
	 * @return
	 */
	public List<PlayerScore> getTopN(int limit) {
        List<PlayerScore> topScores = new ArrayList<>();
        Iterator<PlayerScore> iterator = leaderboard.iterator();
        while (iterator.hasNext() && topScores.size() < limit) {
            topScores.add(iterator.next());
        }
        return topScores;
    }
	
	/**
	 * 排行榜上的第一名玩家
	 * @return
	 */
	public PlayerScore getTopPlayer() {
        if (!leaderboard.isEmpty()) {
            return leaderboard.first();
        } else {
            return null;
        }
    }
}