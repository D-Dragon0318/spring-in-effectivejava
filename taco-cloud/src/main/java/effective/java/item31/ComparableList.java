package effective.java.item31;

import java.util.List;

public class ComparableList {

    public static <T extends Comparable<T>> T findMax(List<T> list) {
        T max = list.get(0);
        for (T element : list) {
            if (max == null || element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    // 使用? extends Comparable<T> 表示列表中的元素类型必须是Comparable的某个子类型
    public static <T extends Comparable<T>> T getMax(List<? extends T> list) {
        T max = list.get(0);
        for (T element : list) {
            if (max == null || element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }
}
