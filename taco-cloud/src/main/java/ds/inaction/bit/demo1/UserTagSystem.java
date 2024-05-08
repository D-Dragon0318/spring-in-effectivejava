package ds.inaction.bit.demo1;

import java.util.BitSet;

public class UserTagSystem {
	// 假设有32个标签，这里为了简化示例，使用固定大小的BitSet
	private static final int TAG_COUNT = 32;
	
	private static final BitSet[] userTags = new BitSet[1000]; // 假设有1000个用户

	public static void main(String[] args) {
		// 初始化用户标签
		initUserTags();

		// 示例操作
		int userId = 1;
		addTag(userId, 5); // 为用户1添加标签5
		System.out.println("User " + userId + " has tag 5: " + hasTag(userId, 5)); // 应输出: true

		removeTag(userId, 9); // 尝试移除不存在的标签9，不会影响位图
		System.out.println("User " + userId + " has tag 9 after removal attempt: " + hasTag(userId, 9)); // 应输出: false

		printTags(userId); // 打印用户1的所有标签
	}

	private static void initUserTags() {
		for (int i = 0; i < userTags.length; i++) {
			userTags[i] = new BitSet(TAG_COUNT);
		}
	}

	// 为用户添加标签
	public static void addTag(int userId, int tagIndex) {
		if (userId >= 0 && userId < userTags.length && tagIndex >= 0 && tagIndex < TAG_COUNT) {
			userTags[userId].set(tagIndex);
		} else {
			System.out.println("Invalid user ID or tag index.");
		}
	}

	// 判断某个用户是否有某个标签
	public static boolean hasTag(int userId, int tagIndex) {
		if (userId >= 0 && userId < userTags.length && tagIndex >= 0 && tagIndex < TAG_COUNT) {
			return userTags[userId].get(tagIndex);
		}
		return false;
	}

	// 移除用户指定标签
	public static void removeTag(int userId, int tagIndex) {
		if (userId >= 0 && userId < userTags.length && tagIndex >= 0 && tagIndex < TAG_COUNT) {
			userTags[userId].clear(tagIndex);
		} else {
			System.out.println("Invalid user ID or tag index.");
		}
	}

	// 查找某个用户身上的所有标签
	public static void printTags(int userId) {
		BitSet tags = userTags[userId];
		StringBuilder tagList = new StringBuilder();
		for (int i = tags.nextSetBit(0); i >= 0; i = tags.nextSetBit(i + 1)) {
			tagList.append(i).append(" ");
		}
		System.out.println("User " + userId + " has tags: " + (tagList.length() > 0 ? tagList.toString().trim() : "None"));
	}
}
