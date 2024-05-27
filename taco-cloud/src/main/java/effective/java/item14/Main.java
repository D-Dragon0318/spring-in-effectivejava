package effective.java.item14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 创建一些Book实例
        Book book1 = new Book("Java编程思想", "Bruce Eckel", 2006);
        Book book2 = new Book("Clean Code", "Robert C. Martin", 2008);
        Book book3 = new Book("设计模式:可复用面向对象软件的基础", "Erich Gamma", 1995);
        Book book4 = new Book("重构:改善既有代码的设计", "Martin Fowler", 1999);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        System.out.println("原始书籍列表:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + ", " + book.getPublicationYear());
        }

        // 基于书名排序
        Collections.sort(books);
        System.out.println("\n按书名排序:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + ", " + book.getPublicationYear());
        }

        // 使用AuthorComparator按作者排序
        Collections.sort(books, new AuthorComparator());
        System.out.println("\n按作者姓氏排序:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + ", " + book.getPublicationYear());
        }

        // 重置列表状态为原始顺序，以便再次排序
        books.clear();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        // 使用PublicationYearComparator按出版年份降序排序
        Collections.sort(books, new PublicationYearComparator());
        System.out.println("\n按出版年份降序排序:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + ", " + book.getPublicationYear());
        }
    }
}

