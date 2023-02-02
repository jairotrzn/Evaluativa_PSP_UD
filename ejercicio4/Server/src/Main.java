
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Conectando ...");
        try (
                ServerSocket serverSocket = new ServerSocket(6000);
                Socket socket = serverSocket.accept();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ){
                System.out.println("Conexion establecida");
                User user = new User();
                objectOutputStream.writeObject(user);

                User userFinal = (User) objectInputStream.readObject();
                System.out.println("User name :" + userFinal.getName() + " Password: " + userFinal.getPassword() + " Date: " + userFinal.getRegistro() );

        }catch (IOException io){
            io.getMessage();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}