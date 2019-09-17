package servion;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServiOn {

    static int nsocket;
    static Socket socket;
    static ServerSocket socketServidor;

    public static void main(String[] args) {
        try {
            try {
                nsocket = Integer.parseInt(args[args.length - 1]);
            } catch (Exception e) {
                System.out.println("No se introdujo un socket valido");
                System.exit(0);
            }
            try {
                socketServidor = new ServerSocket(nsocket);
                socket = socketServidor.accept();
            } catch (Exception e) {
                System.out.println("No se pudo crear el servidor");
                System.exit(1);
            }
            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String entrada = "_";
            Scanner scanner = new Scanner(System.in);
            String salida;
            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            do {
                entrada = lector.readLine();
                System.out.println(entrada);
                if (entrada.equalsIgnoreCase("fin")) {
                    System.out.println("Me voy");
                    socket.close();
                    socketServidor.close();
                    System.exit(0);
                }

                salida = scanner.nextLine();
                escritor.println(salida);

            } while (!entrada.equalsIgnoreCase("fin"));
        } catch (IOException e) {
            System.out.println("Reintentar");
        }
    }

}
