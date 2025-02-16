package cliente;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] argumentos) {
        try (Socket conexion = new Socket("localhost", 4321);
             BufferedReader lectorServidor = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
             PrintWriter escritorServidor = new PrintWriter(conexion.getOutputStream(), true);
             BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in))) {

            String mensajeServidor;

            while ((mensajeServidor = lectorServidor.readLine()) != null) {
                System.out.println(mensajeServidor);
                if (mensajeServidor.contains("?")) {
                    escritorServidor.println(entradaUsuario.readLine());
                }
            }
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }
}