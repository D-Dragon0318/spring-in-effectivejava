package effective.java.item19.demo1;

public interface Animal {
	void eat();
	void sleep();

	default void makeSound() {
		System.out.println("This animal makes a generic sound.");
	}
}
