package com.javatutor; // No borrar el paquete o el codigo nunca va funcionar

// Tampoco la clase, siempre debe ser App
public class App {
    public static void main(String[] args) {
        // Declaración de variables
        int I = 0; // Asignación 1
        int J;
        int ACUM;
        double REA;
        double SUM;
        char CAR;
        boolean BAND;

        // Asignación 2: I = I + 1
        I = I + 1;
        System.out.println("Asignación 2: I = " + I);

        // Asignación 3: ACUM = 0
        ACUM = 0;
        System.out.println("Asignación 3: ACUM = " + ACUM);

        // Asignación 4: J = 5 ** 2 div 3 = 25 / 3 = 8
        J = (int) (Math.pow(5, 2) / 3);
        System.out.println("Asignación 4: J = " + J);

        // Asignación 5: CAR = 'a'
        CAR = 'a';
        System.out.println("Asignación 5: CAR = " + CAR);

        // Asignación 6: ACUM = J div I = 8 / 1 = 8
        ACUM = J / I;
        System.out.println("Asignación 6: ACUM = " + ACUM);

        // Asignación 7: REA = ACUM / 3 = 8 / 3.0 = 2.666...
        REA = ACUM / 3.0;
        System.out.println("Asignación 7: REA = " + REA);

        // Asignación 8: BAND = (8 > 5) v (15 < 2 ** 3) = true OR (15 < 8) = true
        BAND = (8 > 5) || (15 < Math.pow(2, 3));
        System.out.println("Asignación 8: BAND = " + BAND);

        // Asignación 9: SUM = ACUM * 5 / J ** 2 = 8 * 5 / 64 = 40 / 64 = 0.625
        SUM = ACUM * 5 / Math.pow(J, 2);
        System.out.println("Asignación 9: SUM = " + SUM);

        // Asignación 10: I = I * 3 = 1 * 3 = 3
        I = I * 3;
        System.out.println("Asignación 10: I = " + I);

        // Asignación 11: REA = REA / 5 = 2.666... / 5 = 0.533...
        REA = REA / 5;
        System.out.println("Asignación 11: REA = " + REA);

        // Asignación 12: BAND = BAND o (I = J) = true OR (3 = 8) = true OR false = true
        // Pero luego se ejecuta: BAND = BAND || (I == J), pero I=3 y J=8, así que I !=
        // J, resultado es false
        BAND = BAND || (I == J);
        System.out.println("Asignación 12: BAND = " + BAND);

        // Asignación 13: I = REA; // ERROR: No se puede asignar double a int sin cast
        // I = REA; // Esta línea causaría error de compilación
        System.out.println("Asignación 13: ERROR - No se puede asignar double a int sin casting explícito");

        // Asignación 14: CAR = J; // ERROR: No se puede asignar int a char sin cast
        // CAR = J; // Esta línea causaría error de compilación
        System.out.println("Asignación 14: ERROR - No se puede asignar int a char sin casting explícito");

        // Resultados finales
        System.out.println("\n=== RESULTADOS FINALES ===");
        System.out.println("I: " + I);
        System.out.println("J: " + J);
        System.out.println("ACUM: " + ACUM);
        System.out.println("REA: " + REA);
        System.out.println("SUM: " + SUM);
        System.out.println("CAR: " + CAR);
        System.out.println("BAND: " + BAND);
    }
}