package effective.java.item19.demo1;

public class Dog implements Animal {
	
	@Override
	public void eat() {
		System.out.println("Dog eats dog food.");
	}

	@Override
	public void sleep() {
		System.out.println("Dog sleeps in a dog bed.");
	}
	
}
