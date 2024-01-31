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

        System.out.println("=============channel read buffer=============");
        // 채널에 file이 가진 byte 수 만큼의 데이터가 있음 -> buffer에 담음 -> buffer의 position이 파일의 byte수 만큼 증가
        int ioStatus = channel.read(buffer);  // position = file의 byte 수, limit = 1024
        System.out.println("ioStatus = " + ioStatus);
        System.out.println("buffer.position() = " + buffer.position());
        System.out.println("buffer.limit() = " + buffer.limit());

        System.out.println("=============Start Read Buffer=============");
//        buffer.flip();  // limit = position, position = 0 으로 바꾼다는 것은, 데이터가 있는 만큼만 읽을 수 있게 해준다. (낭비 x)
        buffer.rewind();  // 얘는 position만 변경해서, 1024 길이만큼의 데이터를 다 읽는다. 설령 그 뒤가 빈 값이라 하더라도.
        System.out.println("buffer.position() = " + buffer.position());
        System.out.println("buffer.limit() = " + buffer.limit());

        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }

        channel.close();
    }
}
