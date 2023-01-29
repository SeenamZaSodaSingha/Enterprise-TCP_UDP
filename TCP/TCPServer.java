import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String[] args) throws Exception {
        int num1 = 0, num2 = 0, sum = 0;
        String inLine = "";

        ServerSocket welcomeSocket = new ServerSocket(5000);
        System.out.println("Waiting for client connection at port number 5000");
        Socket connectionSocket = welcomeSocket.accept();

        contol: while (true) {
            System.out.println("USER CONNECTED");
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            inLine = inFromClient.readLine();
            if (inLine.isEmpty()) {
                System.out.println("USER DISCONNECTED");
                break contol;
            }
            // System.out.println("INPUT 1 NOT EMPTY FROM SERVER");
            num1 = Integer.parseInt(inLine);
            // System.out.println("NUM 1 FROM SERVER = " + num1);
            inLine = inFromClient.readLine();
            // System.out.println("INPUT 2 NOT EMPTY FROM SERVER");
            num2 = Integer.parseInt(inLine);
            // System.out.println("NUM 2 FROM SERVER = " + num2);
            sum = num1+num2;
            // System.out.println("SUM FROM SERVER: " + sum);

            outToClient.writeBytes(Integer.toString(sum) + '\n');
            sum = 0;
        }
        welcomeSocket.close();
    }
}