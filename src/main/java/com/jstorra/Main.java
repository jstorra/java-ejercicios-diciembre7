package com.jstorra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("""
                        \n---------------------------------
                        Escogé el ejercicio a ejecutar:

                        1. Cifrado César
                        2. Numero romano a entero
                        3. Validación correo
                        4. Conversión fecha
                        5. Combinaciones entre cadenas
                        6. Salir
                        """);
                System.out.print("Opcion: ");
                int opcion = scanner.nextInt();
                System.out.println("---------------------------------");

                if (opcion == 1) {
                    ejercicio1();
                } else if (opcion == 2) {
                    ejercicio2();
                } else if (opcion == 3) {
                    ejercicio3();
                } else if (opcion == 4) {
                    ejercicio4();
                } else if (opcion == 5) {
                    ejercicio5();
                } else if (opcion == 6) {
                    System.out.println("\nSaliendo...");
                    break;
                } else {
                    System.out.println("\nERROR: La opción ingresada no es válida.");
                }
            }
        } catch (Exception e) {
            System.out.println("\nERROR: El carácter ingresado no es válido.");
        }
    }

    public static void ejercicio1() {
        // 1. Escribe un programa en Java que tome una cadena de texto y la encripte
        // utilizando el cifrado de César.
        // El cifrado de César consiste en desplazar cada letra de la cadena por un
        // número fijo de posiciones en el alfabeto.

        Scanner scanner = new Scanner(System.in);

        int numeroLetrasAlfabeto = 26;
        int desplazamiento = 3;

        System.out.print("\nIngresa un texto: ");
        String texto = scanner.nextLine();
        char[] textoArray = texto.toCharArray();
        String textoEncriptado = "";

        for (int i = 0; i < textoArray.length; i++) {
            if (!Character.isLetterOrDigit(textoArray[i]) || Character.isDigit(textoArray[i])) {
                textoEncriptado += textoArray[i];
                continue;
            }
            int asciiPrimeraLetra = Character.isUpperCase(textoArray[i]) ? (int) 'A' : (int) 'a';
            int posicionOriginal = textoArray[i] - asciiPrimeraLetra;
            int nuevaPosicion = (posicionOriginal + desplazamiento) % numeroLetrasAlfabeto;
            int nuevoValorAscii = asciiPrimeraLetra + nuevaPosicion;
            textoEncriptado += (char) nuevoValorAscii;
        }
        System.out.println("\nTexto encriptado: " + textoEncriptado);
    }

    public static void ejercicio2() {
        // 2. Escribe un programa en Java que tome una cadena que represente un número
        // romano y lo convierta a un número entero.

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIngresa un número romano: ");
        String numeroRomano = scanner.nextLine().toUpperCase();

        int resultado = 0;

        for (int i = 0; i < numeroRomano.length(); i++) {
            int valorActual = obtenerValorRomano(numeroRomano.charAt(i));
            if (valorActual == -1) {
                System.out.println("\nERROR: Carácter romano no válido \"" + numeroRomano.charAt(i) + "\".");
                break;
            } else {
                if (i < numeroRomano.length() - 1) {
                    int valorSiguiente = obtenerValorRomano(numeroRomano.charAt(i + 1));
                    if (valorSiguiente == -1) {
                        System.out.println("\nERROR: Carácter romano no válido \"" + numeroRomano.charAt(i + 1) + "\".");
                        break;
                    } else {
                        if (valorActual < valorSiguiente) {
                        resultado -= valorActual;
                        } else {
                            resultado += valorActual;
                        }
                    }
                } else {
                    resultado += valorActual;
                }
            }
        }
        if (resultado > 0) {
            System.out.println("\nNumero entero: " + resultado);
        }
    }

    public static int obtenerValorRomano(char caracter) {
        switch (caracter) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }

    public static void ejercicio3() {
        // 3. Escribe un programa en Java que valide si una cadena representa una
        // dirección de correo electrónico.
        // La dirección de correo debe tener el formato estándar de "usuario@dominio".

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIngresa el correo electronico: ");
        String correo = scanner.nextLine();

        String patron = "^[a-zA-Z0-9+-_&]+@[a-zA-Z0-9]+\\.[a-zA-Z]+{2,4}$";

        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(correo);
        
        if (matcher.matches()) {
            System.out.println("\nMENSAJE: El correo electronico es correcto!!");
        } else {
            System.out.println("\nERROR: El correo electronico no es válido.");
        }
    }

    public static void ejercicio4() {
        // 4. Escribe un programa en Java que tome una fecha en formato "dd/mm/yyyy" y
        // la convierta al formato "yyyy-mm-dd".

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIngresa una fecha en formato dd/mm/yyyy: ");
        String fechaOriginal = scanner.nextLine();

        try {
            SimpleDateFormat formatoOriginal = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate = formatoOriginal.parse(fechaOriginal);

            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fechaDate);

            if (calendario.get(Calendar.DAY_OF_MONTH) < 1 || calendario.get(Calendar.DAY_OF_MONTH) > 31) {
                System.out.println("\nERROR: El día ingresado no es válido.");
            } else if (calendario.get(Calendar.MONTH) < 1 || calendario.get(Calendar.MONTH) > 12) {
                System.out.println("\nERROR: El mes ingresado no es válido.");
            } else if (calendario.get(Calendar.YEAR) < 1900) {
                System.out.println("\nERROR: El año ingresado no es válido.");
            } else {
                SimpleDateFormat formatoNuevo = new SimpleDateFormat("yyyy-MM-dd");
                String fechaConvertida = formatoNuevo.format(fechaDate);
                System.out.println("\nFecha convertida: " + fechaConvertida);
            }
        } catch (ParseException e) {
            System.out.println("\nERROR: Asegúrate de ingresar la fecha en formato dd/mm/yyyy.");
        }
    }

    public static void ejercicio5() {
        // 5. Escribe un programa en Java que tome dos cadenas y genere todas las
        // posibles combinaciones de las letras de ambas cadenas.
        // Cada combinación debe contener exactamente una letra de cada cadena.

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIngresa una palabra: ");
        String primerPalabra = scanner.nextLine();  
        System.out.print("\nIngresa otra palabra: ");
        String segundaPalabra = scanner.nextLine();

        System.out.println("\nCombinaciones:");
        for (char letra_primerPalabra : primerPalabra.toCharArray()) {
            for (char letra_segundaPalabra : segundaPalabra.toCharArray()) {
                System.out.println("- " + letra_primerPalabra + "" + letra_segundaPalabra);
            }
        }
    }
}