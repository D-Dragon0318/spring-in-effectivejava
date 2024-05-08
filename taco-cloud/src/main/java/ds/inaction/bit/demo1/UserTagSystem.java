package ds.inaction.bit.demo1;

import java.util.BitSet;

public class UserTagSystem {
	// 假设有32个标签，这里为了简化示例，使用固定大小的BitSet
	private static final int TAG_COUNT = 32;
	private static final int USER_COUNT = 1000;
	
	private static final BitSet[] userTags = new BitSet[USER_COUNT]; // 假设有1000个用户
	
	private static final BitSet[] tagUsers = new BitSet[TAG_COUNT]; // 假设有32个标签

	public static void main(String[] args) {
		// 初始化用户标签
		initUserTags();

		// 示例操作
		int userId = 1;
		int tagIndex = 5;
		addTag(userId, tagIndex); // 为用户1添加标签5
		System.out.println("User " + userId + " has tag 5: " + hasTag(userId, 5)); // 应输出: true

		removeTag(userId, 9); // 尝试移除不存在的标签9，不会影响位图
		System.out.println("User " + userId + " has tag 9 after removal attempt: " + hasTag(userId, 9)); // 应输出: false

		printTags(userId); // 打印用户1的所有标签
		printUsers(tagIndex);// 打印标签5的所有用户
	}

	private static void initUserTags() {
		//一个用户对应一个标签位图
		for (int i = 0; i < userTags.length; i++) {
			userTags[i] = new BitSet(TAG_COUNT);
		}
		//一个标签对应一个用户位图
		for (int i = 0; i < tagUsers.length; i++) {
			tagUsers[i] = new BitSet(USER_COUNT);
		}
	}

	// 为用户添加标签
	public static void addTag(int userId, int tagIndex) {
		if (userId >= 0 && userId < userTags.length && tagIndex >= 0 && tagIndex < TAG_COUNT) {
			userTags[userId].set(tagIndex);
			tagUsers[tagIndex].set(userId);
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
			tagUsers[tagIndex].clear(userId);
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
	
	// 查找某标签的所有用户
	public static void printUsers(int tagIndex) {
		BitSet users = tagUsers[tagIndex];
		StringBuilder userList = new StringBuilder();
		for (int i = users.nextSetBit(0); i >= 0; i = users.nextSetBit(i + 1)) {
			userList.append(i).append(" ");
		}
		System.out.println("标签 " + tagIndex + " has users: " + (userList.length() > 0 ? userList.toString().trim() : "None"));
	}
	
}
