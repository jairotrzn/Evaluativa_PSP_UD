import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    System.out.println("Soy el cliente");
        try ( Socket socket = new Socket("localhost",6000);
              ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
              ObjectOutputStream objectOutputStream = new ObjectOutputStream((socket.getOutputStream()));
              Scanner scanner = new Scanner(System.in))
        {

            Product producto = (Product) objectInputStream.readObject();
            mostrarDatos(producto);
            modificarProducto(producto,datosComprador(scanner));
            objectOutputStream.writeObject(producto);
            System.out.println("Los datos que voy a devolver son" + producto.nombreProducto + producto.getNombreComprador() + producto.getPrecio() );

        }catch (IOException io){
            io.getMessage();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarDatos (Product product){
        System.out.println("Product name :" + product.getNombreProducto() + "\n" +
                "Product initial price : " + product.getPrecio());
    }

    public static String[] datosComprador(Scanner scanner){
        System.out.println("\n Introduce nombre y precio de puja");
        return scanner.nextLine().split(" ");
    }

    public static Product modificarProducto (Product product , String[] datos){

        product.setNombreComprador(datos[0]);
        product.setPrecio(Double.valueOf(datos[1]));

        return  product;
    }
}