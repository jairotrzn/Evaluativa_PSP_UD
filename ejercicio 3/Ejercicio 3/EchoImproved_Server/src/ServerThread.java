import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

    Socket service;
    String message;

    public ServerThread(Socket service) {
        this.service = service;
        this.message ="";
    }

    @Override
    public void run() {

            DataInputStream socketIn = null;
            DataOutputStream socketOut = null;

            try {

                socketIn = new DataInputStream(service.getInputStream());
                socketOut = new DataOutputStream(service.getOutputStream());

                do {
                    message = socketIn.readUTF();
                    System.out.println("Recibido: " + message);
                    socketOut.writeUTF(message.toUpperCase());
                } while (!message.equals("bye"));

            } catch (IOException exception) {
                System.err.println(exception);
            }finally {
                try {
                    if(socketOut != null){
                        socketOut.close();
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                try {
                    if(socketIn != null){
                        socketIn.close();
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                try {
                    if(service != null){
                        service.close();
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

        System.out.println("Cerrando conexion");

    }
}
