package effective.java.item9;

import java.io.IOException;  
  
public class TryFinallyExample {
    static class MyResource {
        public void close() throws IOException {
            // 模拟资源关闭时可能抛出的异常
            throw new IOException("资源关闭时的异常");
        }
    }

    public static void main(String[] args) {
        MyResource resource = new MyResource();
        try {
            // 业务逻辑部分，这里故意抛出一个异常模拟业务异常
            throw new IllegalArgumentException("业务逻辑中的异常");
        } finally {
            try {
                resource.close(); // 这里可能抛出IOException
            } catch (IOException e) {
                // 如果没有正确处理，这里会覆盖掉原始异常
                throw new RuntimeException("资源关闭异常", e); 
            }
        }
    }
}
