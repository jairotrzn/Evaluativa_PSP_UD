import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Main_Cliente {
    public static void main(String[] args) {
        String mensaje = "";
        try (
                Socket mySocket = new Socket("localhost", 6000);
                DataInputStream socketIn = new DataInputStream(mySocket.getInputStream());
                DataOutputStream socketOut = new DataOutputStream(mySocket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
        ) {
            do{
                System.out.println("Introduce mensaje para ECO");
                mensaje = scanner.nextLine();
                socketOut.writeUTF(mensaje);
                String response = socketIn.readUTF();
                System.out.println("Eco de server " + response);
            }while (!mensaje.equals("bye"));


        } catch (IOException e) {
            System.err.println(e);
            System.out.println("se ha cerrado en el cliente ");
        }
    }

}