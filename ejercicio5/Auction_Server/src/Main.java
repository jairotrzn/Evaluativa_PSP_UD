import java.io.DataOutputStream;
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
        ObjectInputStream objectInputStream;
        try (ServerSocket serverSocket = new ServerSocket(6000);
             Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println("Escuchando...");
            while (clientes.size() != 3) {
                Socket socket = serverSocket.accept();
                clientes.add(socket);
                System.out.println("Clientes conectados a la subasta " + clientes.size());
            }
//            System.out.println("Introduce nombre del producto a subastar");
//            String nombre = scanner.nextLine();
//
//            System.out.println("Introduce precio inicial de la puja");
//            double precio = scanner.nextDouble();

            Product product = new Product("XBox", 300);
            for (Socket cliente : clientes) {
                ServerThread serverThread = new ServerThread(cliente, product);
                serverThread.start();
            }
            for(Socket socket : clientes){
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                productos.add((Product) objectInputStream.readObject());
            }
            Product productoFinal =  obtenerGanador(productos);
            String linea = "EL GANADOR DE LA SUBASTA ES: " + productoFinal.getNombreComprador() + " " + productoFinal.getPrecio();
            for (Socket socket : clientes) {

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(linea);
            }


        } catch (IOException io) {
            io.getMessage();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Product obtenerGanador(ArrayList<Product> productos) {
        String linea = "";
        double precio = 0;
        Product productGanador = null;
        for (Product product : productos) {
            if (product.getPrecio() > precio) {
                productGanador = product;
            }
        }
        return productGanador;
    }
}