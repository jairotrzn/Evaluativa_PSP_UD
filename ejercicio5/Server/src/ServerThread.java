import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread{
    Socket socket;
    Product product;

    public ServerThread (Socket socket, Product product){

        this.socket = socket;
        this.product = product;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(product);

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Product producto = (Product) objectInputStream.readObject();

        }catch (IOException io){
            io.getMessage();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getProduct(){
        return this.product;
    }
}
