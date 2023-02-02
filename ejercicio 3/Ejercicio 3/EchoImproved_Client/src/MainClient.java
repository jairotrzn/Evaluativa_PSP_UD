import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce ip de conexion: ");
        String ip = scanner.nextLine();
        final String BYE = "bye";
        String mensaje = "";


        try (
                Socket mySocket = new Socket(ip, 6000);
                DataInputStream socketIn = new DataInputStream(mySocket.getInputStream());
                DataOutputStream socketOut = new DataOutputStream(mySocket.getOutputStream());
        ) {

            do {
                System.out.println("Introduce mensaje para eco : ");
                mensaje = scanner.nextLine();
                socketOut.writeUTF(mensaje);
                String response = socketIn.readUTF();
                System.out.println("Recibido: " + response);

            } while (!mensaje.equals(BYE));

        } catch (UnknownHostException e) {
            System.err.println("Host Offline");
        } catch (IOException e) {
            System.err.println(e);
        }

        System.out.println("Exit");
        scanner.close();

    }
}