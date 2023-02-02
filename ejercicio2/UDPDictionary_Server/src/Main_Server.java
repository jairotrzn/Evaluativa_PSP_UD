
import java.net.*;
import java.io.*;
import java.util.HashMap;


public class Main_Server {
    public static void main(String[] args) {
        HashMap<String,String> diccionario = new HashMap<String,String>();
        diccionario.put("uno","one");
        diccionario.put("dos","two");
        diccionario.put("tres","three");
        diccionario.put("cuatro","four");
        diccionario.put("cinco","five");
        diccionario.put("seis","six");
        diccionario.put("siete","seven");
        diccionario.put("Ocho","eigth");
        diccionario.put("nueve","nine");
        diccionario.put("diez","ten");

        try (DatagramSocket mySocket = new DatagramSocket(6000, InetAddress.getLocalHost())) {

            byte[] buffer = new byte[1024];
            DatagramPacket packetR = new DatagramPacket(buffer, buffer.length);
            mySocket.receive(packetR);
            String palabra = new String(packetR.getData()).trim();
            System.out.println("Voy a traducir: " + palabra);

            int destPort = packetR.getPort();
            InetAddress destAddr = packetR.getAddress();


            String traduccion = diccionario.get(palabra);
            byte[] message = traduccion.getBytes();
            DatagramPacket packetS = new DatagramPacket(message, message.length, destAddr, destPort);
            mySocket.send(packetS);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

}