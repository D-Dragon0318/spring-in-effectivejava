package effective.java.item19.demo1;

public class Cat implements Animal { 
	
    @Override  
    public void eat() {  
        System.out.println("Cat eats cat food.");  
    }  
  
    @Override  
    public void sleep() {  
        System.out.println("Cat sleeps in a cat bed.");  
    }  
    
}
