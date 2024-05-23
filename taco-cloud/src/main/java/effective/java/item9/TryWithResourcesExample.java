package effective.java.item9;

import java.io.*;

public class TryWithResourcesExample {
    static class MyResource implements AutoCloseable {
        public void close() throws IOException {
            // 模拟资源关闭时可能抛出的异常
            throw new IOException("资源关闭时的异常");
        }
    }

    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            // 业务逻辑部分，这里故意抛出一个异常模拟业务异常
            throw new IllegalArgumentException("业务逻辑中的异常");
        } catch (Exception e) {
            System.out.println("捕获到异常: " + e.getMessage());
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("被抑制的异常: " + suppressed.getMessage());
            }
        }
    }
}
