package effective.java.item14;

import java.util.Comparator;

public class PublicationYearComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return Integer.compare(b2.getPublicationYear(), b1.getPublicationYear()); // 从新到旧排序
    }
}
