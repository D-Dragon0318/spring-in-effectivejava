package effective.java.item7.message;

public class Main {
    public static void main(String[] args) {
        MessageManager manager = new MessageManager();

        // 添加消息
        manager.addMessage(new Message("Hello, World!"));
        manager.addMessage(new Message("Goodbye, World!"));

        // 强制触发一次垃圾回收，模拟消息对象可能被回收的情况
        System.gc();

        // 打印消息，检查哪些消息还存在
        manager.printMessages();

        // 注意：在实际应用中，垃圾回收的时间不可控，此处仅用于演示
        // 在没有其他对Message对象的强引用，并且发生垃圾回收的情况下，部分或全部消息可能已被回收
    }
}
