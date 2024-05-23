package effective.java.item10;

public class TestBook {
	public static void main(String[] args) {
		Book book1 = new Book("Java编程思想", "Bruce Eckel", 2006);
		Book book2 = new Book("Java编程思想", "Bruce Eckel", 2006);
		Book book3 = new Book("Effective Java", "Joshua Bloch", 2008);

		System.out.println("book1 和 book2 是否相等: " + book1.equals(book2)); // 应输出 true
		System.out.println("book1 和 book3 是否相等: " + book1.equals(book3)); // 应输出 false
	}
}
