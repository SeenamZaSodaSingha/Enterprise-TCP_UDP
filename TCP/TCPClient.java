import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        int num;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("21September", 5000);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while (true) {
            for (int i = 0; i < 2; i++) {
                System.out.printf("Enter number %d: ", i + 1);
                String strIn = inFromUser.readLine();
                if (strIn.isEmpty()) {
                    outToServer.writeBytes(strIn + '\n');
                    System.exit(1);
                }
                num = Integer.parseInt(strIn);
                // System.out.println("CLIENT NUM " + i + " IN: " + num);
                outToServer.writeBytes(Integer.toString(num) + '\n');
            }
            System.out.println("The result is " + inFromServer.readLine());
        }
    }
}
