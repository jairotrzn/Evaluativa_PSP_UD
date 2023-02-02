import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main_Client {
    public static void main(String[] args) {

        try (
                DatagramSocket mySocket = new DatagramSocket();
                Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println("Introuce palabra para traducir");
            String palabra = scanner.nextLine();
            byte[] message = palabra.getBytes();
            DatagramPacket packetS = new DatagramPacket(message, message.length, InetAddress.getLocalHost(), 6000);
            mySocket.send(packetS);

            byte[] buffer = new byte[1024];
            DatagramPacket packetR = new DatagramPacket(buffer, buffer.length);
            mySocket.setSoTimeout(5);
            mySocket.receive(packetR);
            System.out.println("La traduccion de : " + palabra + " es " +  new String(packetR.getData()).trim());

        } catch (SocketTimeoutException time){
            System.err.println("No translation found");

        } catch (IOException e) {
            System.err.println(e);
        }
    }

}