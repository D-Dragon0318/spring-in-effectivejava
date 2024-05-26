package effective.java.Item13;

public class ReferenceTypeArrayCloneExample {
    public static void main(String[] args) {
        // 创建Person对象
        Person alice = new Person("Alice", 30);
        Person bob = new Person("Bob", 25);

        // 创建Person对象的引用类型数组
        Person[] originalArray = new Person[2];
        originalArray[0] = alice;
        originalArray[1] = bob;

        // 使用clone方法进行数组的浅复制
        Person[] copiedArray = originalArray.clone(); // 注意这里使用了数组的clone方法

        // 修改复制数组中的第一个元素的属性，观察原始数组是否受影响
        copiedArray[0].setName("Charlie");
        copiedArray[0].setAge(28);

        System.out.println("Original Array:");
        for (Person person : originalArray) {
            System.out.println(person.getName() + ", " + person.getAge());
        }

        System.out.println("\nCopied Array:");
        for (Person person : copiedArray) {
            System.out.println(person.getName() + ", " + person.getAge());
        }
    }
}
