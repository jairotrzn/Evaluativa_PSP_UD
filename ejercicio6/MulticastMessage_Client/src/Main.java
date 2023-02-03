import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


    }

    public static String obtenerMensaje(Scanner scanner){
        System.out.println("Escribe mensaje");
        return scanner.nextLine();
    }
}