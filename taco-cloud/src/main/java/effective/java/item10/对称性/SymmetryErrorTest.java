package effective.java.item10.对称性;

public class SymmetryErrorTest {
    public static void main(String[] args) {
        Person personAlice = new Person("Alice", 30);
        Person personBob = new Person("Bob", 30);

        // 根据错误的equals实现，只要调用对象的名字是"Alice"，就认为相等
        System.out.println(personAlice.equals(personBob) ? "personAlice认为与personBob相等" : "personAlice认为与personBob不相等");

        // 但是，当比较方向反转，personBob的equals方法没有类似的特殊逻辑，所以应当基于常规比较（本例中应为不相等）
        System.out.println(personBob.equals(personAlice) ? "personBob认为与personAlice相等" : "personBob认为与personAlice不相等");
    }
}
