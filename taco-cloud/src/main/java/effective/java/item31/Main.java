package effective.java.item31;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<String> strings = Arrays.asList("a", "b", "c");
        System.out.println(ComparableList.findMax(integers));;// 正确
        System.out.println(ComparableList.findMax(strings));;// 正确

        System.out.println(ComparableList.getMax(integers));;// 正确
        System.out.println(ComparableList.getMax(strings));;// 正确



        List<Animal> mixedAnimals = Arrays.asList(new Dog(), new Cat());
        List<Dog> dogs = Arrays.asList(new Dog(), new Dog());
        List<Cat> cats = Arrays.asList(new Cat(), new Cat());
//        ComparableList.findMax(dogs); // 错误，即使 Dog 的父类实现实现了Comparable<Animal>
//        ComparableList.findMax(cats); // 错误，即使 Cat 的父类实现实现了Comparable<Animal>
        ComparableList.findMax(mixedAnimals); // 正确，因为列表直接包含 Animal 对象


        ComparableList.getMax(dogs); // 正确，因为 Dog 是 Animal 的子类，因为Animal已经实现了Comparable<Animal>
        ComparableList.getMax(cats); // 正确，因为 Cat 也是 Animal 的子类，因为Animal已经实现了Comparable<Animal>
        ComparableList.getMax(mixedAnimals); // 正确，因为列表直接包含 Animal 对象
    }
}
