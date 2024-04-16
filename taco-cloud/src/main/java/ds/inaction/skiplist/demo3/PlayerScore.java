package ds.inaction.skiplist.demo3;

/**
 * 玩家信息（最小堆）
 */
public class PlayerScore implements Comparable<PlayerScore> {
	private String playerId;
	private int score;
	private final int timestamp;

	public PlayerScore(String playerId, int score, int timestamp) {
		this.playerId = playerId;
		this.score = score;
		this.timestamp = timestamp;
	}

	public String getPlayerId() {
		return playerId;
	}

	public int getScore() {
		return score;
	}
	
	public int getTimestamp() {
        return timestamp;
    }
	
	// 实现Comparable接口，根据分数和timestamp排序
    @Override
    public int compareTo(PlayerScore other) {
    	//升序：实现最小堆
        int scoreComparison = Integer.compare(this.score, other.score);
        if (scoreComparison != 0) {
            return scoreComparison;
        } else {
            // 当分数相同时，根据timestamp排序
            return Integer.compare(this.timestamp, other.timestamp);
        }
    }
	
}