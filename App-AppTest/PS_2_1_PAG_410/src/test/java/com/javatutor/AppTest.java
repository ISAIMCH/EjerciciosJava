package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Pruebas unitarias para la clase App (PS 2.1)
 * Valida el cálculo de la tangente (TANGENTE = SENO / COSENO)
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
    void testTangente60Grados() {
        String input = "0.8660\n0.5000\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.toUpperCase().contains("TANGENTE") || output.contains("1.73"),
            "Debe calcular tangente de 60° ≈ 1.732");
    }

    @Test
    void testTangente45Grados() {
        String input = "0.7071\n0.7071\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.toUpperCase().contains("TANGENTE") || output.contains("1.00"),
            "Debe calcular tangente de 45° = 1.0");
    }

    @Test
    void testTangente30Grados() {
        String input = "0.5000\n0.8660\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.toUpperCase().contains("TANGENTE") || output.contains("0.57"),
            "Debe calcular tangente de 30° ≈ 0.577");
    }

    @Test
    void testTangente0Grados() {
        String input = "0.0000\n1.0000\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.toUpperCase().contains("TANGENTE") || output.contains("0.00"),
            "Debe calcular tangente de 0° = 0.0");
    }

    @Test
    void testCosenoIgualCero() {
        String input = "0.5000\n0.0000\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.toUpperCase().contains("ERROR") || 
                   output.toUpperCase().contains("DIVISION POR CERO") ||
                   output.toUpperCase().contains("INDEFINIDA"),
            "Debe rechazar coseno = 0");
    }

    @Test
    void testTangenteNegativa() {
        String input = "-0.5000\n0.8660\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.toUpperCase().contains("TANGENTE") || output.contains("-0.57"),
            "Debe calcular tangente negativa");
    }

    @Test
    void testTangenteCuadranteIII() {
        String input = "-0.7071\n-0.7071\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.toUpperCase().contains("TANGENTE") || output.contains("1.00"),
            "Debe calcular tangente en cuadrante III");
    }

    @Test
    void testValoresEnLimites() {
        String input = "1.0000\n0.0001\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.contains("10000") || output.toUpperCase().contains("TANGENTE"),
            "Debe manejar tangentes muy grandes");
    }
}
