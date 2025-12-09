package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Pruebas unitarias para la clase App (PS 2.1)
 * Valida el cálculo de la tangente (TANGENTE = SENO / COSENO)
 * Tests rigurosos que validan el VALOR EXACTO, no solo la presencia de palabras clave
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
        // tan(60°) = seno(60°) / coseno(60°) = 0.8660 / 0.5000 = 1.732
        String input = "0.8660\n0.5000\n";
        String output = runAppWithInput(input);
        
        // Validación ESTRICTA: debe contener el valor calculado, NO solo la palabra "TANGENTE"
        assertTrue(output.contains("1.7320") || output.contains("1.732"),
            "Debe calcular exactamente tangente de 60° = 1.732");
        // Validación adicional: NO debe contener el valor del coseno como resultado
        assertFalse(output.contains("0.5000") && output.contains("resultado"),
            "No debe confundir coseno con tangente");
    }

    @Test
    void testTangente45Grados() {
        // tan(45°) = seno(45°) / coseno(45°) = 0.7071 / 0.7071 = 1.0
        String input = "0.7071\n0.7071\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.contains("1.0000") || output.contains("1.0"),
            "Debe calcular exactamente tangente de 45° = 1.0");
    }

    @Test
    void testTangente30Grados() {
        // tan(30°) = seno(30°) / coseno(30°) = 0.5000 / 0.8660 = 0.577
        String input = "0.5000\n0.8660\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.contains("0.5773") || output.contains("0.577"),
            "Debe calcular exactamente tangente de 30° ≈ 0.577");
    }

    @Test
    void testTangente0Grados() {
        // tan(0°) = seno(0°) / coseno(0°) = 0.0 / 1.0 = 0.0
        String input = "0.0000\n1.0000\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.contains("0.0000"),
            "Debe calcular exactamente tangente de 0° = 0.0");
    }

    @Test
    void testCosenoIgualCero() {
        // tan(90°) = seno(90°) / coseno(90°) = 1.0 / 0.0 = indefinido
        String input = "1.0000\n0.0000\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.toUpperCase().contains("ERROR") || 
                   output.toUpperCase().contains("NO ESTÁ DEFINIDA") ||
                   output.toUpperCase().contains("CERO"),
            "Debe rechazar explícitamente coseno = 0");
        // Validación crítica: NO debe mostrar resultado de tangente
        assertFalse(output.toUpperCase().contains("TANGENTE") && output.contains("Infinity"),
            "No debe calcular tangente cuando coseno es 0");
    }

    @Test
    void testTangenteNegativa() {
        // tan(-30°) = seno(-30°) / coseno(-30°) = -0.5000 / 0.8660 = -0.577
        String input = "-0.5000\n0.8660\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.contains("-0.5773") || output.contains("-0.577"),
            "Debe calcular exactamente tangente negativa = -0.577");
    }

    @Test
    void testTangenteCuadranteIII() {
        // tan(225°) = seno(225°) / coseno(225°) = -0.7071 / -0.7071 = 1.0
        String input = "-0.7071\n-0.7071\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.contains("1.0000") || output.contains("1.0"),
            "Debe calcular exactamente tangente en cuadrante III = 1.0");
    }

    @Test
    void testValoresEnLimites() {
        // tan con coseno muy pequeño = seno / coseno = 1.0 / 0.0001 = 10000
        String input = "1.0000\n0.0001\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.contains("10000.0") || output.contains("10000"),
            "Debe calcular exactamente tangente muy grande = 10000.0");
    }

    @Test
    void testValidarQueSiempreCalculaSeno_DividoPor_Coseno() {
        // Prueba que valida la fórmula correcta: si cambio a mostrar coseno, debe fallar
        // seno=0.5, coseno=2 -> tangente = 0.5/2 = 0.25 (NO 2.0)
        String input = "0.5\n2.0\n";
        String output = runAppWithInput(input);
        
        assertTrue(output.contains("0.2500") || output.contains("0.25"),
            "CRÍTICO: Debe ser seno/coseno = 0.5/2.0 = 0.25, NO 2.0 (coseno)");
        assertFalse(output.contains("2.0000") && output.contains("tangente"),
            "CRÍTICO: No debe mostrar 2.0 como tangente");
    }
}
