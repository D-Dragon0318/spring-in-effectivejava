package effective.java.item10;

public class Book {
	private String title;
	private String author;
	private int publicationYear;	

	public Book(String title, String author, int publicationYear) {
		super();
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { // 自反性：检查是否是同一个对象
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false; // 非null性：确保比较对象不为null且类型相同
		}

		Book other = (Book) obj; // 安全转换

		// 比较所有关键域
		return this.title.equals(other.title) && this.author.equals(other.author)
				&& this.publicationYear == other.publicationYear;
	}

	@Override
	public int hashCode() {
		// 为了遵循equals和hashCode的约定，也需要重写hashCode方法
		int result = 17;
		result = 31 * result + title.hashCode();
		result = 31 * result + author.hashCode();
		result = 31 * result + publicationYear;
		return result;
	}
}