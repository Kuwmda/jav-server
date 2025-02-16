package servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4321)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("Cliente conectado.");

                    // Flujo de interacci�n con el cliente
                    out.println("Servidor: Bienvenido. ¿Como te llamas?");
                    String nombreCliente = in.readLine();
                    out.println("Servidor: Hola, " + nombreCliente + ". Vamos a generar tu contraseña.");

                    RequisitosPass requisitos = new RequisitosPass();

                        requisitos.setNumMayusculas(solicitarNumero("¿Cuantas mayusculas tendrá tu contraseña?", in, out));
                    requisitos.setNumMinusculas(solicitarNumero("¿Cuantas minusculas tendrá tu contraseña?", in, out));
                    requisitos.setNumDigitos(solicitarNumero("¿Cuantos digitos tendrá tu contraseña?", in, out));
                    requisitos.setNumCaractEspeciales(solicitarNumero("¿Cuantos caracteres tendrá tu contraseña?", in, out));

                    ServicioPass servicio = new ServicioPass();
                    int longitud = servicio.obtenerLongitud(requisitos);
                    out.println("Servidor: La longitud de tu contraseña sera de " + longitud + " caracteres.");
                    out.println("Servidor: ¿Quieres generar tu contraseña ahora? (si/no)");

                    if (in.readLine().equalsIgnoreCase("si")) {
                        String password = servicio.generarPassword(requisitos);
                        out.println("Servidor: Aqui tienes tu contraseña: " + password);
                    } else {
                        out.println("Servidor: No se generara tu contraseña.");
                    }

                    System.out.println("Cliente desconectado.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solicitarNumero(String mensaje, BufferedReader in, PrintWriter out) throws IOException {
        while (true) {
            out.println("Servidor: " + mensaje);
            try {
                int numero = Integer.parseInt(in.readLine());
                if (numero >= 0) {
                    return numero;
                }
                out.println("Servidor: El numero debe ser mayor o igual que 0.");
            } catch (NumberFormatException e) {
                out.println("Servidor: Por favor, introduce un numero valido.");
            }
        }
    }
}
