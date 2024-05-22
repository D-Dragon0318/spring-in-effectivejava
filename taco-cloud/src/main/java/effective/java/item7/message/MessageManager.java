package effective.java.item7.message;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private List<WeakReference<Message>> messages = new ArrayList<>();

    public void addMessage(Message msg) {
        messages.add(new WeakReference<>(msg));
    }

    /**
     * 安全地获取并打印消息内容，检查消息是否已被回收。
     */
    public void printMessages() {
        List<WeakReference<Message>> toBeRemoved = new ArrayList<>(); // 用于存储已回收对象的引用，稍后清理
        for (WeakReference<Message> ref : messages) {
            Message msg = ref.get();
            if (msg != null) {
                System.out.println(msg);
            } else {
                toBeRemoved.add(ref); // 记录已回收的引用
            }
        }
        // 清理已被回收的对象引用
        messages.removeAll(toBeRemoved);
    }
}
