package effective.java.Item13;

import java.util.Arrays;

public class ArrayCloneExample {
    public static void main(String[] args) {
        int[] originalArray = {1, 2, 3, 4, 5};
        int[] clonedArray = originalArray.clone(); // 使用clone方法复制数组

        // 改变克隆数组的第一个元素，验证深拷贝
        clonedArray[0] = 10;

        System.out.println("Original Array: " + Arrays.toString(originalArray));
        System.out.println("Cloned Array: " + Arrays.toString(clonedArray));
    }
}
