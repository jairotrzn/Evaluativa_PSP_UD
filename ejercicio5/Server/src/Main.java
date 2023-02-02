import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Socket> clientes = new ArrayList<>();

        try(ServerSocket serverSocket = new ServerSocket(6000);
            Socket socket = serverSocket.accept();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream (socket.getOutputStream());
        ) {

            


        }catch (IOException io){
            io.getMessage();
        }
    }
}