package effective.java.item7;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class MapRefenceDemo {

   static Map<String, String> map;

   public static void main(String[] args) throws Exception {

       MapRefenceDemo demo = new MapRefenceDemo();       

       System.out.println("强引用测试-----------");
       demo.strongTest();
       System.out.println("gc 发生前：" + map.size());
       System.out.println("开始通知GC");
       //注意，这里只是通过垃圾回收器进行垃圾回收，并不一定马上执行
       System.gc();
       Thread.sleep(1000 * 5);
       System.out.println("gc 发生后：" + map.size());
       
       System.out.println("弱引用测试-----------");
       demo.weakTest();
       System.out.println("gc 发生前：" + map.size());
       System.out.println("开始通知GC");
       //注意，这里只是通过垃圾回收器进行垃圾回收，并不一定马上执行
       System.gc();
       Thread.sleep(1000 * 5);
       System.out.println("gc 发生后：" + map.size());


   }
   
   /**
    * 强引用测试
    */
   public void strongTest() {
       map = new HashMap<>();
       String key = new String("key");
       String value = new String("value");
       map.put(key, value);
       //key和value是局部变量，它随着方法的执行完，这个变量的生命使用周期就结束了==>这里根本不需要手动置null
       key = null;
       value = null;
   }

   /**
    * 弱引用测试
    */
   public void weakTest() {
       map = new WeakHashMap<>();
       String key = new String("key");
       String value = new String("value");
       map.put(key, value);
       //key和value是局部变量，它随着方法的执行完，这个变量的生命使用周期就结束了
//       key = null;
//       value = null;
   }
}