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
        byte[] dataBytes = data.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(dataBytes);

        FileChannel channel = new FileOutputStream(fileName).getChannel();
        int ioStatus = channel.write(buffer);
        System.out.println("ioStatus = " + ioStatus);
        channel.close();
    }

    public static void read(String fileName) throws Exception {
        FileChannel channel = new FileInputStream(fileName).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int ioStatus = channel.read(buffer);
        System.out.println("ioStatus = " + ioStatus);

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }

        channel.close();
    }
}
