package net.udpsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class DatagramSocketClientSample {
    public static void main(String[] args) {
        DatagramSocketClientSample socketClientSample = new DatagramSocketClientSample();
        socketClientSample.sendSocketSample();
    }

    private void sendSocketSample() {
        for (int i = 0; i < 3; i++) {
            sendSocketData("I liked UDP " + LocalDateTime.now());
        }

        sendSocketData("EXIT");
    }

    private void sendSocketData(String data) {
        System.out.println("Client: Connecting");
        try (DatagramSocket client = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            byte[] buffer = data.getBytes();

            DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length, address, 9999);
            client.send(datagramPacket);
            System.out.println("Client: Sent data");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
