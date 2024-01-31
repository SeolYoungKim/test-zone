package nio;

import java.nio.IntBuffer;

public class BufferTest {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(1024);
        System.out.println("buffer.position() = " + buffer.position());
        for (int i = 0; i < 100; i++) {
            buffer.put(i);
        }

        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());
        System.out.println("buffer.remaining() = " + buffer.remaining());

        System.out.println();
        buffer.flip();
        System.out.println("Buffer is flipped");
        System.out.println();

        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());
        System.out.println("buffer.remaining() = " + buffer.remaining());
    }
}
