package effective.java.item7.message;

public class Message {
	private String content;

	public Message(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Message{" + "content='" + content + '\'' + '}';
	}
}
