
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Se ha arrancado el servidor");
        try {
            MulticastSocket multicastSocket = new MulticastSocket();
            InetAddress grupo = InetAddress.getByName("231.0.0.1");
            Scanner scanner = new Scanner(System.in);
            String linea = "";
            while (!linea.equals("finish")){
                System.out.println("Escribe mensaje para enviar");
                linea = scanner.nextLine();
                DatagramPacket datagramPacket = new DatagramPacket(linea.getBytes(),linea.length(),grupo,6000);
                multicastSocket.send(datagramPacket);
            };
            scanner.close();
            multicastSocket.leaveGroup(grupo);
            multicastSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}