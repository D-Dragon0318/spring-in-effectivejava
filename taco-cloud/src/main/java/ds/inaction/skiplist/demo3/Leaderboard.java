package ds.inaction.skiplist.demo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 排行榜功能
 */
public class Leaderboard {
	
	private final PriorityQueue<PlayerScore> leaderboard;  
    private final int maxSize;  
  
    public Leaderboard(int maxSize) {  
        this.maxSize = maxSize;  
        this.leaderboard = new PriorityQueue<>(maxSize);  
    }
	
	/**
	 * 添加玩家：
	 * 
	 * @param playerId 玩家ID
	 * @param score 分数
	 * @param timestamp 自增ID
	 */
	public void addScore(String playerId, int score, int timestamp) {
		PlayerScore newEntry = new PlayerScore(playerId, score, timestamp);  
		  
        // 如果队列已满，移除最低分  
        if (leaderboard.size() == maxSize) {  
        	leaderboard.poll(); // 移除最低分  
        }  
  
        leaderboard.offer(newEntry); // 添加新分数  
	}
	
	/**
	 * 获取TOPN：
	 * 
	 * @param limit
	 * @return
	 */
	public List<PlayerScore> getTopN() {
		List<PlayerScore> topScores = new ArrayList<>(leaderboard);
		// 由于是最小堆PriorityQueue取出的顺序并不是降序，所以需要重新排序
        Collections.sort(topScores, Comparator.comparing(PlayerScore::getScore).reversed()); 
        return topScores;
    }
	
	/**
	 * 排行榜上的第一名玩家
	 * @return
	 */
	public PlayerScore getTopPlayer() {
		// 由于是最小堆PriorityQueue取出的顺序并不是降序，所以需要重新排序
		List<PlayerScore> topScores = new ArrayList<>(leaderboard);
        Collections.sort(topScores, Comparator.comparing(PlayerScore::getScore).reversed()); 
		return topScores.get(0);
    }
}