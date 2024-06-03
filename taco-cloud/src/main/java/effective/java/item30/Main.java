package effective.java.item30;

public class Main {
    public static void main(String[] args) {
        // 测试整型数组
        Integer[] intArray = {1, 5, 3, 9, 2};
        System.out.println("Max in integer array: " + ArrayUtils.findMax(intArray)); // 应输出 9
        
        // 测试字符串数组
        String[] stringArray = {"apple", "banana", "cherry"};
        System.out.println("Max in string array: " + ArrayUtils.findMax(stringArray)); // 应输出 "cherry"
        
        // 注意：对于自定义类使用findMax，这些类需要实现Comparable接口并重写compareTo方法
        // 以下为一个简化的自定义类示例
        class MyComparable implements Comparable<MyComparable> {
            int value;
            
            public MyComparable(int value) {
                this.value = value;
            }
            
            @Override
            public int compareTo(MyComparable o) {
                return Integer.compare(this.value, o.value);
            }

			@Override
			public String toString() {
				return "MyComparable [value=" + value + "]";
			}
        }
        
        // 测试自定义类型数组
        MyComparable[] myComparableArray = {new MyComparable(10), new MyComparable(5), new MyComparable(20)};
        System.out.println("Max in MyComparable array: " + ArrayUtils.findMax(myComparableArray)); // 应输出 对象表示的20
    }
}
