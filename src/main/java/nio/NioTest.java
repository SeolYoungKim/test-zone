package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {
    public static void main(String[] args) throws Exception {
        String fileName = "src/main/java/nio/nio.txt";
        write(fileName, "A dirty world that only remembers English");
        read(fileName);
    }

    private static void write(String fileName, String data) throws Exception {
        System.out.println("=============Start Write=============");

        byte[] dataBytes = data.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(dataBytes);
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        FileChannel channel = new FileOutputStream(fileName).getChannel();
        int ioStatus = channel.write(buffer);
        System.out.println("ioStatus = " + ioStatus);
        channel.close();
    }

    public static void read(String fileName) throws Exception {
        System.out.println("=============Start Read=============");

        FileChannel channel = new FileInputStream(fileName).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("buffer.capacity() = " + buffer.capacity());
        System.out.println("buffer.limit() = " + buffer.limit());
        System.out.println("buffer.position() = " + buffer.position());

        int ioStatus = channel.read(buffer);
        System.out.println("ioStatus = " + ioStatus);

        System.out.println("=============Start Read Buffer=============");
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println("buffer.position() = " + buffer.position());
            System.out.println((char) buffer.get());
        }

        channel.close();
    }
}
