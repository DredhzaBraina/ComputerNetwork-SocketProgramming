import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTCPSocket {

    public static final int PORT = 80;
	public static void main(String argv[]) {


        BufferedReader inputKeyboard = new BufferedReader(new InputStreamReader(System.in));

        Socket socket;

        InetAddress ipAddress;

        String host = "";

        String head = "";

        String input_message = "";

        try {

            System.out.println("Host to connect?");

            host = inputKeyboard.readLine();
            head = "HEAD / HTTP/1.1\r\n"
                    + "Host: "+ host +"\r\n"
                    + "\r";
            ipAddress = InetAddress.getByName(host);
			socket = new Socket(ipAddress, PORT);

            DataOutputStream ouput = new DataOutputStream(socket.getOutputStream());


            System.out.println("\nSending info...");
            System.out.println(head);
            System.out.println("===============================");

            ouput.writeUTF(head);
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            while ((input_message = inputFromServer.readLine()) != null) {
                System.out.println(input_message);
            }
            System.out.println("===============================");
			 socket.close();
            inputFromServer.close();

        } catch (IOException e) {
            System.out.println("Alguna cosa ha anat malament");
            e.printStackTrace();
        }
    }
}