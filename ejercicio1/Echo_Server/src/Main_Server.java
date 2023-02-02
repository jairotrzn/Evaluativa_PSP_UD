import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main_Server {
    public static void main(String[] args) {
        String message ="";
        try (
                ServerSocket server = new ServerSocket(6000);
                Socket service = server.accept();
                DataInputStream socketIn = new DataInputStream(service.getInputStream());
                DataOutputStream socketOut = new DataOutputStream(service.getOutputStream());
        ) {

            do{  message = socketIn.readUTF();
                System.out.println("Received: " + message);
                socketOut.writeUTF(message.toUpperCase());}while (!message.equals("bye"));


        } catch (IOException e) {
            System.err.println(e);
            System.out.println("se ha cerrado en el server ");
        }
    }

}