import java.io.*;
import java.net.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class UDPServer {
    public static void main(String args[]) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            if(sentence.isEmpty()){
                break;
            }
            LocalDateTime now = LocalDateTime.now();  

            sendData = (dtf.format(now)).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);

            serverSocket.send(sendPacket);
        }
    }
}
