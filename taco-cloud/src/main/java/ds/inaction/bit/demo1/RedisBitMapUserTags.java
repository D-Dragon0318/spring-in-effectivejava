package ds.inaction.bit.demo1;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisBitMapUserTags {
	
	private Jedis jedis;
	private static final String USER_TAGS_KEY_PREFIX = "user:tags:";
	
	// 假定我们有预定义的标签列表与位的映射，这里简化处理，实际应用中这应该根据你的设计来
	private static final String[]  tags = {"技术", "音乐", "旅行", "阅读", "运动"}; 

	public RedisBitMapUserTags() {
		jedis = new Jedis("localhost", 6379);
	}

	// 为用户设置标签（假设标签ID从0开始）
	public void setTagForUser(int userId, int tagId) {
		jedis.setbit(USER_TAGS_KEY_PREFIX + userId, tagId, true);
	}

	// 检查用户是否有指定标签
	public boolean hasTagForUser(int userId, int tagId) {
		return jedis.getbit(USER_TAGS_KEY_PREFIX + userId, tagId);
	}

	// 移除用户标签
	public void removeTagForUser(int userId, int tagId) {
		jedis.setbit(USER_TAGS_KEY_PREFIX + userId, tagId, false);
	}
	
	// 查找某个用户身上的所有标签：可以通过遍历位图的方式来实现。不过，这种方法在标签数量很大时效率较低。
	public void printTags(int userId) {
		StringBuilder tagList = new StringBuilder();
        for (int i = 0; i < tags.length; i++) {
            boolean hasTag = jedis.getbit(USER_TAGS_KEY_PREFIX + userId, i);
            if (hasTag) {
    			tagList.append(tags[i]).append(" ");
            }
        }
		System.out.println("User " + userId + " has tags: " + (tagList.length() > 0 ? tagList.toString().trim() : "None"));
	}
	
	//更高效的方式是为每个用户存储一个Set类型的键，包含其所有标签的ID。
	// 添加标签到用户
	public void addTagToUser(int userId, String tag) {
		jedis.sadd(USER_TAGS_KEY_PREFIX + userId, tag);
	}	
	public void printTagsEff(int userId) {
		Set<String> userTags = jedis.smembers(USER_TAGS_KEY_PREFIX + userId);
		System.out.println("User " + userId + " has tags: " + userTags.toString());		
	}

	public static void main(String[] args) {
		RedisBitMapUserTags tagManager = new RedisBitMapUserTags();

		int userId = 123;
		int tagId = 5;

		tagManager.setTagForUser(userId, tagId);
		System.out.println("User " + userId + " has tag " + tagId + "? " + tagManager.hasTagForUser(userId, tagId));

		tagManager.removeTagForUser(userId, tagId);
		System.out.println("User " + userId + " has tag " + tagId + " after removal? "
				+ tagManager.hasTagForUser(userId, tagId));
		
		tagManager.printTags(userId);
		
		tagManager.addTagToUser(userId, tags[0]);
		tagManager.printTagsEff(userId);
		
		// 关闭Jedis连接
		tagManager.jedis.close();
	}
}
