package com.javatutor;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese la cantidad de contactos en el directorio: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        
        String[] nombres = new String[cantidad];
        String[] telefonos = new String[cantidad];
        
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese el nombre del contacto " + (i + 1) + ": ");
            nombres[i] = scanner.nextLine();
            System.out.print("Ingrese el teléfono de " + nombres[i] + ": ");
            telefonos[i] = scanner.nextLine();
        }
        
        System.out.print("\nIngrese el nombre a buscar: ");
        String nombreBuscado = scanner.nextLine();
        scanner.close();
        
        System.out.println("\n=== DIRECTORIO TELEFÓNICO ===\n");
        System.out.println("Contactos registrados:");
        for (int i = 0; i < cantidad; i++) {
            System.out.println("  " + nombres[i] + " - " + telefonos[i]);
        }
        
        System.out.println("\nBuscando: " + nombreBuscado);
        
        // Búsqueda lineal del nombre
        int posicion = -1;
        for (int i = 0; i < cantidad; i++) {
            if (nombres[i].equalsIgnoreCase(nombreBuscado)) {
                posicion = i;
                break;
            }
        }
        
        if (posicion != -1) {
            // Nombre encontrado
            System.out.println("✓ Encontrado");
            System.out.println("Número: " + telefonos[posicion]);
        } else {
            // Nombre no encontrado
            System.out.println("✗ No encontrado en el directorio");
        }
    }

}
