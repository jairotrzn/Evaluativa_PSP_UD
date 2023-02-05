
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class Main {

    public static void main(String[] args) {
        System.out.println("Conexion cliente");
        try {
            MulticastSocket multicastSocket = new MulticastSocket(6000);
            InetAddress grupo = InetAddress.getByName("231.0.0.1");
            multicastSocket.joinGroup(grupo);

            String salida = new String();
            while (!salida.equals("finish")){
                byte[]buffer = new byte[1024];
                DatagramPacket datagramPacketReciv = new DatagramPacket (buffer, buffer.length);
                multicastSocket.receive(datagramPacketReciv);
                String mensaje = new String(datagramPacketReciv.getData());
                System.out.println(" \n Mensaje recivido " + mensaje);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}