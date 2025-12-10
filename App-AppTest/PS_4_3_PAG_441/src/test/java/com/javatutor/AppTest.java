package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    // Test data analysis:
    // 6 numbers: 5, -2, 0, 3, -1, 4
    // Positivos: 5, 3, 4 = 3
    // Negativos: -2, -1 = 2
    // Ceros: 0 = 1

    @Test
    void testContarPositivos() {
        String input = "6\n5\n-2\n0\n3\n-1\n4\n";
        String output = runAppWithInput(input);
        // Verify "Positivos: 3" exists (not just "3")
        assertTrue(output.contains("Positivos: 3") || output.contains("Positivos: 3."),
                "Debe mostrar 'Positivos: 3'");
    }

    @Test
    void testContarNegativos() {
        String input = "6\n5\n-2\n0\n3\n-1\n4\n";
        String output = runAppWithInput(input);
        // Verify "Negativos: 2" exists
        assertTrue(output.contains("Negativos: 2") || output.contains("Negativos: 2."),
                "Debe mostrar 'Negativos: 2'");
    }

    @Test
    void testContarCeros() {
        String input = "6\n5\n-2\n0\n3\n-1\n0\n";
        String output = runAppWithInput(input);
        // Verify "Ceros: 2" exists
        assertTrue(output.contains("Ceros: 2") || output.contains("Ceros: 2."),
                "Debe mostrar 'Ceros: 2'");
    }

    @Test
    void testArregloPositivosSolo() {
        String input = "5\n1\n2\n3\n4\n5\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Positivos: 5") && output.contains("Negativos: 0"),
                "Debe contar 5 positivos, 0 negativos");
    }

    @Test
    void testArregloNegativosSolo() {
        String input = "5\n-1\n-2\n-3\n-4\n-5\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Negativos: 5") && output.contains("Positivos: 0"),
                "Debe contar 5 negativos, 0 positivos");
    }

    @Test
    void testArregloCerosSolo() {
        String input = "4\n0\n0\n0\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Ceros: 4") && output.contains("Positivos: 0") && output.contains("Negativos: 0"),
                "Debe contar 4 ceros, 0 positivos y negativos");
    }

    @Test
    void testArregloVacio() {
        String input = "0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Positivos: 0") && output.contains("Negativos: 0") && output.contains("Ceros: 0"),
                "Debe mostrar 0 para arreglo vacío");
    }

    @Test
    void testArregloUnElementoPositivo() {
        String input = "1\n42\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Positivos: 1") && output.contains("Negativos: 0") && output.contains("Ceros: 0"),
                "Debe contar 1 positivo");
    }

    @Test
    void testArregloUnElementoNegativo() {
        String input = "1\n-42\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Positivos: 0") && output.contains("Negativos: 1") && output.contains("Ceros: 0"),
                "Debe contar 1 negativo");
    }

    @Test
    void testArregloUnElementoCero() {
        String input = "1\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Positivos: 0") && output.contains("Negativos: 0") && output.contains("Ceros: 1"),
                "Debe contar 1 cero");
    }

    @Test
    void testValidacionVariablesDistintas() {
        // CRITICAL: Test where if variables are swapped, test will fail
        // Data: 2 positivos, 3 negativos, 1 cero (all different counts)
        String input = "6\n5\n7\n-1\n-2\n-3\n0\n";
        String output = runAppWithInput(input);

        assertTrue(output.contains("Positivos: 2"),
                "CRÍTICO: Debe mostrar 'Positivos: 2' (no 3 o 1)");
        assertTrue(output.contains("Negativos: 3"),
                "CRÍTICO: Debe mostrar 'Negativos: 3' (no 2 o 1)");
        assertTrue(output.contains("Ceros: 1"),
                "CRÍTICO: Debe mostrar 'Ceros: 1' (no 2 o 3)");

        // If variables are swapped (e.g., positivos = negativos, negativos = positivos)
        // this test would fail because counts would be wrong
    }

    @Test
    void testArregloGrande() {
        String input = "15\n5\n-3\n0\n7\n-1\n0\n2\n-8\n0\n4\n-6\n1\n0\n9\n-2\n";
        String output = runAppWithInput(input);
        // Expected: Positivos: 5, Negativos: 4, Ceros: 4
        // Numbers: 5,7,2,4,1,9 (6 positivos - but one is 9 at end)
        // Negativos: -3,-1,-8,-6,-2 (5 negativos)
        // Ceros: 0,0,0,0 (4 ceros)
        // Total = 6 + 5 + 4 = 15
        assertTrue(output.contains("Positivos: 6") && output.contains("Negativos: 5") && output.contains("Ceros: 4"),
                "Debe contar correctamente en arreglo grande");
    }
}
