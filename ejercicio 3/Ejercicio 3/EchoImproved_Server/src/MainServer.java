import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        final int PORT = 6000;
    // Aplico el constructor de serverSoket con nuemero de puerto y numero maximo de conexiones simultaneas

        try(ServerSocket server = new ServerSocket(PORT,10);){
            System.out.println("Escuchando...");
            while (true){
                Socket service = server.accept();
                System.out.println("Conexion establecida ");
                ServerThread serverThread = new ServerThread(service);
                serverThread.start();
            }

        } catch (IOException exception) {
            System.err.println(exception);
        }

    }
}