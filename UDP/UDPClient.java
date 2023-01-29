import java.io.*;
import java.net.*;
// import java.util.Scanner;

public class UDPClient {
    public static void main(String args[]) throws Exception {
        // Scanner sc = new Scanner(System.in);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("21September");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        while (true) {
            System.out.print("Enter the sentence to be sent to the server: ");
            String sentence = inFromUser.readLine();
            if(sentence.isEmpty()){
                break;
            }
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String dateTime = new String(receivePacket.getData());
            System.out.println("DATE TIME FROM SERVER:" + dateTime);
        }
        // clientSocket.close();
        // sc.close();
    }
}
