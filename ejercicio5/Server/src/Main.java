import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Socket> clientes = new ArrayList<>();
        ArrayList<Product> productos = new ArrayList<>();
        try (ServerSocket serverSocket = new ServerSocket(6000);
             Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println("Escuchando...");
            while (clientes.size() != 2) {
                Socket socket = serverSocket.accept();
                clientes.add(socket);
                System.out.println("Clientes conectados a la subasta " + clientes.size());
            }
            System.out.println("Introduce nombre del producto a subastar");
            String nombre = scanner.nextLine();

            System.out.println("Introduce precio inicial de la puja");
            double precio = scanner.nextDouble();

            Product product = new Product(nombre,precio);
            for (Socket cliente : clientes) {
                ServerThread serverThread = new ServerThread(cliente,product);
                serverThread.start();
               // productos.add(serverThread.getProduct());
                //ObjectOutputStream objectOutputStream = new ObjectOutputStream(cliente.getOutputStream());
                //objectOutputStream.writeObject(product);

                ObjectInputStream objectInputStream = new ObjectInputStream(cliente.getInputStream());
                productos.add((Product) objectInputStream.readObject());

            }

            for (Product products : productos) {
                System.out.print("Los datos son " + products.getNombreProducto() + products.getNombreComprador() +
                        products.getPrecio() + "  y la longitud es " + productos.size());
            }

            System.out.println("El ganador de la puja es :" + obtenerGanador(productos));
        } catch (IOException io) {
            io.getMessage();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String obtenerGanador(ArrayList<Product> productos) {
        String linea = "";
        double precio = 0;
        Product productGanador = null;
        for (Product product : productos) {
            if (product.getPrecio() > precio) {
                productGanador = product;
            }
        }
        return productGanador.nombreComprador + " " + productGanador.getPrecio();
    }
}