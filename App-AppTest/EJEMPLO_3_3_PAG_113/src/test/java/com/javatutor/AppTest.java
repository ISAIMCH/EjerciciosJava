package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Pruebas unitarias para la clase App (Ejemplo 3.3)
 * 
 * Valida el conteo correcto de ceros en una secuencia de números
 */
class AppTest {

    private String runAppWithInput(String input) {
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));

        try {
            App.main(new String[] {});
        } catch (Exception e) {
            fail("El programa lanzó una excepción: " + e.getMessage());
        }

        System.setIn(System.in);
        System.setOut(System.out);

        return outContent.toString();
    }

    @Test
    void testContarCerosCaso1() {
        // 5 números: 0, 0, 5, 0, 10 -> 3 ceros
        String input = "5\n0\n0\n5\n0\n10\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("3"), "Debe contar 3 ceros");
        assertTrue(
                output.toUpperCase().contains("RESULTADO") || output.toUpperCase().contains("CERO")
                        || output.toUpperCase().contains("ENCONTRADO"),
                "Debe mostrar formato claro con palabra RESULTADO o CERO");
    }

    @Test
    void testContarCerosCaso2() {
        // 4 números: 10, 20, 30, 40 -> 0 ceros
        String input = "4\n10\n20\n30\n40\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("0"), "Debe mostrar 0 ceros cuando no hay ceros");
    }

    @Test
    void testContarCerosCaso3() {
        // 3 números: 0, 0, 0 -> 3 ceros
        String input = "3\n0\n0\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("3"), "Debe contar 3 ceros");
    }

    @Test
    void testContarCerosSoloCeros() {
        // 5 números todos ceros -> 5 ceros
        String input = "5\n0\n0\n0\n0\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("5"), "Debe contar 5 ceros cuando todos son ceros");
    }

    @Test
    void testContarCerosNingunoCero() {
        // 3 números sin ceros -> 0 ceros
        String input = "3\n1\n2\n3\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("0"), "Debe mostrar 0 ceros cuando no hay ceros");
    }

    @Test
    void testContarCerosUnoCero() {
        // 2 números: 5, 0 -> 1 cero
        String input = "2\n5\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1"), "Debe contar 1 cero");
    }

    @Test
    void testContarCerosNumeroGrande() {
        // 4 números: 100, 0, 200, 0 -> 2 ceros
        String input = "4\n100\n0\n200\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("2"), "Debe contar 2 ceros");
    }

    @Test
    void testContarCerosConNumerosNegativos() {
        // 5 números: -1, 0, -5, 0, 10 -> 2 ceros
        String input = "5\n-1\n0\n-5\n0\n10\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("2"), "Debe contar 2 ceros incluso con números negativos");
    }

    @Test
    void testFormatoSalida() {
        String input = "2\n5\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.length() > 20, "La salida debe ser significativa");
        assertFalse(output.contains("11") || output.contains("01"),
                "No debe concatenar de forma extraña");
    }

    @Test
    void testSalidaCompleta() {
        String input = "3\n0\n0\n5\n";
        String output = runAppWithInput(input);
        assertTrue(output.length() > 20, "La salida debe ser completa");
        assertTrue(output.contains("2"), "Debe contener el resultado correcto");
    }

    @Test
    void testSalidaNoVacia() {
        String input = "1\n0\n";
        String output = runAppWithInput(input);
        assertNotNull(output, "La salida no debe ser nula");
        assertFalse(output.isEmpty(), "La salida debe contener contenido");
    }

    @Test
    void testCantidadCorrectaProcesada() {
        // Verificar que procesa exactamente N números
        String input = "3\n0\n1\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("2"), "Debe procesar exactamente 3 números y encontrar 2 ceros");
    }

    @Test
    void testMuchosCeros() {
        // 10 números con múltiples ceros
        String input = "10\n0\n1\n0\n2\n0\n3\n0\n4\n0\n5\n";
        String output = runAppWithInput(input);
        // Hay 5 ceros (posiciones 0, 2, 4, 6, 8)
        assertTrue(output.contains("5"), "Debe contar 5 ceros correctamente");
    }

    @Test
    void testResultadoCero() {
        // Casos donde el resultado es 0
        String input = "2\n5\n10\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("0"), "Debe mostrar 0 cuando no hay ceros");
        assertTrue(
                output.toUpperCase().contains("ENCONTRADO") || output.toUpperCase().contains("CERO")
                        || output.toUpperCase().contains("TOTAL"),
                "Debe mostrar formato claro del resultado");
    }
}
