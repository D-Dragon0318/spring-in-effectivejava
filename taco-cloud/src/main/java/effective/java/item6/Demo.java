package effective.java.item6;

import java.util.regex.Pattern;

public class Demo {
	
	private static final Pattern ROMAN_PATTERN = Pattern.compile("^M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
	
	public static void main(String[] args) {
//		testString();
//		testStringEffect();
//		testRoman();
//		testRomanEffect();
		testBoxing();
		testBoxingEffect();
	}

	private static void testString() {
		long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            String s = new String("test");
        }
        long endTime = System.nanoTime();
        System.out.println("构造器创建字符串耗时: " + (endTime - startTime)/1000 + " μs");
	}
	
	private static void testStringEffect() {
		long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            String s = "test";
        }
        long endTime = System.nanoTime();
        System.out.println("使用字面量耗时: " + (endTime - startTime)/1000 + " μs");
	}
	
	private static void testRoman() {
		long startTime = System.nanoTime();
		boolean flag = false;
        for (int i = 0; i < 1000; i++) {
            String s = "XIV";
            flag = s.matches("^M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
        }
        long endTime = System.nanoTime();
        System.out.println("未优化正则表达式耗时: " + (endTime - startTime)/1000 + " μs" + ", 结果：" + flag);
	}
	
	private static void testRomanEffect() {
		long startTime = System.nanoTime();
		boolean flag = false;
        for (int i = 0; i < 100; i++) {
            String s = "XIV";
            flag = ROMAN_PATTERN.matcher(s).matches();
        }
        long endTime = System.nanoTime();
        System.out.println("优化后正则表达式耗时: " + (endTime - startTime)/1000 + " μs" + ", 结果：" + flag);
	}
	
	private static void testBoxing() {
		long startTime = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("自动装箱耗时: " + (endTime - startTime) + " ms"+ ", 结果：" + sum);
	}
	
	private static void testBoxingEffect() {
		long startTime = System.currentTimeMillis();
        long sum = 0;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用基本类型耗时: " + (endTime - startTime) + " ms"+ ", 结果：" + sum);
	}
	
}

