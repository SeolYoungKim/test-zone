package net.udpsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramSocketServerSample {
    public static void main(String[] args) {
        DatagramSocketServerSample socketServerSample = new DatagramSocketServerSample();
        socketServerSample.startServer();
    }

    private void startServer() {
        try(DatagramSocket serverSocket = new DatagramSocket(9999)) {
            int bufferLength = 256;
            byte[] buffer = new byte[bufferLength];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, bufferLength);
            while (true) {
                System.out.println("Server: Waiting for request.");
                serverSocket.receive(datagramPacket);

                int dataLength = datagramPacket.getLength();
                System.out.println("Server: Received. Data length=" + dataLength);

                String data = new String(datagramPacket.getData(), 0, dataLength);
                System.out.println("Received data: " + data);

                if (data.equals("EXIT")) {
                    System.out.println("Stop DatagramServer");
                    break;
                }
                System.out.println("=====================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
