import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MulticastSocket multicastSocket = null;
        try {
            multicastSocket = new MulticastSocket(6000);
            InetAddress group = InetAddress.getByName("225.0.0.1");
            multicastSocket.joinGroup(group);

            byte[] buffer = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            multicastSocket.receive(datagramPacket);

            String mensaje = "Hola estoy en el grupo";
            DatagramPacket
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}