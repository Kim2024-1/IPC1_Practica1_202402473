package javaapplication4;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class JavaApplication4 {
    private static int tamano = 15;
    private static char[][] MatrizSopa = new char[tamano][tamano];
    private static ArrayList<String> palabras = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);
    private static Random rand = new Random();
    private static boolean esMayusculas = true;
    private static ArrayList<Jugador> jugadores = new ArrayList<>();

    public static void main(String[] args) {
        boolean cerrar = false;
        while (!cerrar) {
            String nombre = "";

            System.out.println("-----Comenzar Partida-----");
            System.out.print("Ingrese su nombre (Obligatorio): ");
            nombre = scan.nextLine();
            while (nombre.trim().isEmpty()) {
                

                if (nombre.trim().isEmpty()) {
                    System.out.println("El nombre es obligatorio. Por favor, ingrese su nombre.");
                }
            }

            System.out.println("Seleccione una opcion:");
            System.out.println("1. Menu");
            System.out.println("2. Jugar");
            System.out.println("3. Terminar Partida");
            System.out.print("Opcion a seleccionar: ");
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    boolean subcerrar = false;
                    do {
                        System.out.println("1. Insertar");
                        System.out.println("2. Modificar");
                        System.out.println("3. Eliminar");
                        System.out.println("4. Salir");
                        System.out.print("Opcion a seleccionar: ");
                        int subopcion = leerOpcion();
                        scan.nextLine();

                        switch (subopcion) {
                            case 1: // Insertar
                                System.out.println("Ingrese el numero de palabras que desea agregar:");
                                int numeroPal = leerOpcion();
                                scan.nextLine();

                                for (int i = 0; i < numeroPal; i++) {
                                    System.out.print("Ingrese la palabra: ");
                                    String palabra = scan.nextLine();

                                    if (palabra.length() >= 3 && palabra.length() <= 8) {
                                        palabras.add(palabra);
                                        if (i == 0) {
                                            esMayusculas = Character.isUpperCase(palabra.charAt(0));
                                        }
                                    } else {
                                        System.out.println("Error: La palabra debe tener entre 3 a 8 caracteres.");
                                        i--;
                                    }
                                }
                                break;

                            case 2: // Modificar
                                System.out.print("Ingrese la palabra que va a modificar: ");
                                String palabraAntigua = scan.nextLine();

                                if (palabras.contains(palabraAntigua)) {
                                    System.out.print("Ingrese la nueva palabra: ");
                                    String palabraNueva = scan.nextLine();

                                    if (palabraNueva.length() >= 3 && palabraNueva.length() <= 8) {
                                        int lista = palabras.indexOf(palabraAntigua);
                                        palabras.set(lista, palabraNueva);
                                        System.out.println("La palabra '" + palabraAntigua + "' se ha cambiado correctamente por: '" + palabraNueva + "'.");
                                    } else {
                                        System.out.println("Error: La palabra debe tener entre 3 a 8 caracteres.");
                                    }
                                } else {
                                    System.out.println("Error: La palabra no se encuentra en el banco de palabras.");
                                }
                                break;

                            case 3: // Eliminar
                                System.out.print("Ingrese la palabra a eliminar: ");
                                String palabra = scan.nextLine();

                                if (palabras.remove(palabra)) {
                                    System.out.println("Palabra eliminada correctamente.");
                                } else {
                                    System.out.println("Error: La palabra no se encuentra en el banco de palabras.");
                                }
                                break;

                            case 4: // Salir
                                subcerrar = true;
                                break;
                        }
                    } while (!subcerrar);
                    break;

                case 2: // Jugar
                    if (palabras.isEmpty()) {
                        System.out.println("Aun no han sido insertadas palabras.");
                        break;
                    }

                    inicializarMatriz();
                    ArrayList<String> palabrasCopia = new ArrayList<>(palabras);

                    insertarPalabras();
                    rellenarMatriz();

                    boolean jugar = true;
                    int palabrasEncontradas = 0;

                    while (jugar = true) {
                        System.out.println("La sopa de letras es:");
                        imprimirMatriz();

                        System.out.print("Escribe la palabra que encontraste (o escribe 'salir' para terminar): ");
                        String palabraEncontrada = scan.nextLine().trim();

                        if (palabraEncontrada.equalsIgnoreCase("salir")) {
                            jugar = false;
                        } else if (palabraEncontrada.isEmpty()) {
                            System.out.println("La palabra ingresada está vacía.");
                        } else if (palabrasCopia.contains(palabraEncontrada)) {
                            if (marcarPalabras(palabraEncontrada)) {
                                palabrasCopia.remove(palabraEncontrada);
                                palabrasEncontradas++;
                                System.out.println("¡Palabra encontrada! Palabras restantes: " + palabrasCopia.size());

                            } else {
                                System.out.println("La palabra '" + palabraEncontrada + "' no se encontró en la sopa de letras.");
                            }
                        } else {
                            System.out.println("La palabra '" + palabraEncontrada + "' no está en la lista de palabras.");
                        }

                        if (palabrasCopia.isEmpty()) {
                            imprimirMatriz();
                            System.out.println("¡Felicidades! Has encontrado todas las palabras.");
                            jugar = false;
                        }
                    }

                    // Guardar el resultado del jugador
                    Jugador jugador = new Jugador(nombre, palabrasEncontradas * 10, 0, palabrasEncontradas);
                    jugadores.add(jugador);
                    break;

                case 3: // Terminar Partida
                    mostrarMenuTerminarPartida();
                    cerrar = true;
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        scan.close();
    }

    private static void mostrarMenuTerminarPartida() {
        boolean terminarCerrar = false;
        while (!terminarCerrar) {
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Historial de partidas");
            System.out.println("2. Puntuaciones más altas");
            System.out.println("3. Información del estudiante");
            System.out.println("4. Salir");
            System.out.print("Opcion a seleccionar: ");
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    mostrarHistorialPartidas();
                    break;

                case 2:
                    mostrarPuntuacionesAltas();
                    break;

                case 3:
                    mostrarInformacionEstudiante();
                    break;

                case 4:
                    terminarCerrar = true;
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    private static void mostrarHistorialPartidas() {
        System.out.println("Historial de Partidas:");
        for (Jugador jugador : jugadores) {
            System.out.println("Nombre: " + jugador.nombre + ", Puntos: " + jugador.puntuacion +
                    ", Fallos: " + jugador.fallos + ", Palabras Encontradas: " + jugador.palabrasEncontradas);
        }
    }

    private static void mostrarPuntuacionesAltas() {
        System.out.println("Puntuaciones Más Altas:");
        Collections.sort(jugadores, Comparator.comparingInt(j -> -j.puntuacion));
        for (int i = 0; i < Math.min(3, jugadores.size()); i++) {
            Jugador jugador = jugadores.get(i);
            System.out.println((i + 1) + ". Nombre: " + jugador.nombre + ", Puntos: " + jugador.puntuacion);
        }
    }

    private static void mostrarInformacionEstudiante() {
        System.out.println("Informacion del creador:");
        System.out.println("Kimberly Samantha Gomez Chavez");
        System.out.println("Carne: 202402473");
        System.out.println("Seccion:B ");
        
    }

    private static int leerOpcion() {
        while (!scan.hasNextInt()) {
            System.out.println("Error: Debe ingresar un número.");
            scan.next();
        }
        return scan.nextInt();
    }

    private static void inicializarMatriz() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                MatrizSopa[i][j] = '.';
            }
        }
    }

    private static void insertarPalabras() {
        for (String palabra : palabras) {
            boolean colocada = false;
            while (!colocada) {
                int fila = rand.nextInt(tamano);
                int col = rand.nextInt(tamano);
                int direccion = rand.nextInt(2); // 0: horizontal, 1: vertical

                if (direccion == 0 && col + palabra.length() <= tamano) {
                    boolean puedeColocar = true;
                    for (int i = 0; i < palabra.length(); i++) {
                        if (MatrizSopa[fila][col + i] != '.') {
                            puedeColocar = false;
                            break;
                        }
                    }
                    if (puedeColocar) {
                        for (int i = 0; i < palabra.length(); i++) {
                            MatrizSopa[fila][col + i] = esMayusculas ? Character.toUpperCase(palabra.charAt(i)) : Character.toLowerCase(palabra.charAt(i));
                        }
                        colocada = true;
                    }
                } else if (direccion == 1 && fila + palabra.length() <= tamano) {
                    boolean puedeColocar = true;
                    for (int i = 0; i < palabra.length(); i++) {
                        if (MatrizSopa[fila + i][col] != '.') {
                            puedeColocar = false;
                            break;
                        }
                    }
                    if (puedeColocar) {
                        for (int i = 0; i < palabra.length(); i++) {
                            MatrizSopa[fila + i][col] = esMayusculas ? Character.toUpperCase(palabra.charAt(i)) : Character.toLowerCase(palabra.charAt(i));
                        }
                        colocada = true;
                    }
                }
            }
        }
    }

    private static void rellenarMatriz() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (MatrizSopa[i][j] == '.') {
                    MatrizSopa[i][j] = esMayusculas ? (char) ('A' + rand.nextInt(26)) : (char) ('a' + rand.nextInt(26));
                }
            }
        }
    }

    private static void imprimirMatriz() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                System.out.print(MatrizSopa[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean marcarPalabras(String word) {
        if (word == null || word.isEmpty()) {
            System.out.println("Error: La palabra ingresada está vacía.");
            return false;
        }

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (MatrizSopa[i][j] == word.charAt(0)) {
                    // Verificar horizontal
                    boolean found = true;
                    for (int k = 0; k < word.length(); k++) {
                        if (j + k >= tamano || MatrizSopa[i][j + k] != word.charAt(k)) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        for (int k = 0; k < word.length(); k++) {
                            MatrizSopa[i][j + k] = '#';
                        }
                        return true;
                    }

                    // Verificar vertical
                    found = true;
                    for (int k = 0; k < word.length(); k++) {
                        if (i + k >= tamano || MatrizSopa[i + k][j] != word.charAt(k)) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        for (int k = 0; k < word.length(); k++) {
                            MatrizSopa[i + k][j] = '#';
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static class Jugador {
        String nombre;
        int puntuacion;
        int fallos;
        int palabrasEncontradas;

        public Jugador(String nombre, int puntuacion, int fallos, int palabrasEncontradas) {
            this.nombre = nombre;
            this.puntuacion = puntuacion;
            this.fallos = fallos;
            this.palabrasEncontradas = palabrasEncontradas;
        }
    }
}