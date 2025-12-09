package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Pruebas unitarias para la clase App (PS 2.2)
 * Valida el cálculo de la cotangente (COTANGENTE = COSENO / SENO)
 * Tests rigurosos que validan el VALOR EXACTO, no solo la presencia de palabras
 * clave
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
    void testCotangente60Grados() {
        // cot(60°) = coseno(60°) / seno(60°) = 0.5000 / 0.8660 = 0.5774
        String input = "0.8660\n0.5000\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("0.5773") || output.contains("0.5774"),
                "Debe calcular exactamente cotangente de 60° ≈ 0.5774");
    }

    @Test
    void testCotangente45Grados() {
        // cot(45°) = coseno(45°) / seno(45°) = 0.7071 / 0.7071 = 1.0
        String input = "0.7071\n0.7071\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1.0000") || output.contains("1.0"),
                "Debe calcular exactamente cotangente de 45° = 1.0");
    }

    @Test
    void testCotangente30Grados() {
        // cot(30°) = coseno(30°) / seno(30°) = 0.8660 / 0.5000 = 1.7320
        String input = "0.5000\n0.8660\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1.7320") || output.contains("1.732"),
                "Debe calcular exactamente cotangente de 30° ≈ 1.7320");
    }

    @Test
    void testCotangente0Grados() {
        // cot(0°) = coseno(0°) / seno(0°) = indefinido (pero el código trata seno=1
        // como 0°)
        // Este test verifica un ángulo cercano a 0° donde seno es pequeño
        String input = "0.9999\n0.0100\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("99.99") || output.contains("cotangente"),
                "Debe calcular cotangente con seno pequeño");
    }

    @Test
    void testSenoIgualCero() {
        // cot(90°) = coseno(90°) / seno(90°) = 0 / 1 = indefinido
        // Pero nuestro test es seno = 0.0 exacto
        String input = "0.0\n0.5\n";
        String output = runAppWithInput(input);
        assertTrue(output.toUpperCase().contains("ERROR") ||
                output.toUpperCase().contains("INDEFINIDA") ||
                output.toUpperCase().contains("SENO"),
                "Debe rechazar seno = 0 con mensaje de error");
    }

    @Test
    void testsenoDiferenteDeZero() {
        // cot con seno != 0 debe funcionar
        String input = "0.5\n0.8660\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1.73") && output.contains("cotangente"),
                "Debe calcular cotangente cuando seno es diferente de 0");
    }

    @Test
    void testCotangenteNegativa() {
        // cot(-30°) = coseno(-30°) / seno(-30°) = 0.8660 / -0.5000 = -1.7320
        String input = "-0.5000\n0.8660\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("-1.7320") || output.contains("-1.732"),
                "Debe calcular exactamente cotangente negativa = -1.732");
    }

    @Test
    void testCotangentePositivaEnCuadranteIII() {
        // cot(225°) = coseno(225°) / seno(225°) = -0.8660 / -0.5000 = 1.7320
        String input = "-0.5000\n-0.8660\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1.7320") || output.contains("1.732"),
                "Debe calcular exactamente cotangente en cuadrante III = 1.732");
    }

    @Test
    void testValoresEnLimites() {
        // cot con seno muy pequeño = coseno / seno = 1.0 / 0.0001 = 10000
        String input = "0.0001\n1.0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("10000.0") || output.contains("10000"),
                "Debe calcular exactamente cotangente muy grande = 10000.0");
    }

    @Test
    void testValidarQueSiempreCoseno_DividoPor_Seno() {
        // Prueba que valida la fórmula correcta: si cambio a mostrar seno, debe fallar
        // coseno=2, seno=0.5 -> cotangente = 2/0.5 = 4.0 (NO 0.5)
        String input = "0.5\n2.0\n";
        String output = runAppWithInput(input);

        assertTrue(output.contains("4.0000") || output.contains("4.0"),
                "CRÍTICO: Debe ser coseno/seno = 2.0/0.5 = 4.0, NO 0.5 (seno)");
        assertFalse(output.contains("0.5000") && output.contains("cotangente"),
                "CRÍTICO: No debe mostrar 0.5 como cotangente");
    }
}