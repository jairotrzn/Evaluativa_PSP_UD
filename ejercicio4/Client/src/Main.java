
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (
                Socket socket = new Socket("localhost",6000);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in);
            ){
            User userCompleto = (User) objectInputStream.readObject();
            System.out.println("Intoduce nombre de usuario");
            String nameUser = scanner.nextLine();
            System.out.println("Introduce password");
            String password = scanner.nextLine();

            userCompleto.setName(nameUser);
            userCompleto.setPassword(password);

            objectOutputStream.writeObject(userCompleto);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException io){
            io.getMessage();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}