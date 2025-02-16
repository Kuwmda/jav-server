package servidor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ServicioPass {

    private static final String MAYUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITOS = "0123456789";
    private static final String CHAR = "!@#$%^&*()_-+=.:?";

    public String generarPassword(RequisitosPass requisitos) {
        Random random = new Random();
        List<Character> password = new ArrayList<>();

        for (int i = 0; i < requisitos.getNumMayusculas(); i++) {
            password.add(MAYUS.charAt(random.nextInt(MAYUS.length())));
        }
        for (int i = 0; i < requisitos.getNumMinusculas(); i++) {
            password.add(MINUS.charAt(random.nextInt(MINUS.length())));
        }
        for (int i = 0; i < requisitos.getNumDigitos(); i++) {
            password.add(DIGITOS.charAt(random.nextInt(DIGITOS.length())));
        }
        for (int i = 0; i < requisitos.getNumCaractEspeciales(); i++) {
            password.add(CHAR.charAt(random.nextInt(CHAR.length())));
        }

        Collections.shuffle(password);
        StringBuilder result = new StringBuilder();
        for (char c : password) {
            result.append(c);
        }

        return result.toString();
    }

    public int obtenerLongitud(RequisitosPass requisitos) {
        return requisitos.getNumMayusculas() + requisitos.getNumMinusculas() +
               requisitos.getNumDigitos() + requisitos.getNumCaractEspeciales();
    }
}

