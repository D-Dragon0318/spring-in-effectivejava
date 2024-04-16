package ds.inaction;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class demo {
	public static void main(String[] args) {
		Leaderboard leaderboard = new Leaderboard();
		AtomicInteger counter = new AtomicInteger(0); // 全局唯一的计数器模拟时间戳
		leaderboard.addScore("player1", 100, counter.incrementAndGet());
		leaderboard.addScore("player2", 200, counter.incrementAndGet());
		leaderboard.addScore("player3", 150, counter.incrementAndGet());
		leaderboard.addScore("player4", 50, counter.incrementAndGet());
		leaderboard.addScore("player5", 220, counter.incrementAndGet());
		leaderboard.addScore("player6", 360, counter.incrementAndGet());
		leaderboard.addScore("player7", 360, counter.incrementAndGet());

		// 输出前3名玩家
		List<PlayerScore> topPlayers = leaderboard.getTopN(3);
		for (PlayerScore player : topPlayers) {
			System.out.println("Player ID: " + player.getPlayerId() + ", Score: " + player.getScore());
		}
		
		// 输出第1名玩家
	    PlayerScore topPlayer = leaderboard.getTopPlayer();
	    if (topPlayer != null) {
	        System.out.println("Top player 第一名: " + topPlayer.getPlayerId() + ", Score: " + topPlayer.getScore());
	    } else {
	        System.out.println("Leaderboard is empty!");
	    }
	}
}