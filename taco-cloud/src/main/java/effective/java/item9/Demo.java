package effective.java.item9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo {

	public static void main(String[] args) {
//		testTryFinally();
//		testTryResource();
		testTryFinallyMulti();
		testTryResourceMulti();
	}

	public static void testTryFinally() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("path/to/file.txt");
			// 读取文件内容...
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到：" + e.getMessage());
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("关闭文件时出错：" + e.getMessage());
				}
			}
		}
	}

	public static void testTryResource() {
		try (FileInputStream fis = new FileInputStream("path/to/file.txt")) {
			// 读取文件内容...
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到：" + e.getMessage());
		} catch (IOException e) {
			System.out.println("读取文件时出错：" + e.getMessage());
		}
	}

	public static void testTryFinallyMulti() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("source.txt");
			fos = new FileOutputStream("destination.txt");
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到：" + e.getMessage());
		} catch (IOException e) {
			System.out.println("文件读写错误：" + e.getMessage());
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				System.out.println("关闭输入流时出错：" + e.getMessage());
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				System.out.println("关闭输出流时出错：" + e.getMessage());
			}
		}
	}

	public static void testTryResourceMulti() {
		try (FileInputStream fis = new FileInputStream("source.txt");
				FileOutputStream fos = new FileOutputStream("destination.txt")) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到：" + e.getMessage());
		} catch (IOException e) {
			System.out.println("文件读写错误：" + e.getMessage());
		}
	}

}
