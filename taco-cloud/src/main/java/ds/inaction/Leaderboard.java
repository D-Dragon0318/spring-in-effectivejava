package ds.inaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 排行榜功能
 */
public class Leaderboard {
	
	private final int maxCapacity;
	
	private final ConcurrentSkipListSet<PlayerScore> leaderboard = new ConcurrentSkipListSet<>();
	
	public Leaderboard(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
	
	/**
	 * 添加玩家：
	 * 
	 * @param playerId 玩家ID
	 * @param score 分数
	 * @param timestamp 自增ID
	 */
	public void addScore(String playerId, int score, int timestamp) {
		leaderboard.add(new PlayerScore(playerId, score, timestamp));
		// 检查并移除超出容量限制的最低分玩家
        while (leaderboard.size() > maxCapacity) {
            leaderboard.pollLast();
        }
	}
	
	/**
	 * 获取TOPN：
	 * 
	 * @param limit
	 * @return
	 */
	public List<PlayerScore> getTopN() {
        // 返回不可变视图
        return Collections.unmodifiableList(new ArrayList<>(leaderboard));
//        return topScores;
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