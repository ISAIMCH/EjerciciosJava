package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Pruebas unitarias para la clase App (Problema 1.6)
 * 
 * Valida el cálculo correcto de conversión de días a segundos
 * y verifica que el programa procese correctamente la entrada, salida y
 * estructura
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
    void testAppExecutes() {
        String output = runAppWithInput("7\n");
        assertNotNull(output, "La salida no debe ser nula");
        assertFalse(output.isEmpty(), "La salida debe contener contenido");
        assertTrue(output.length() > 20, "La salida debe ser significativa");
    }

    @Test
    void testConversionCaso1() {
        // Caso 1: 7 días -> 604800 segundos
        String output = runAppWithInput("7\n");
        assertTrue(output.contains("604800"), "Debe calcular 604800 segundos para 7 días");
        assertTrue(output.toUpperCase().contains("RESULTADO") || output.toUpperCase().contains("SEGUNDO"),
                "Debe mostrar claramente que es un resultado de segundos");
    }

    @Test
    void testConversionCaso2() {
        // Caso 2: 15 días -> 1296000 segundos
        String output = runAppWithInput("15\n");
        assertTrue(output.contains("1296000"), "Debe calcular 1296000 segundos para 15 días");
        assertTrue(output.contains("15"), "Debe mostrar los días ingresados");
    }

    @Test
    void testConversionCaso3() {
        // Caso 3: 1 día -> 86400 segundos
        String output = runAppWithInput("1\n");
        assertTrue(output.contains("86400"), "Debe calcular 86400 segundos para 1 día");
    }

    @Test
    void testConversionCaso4() {
        // Caso 4: 30 días -> 2592000 segundos
        String output = runAppWithInput("30\n");
        assertTrue(output.contains("2592000"), "Debe calcular 2592000 segundos para 30 días");
    }

    @Test
    void testConversionCaso5() {
        // Caso 5: 365 días -> 31536000 segundos
        String output = runAppWithInput("365\n");
        assertTrue(output.contains("31536000"), "Debe calcular 31536000 segundos para 365 días");
    }

    @Test
    void testConversionCeroDias() {
        // Cero días deben resultar en 0 segundos
        String output = runAppWithInput("0\n");
        assertTrue(output.contains("0"), "Debe mostrar 0 para 0 días");
    }

    @Test
    void testFormulaCorrectaEnSalida() {
        // Verificar que se muestra claramente el resultado
        String output = runAppWithInput("5\n");
        // 5 días * 24 * 60 * 60 = 432000
        assertTrue(output.contains("432000"), "Debe calcular 432000 segundos para 5 días");
        assertFalse(output.contains("432000432000"), "No debe duplicar el resultado");
    }

    @Test
    void testSalidaContieneDiasIngresados() {
        // Verificar que la salida contiene referencia a los días ingresados
        String output = runAppWithInput("10\n");
        assertTrue(output.contains("10"), "Debe mostrar los días ingresados (10)");
        assertTrue(output.contains("864000"), "Debe mostrar el resultado calculado (864000)");
    }

    @Test
    void testSalidaContienePalabrasClaves() {
        // Verificar que contiene palabras clave de resultado
        String output = runAppWithInput("20\n");
        assertTrue(
                output.toUpperCase().contains("RESULTADO") || output.toUpperCase().contains("SEGUNDO")
                        || output.toUpperCase().contains("CONVERSIÓN") || output.toUpperCase().contains("CONVERSION")
                        || output.contains("1728000"),
                "Debe contener palabras clave como RESULTADO, SEGUNDO, CONVERSIÓN o el resultado calculado");
    }

    @Test
    void testFormatoDeResultado() {
        // Verificar que el resultado tiene formato correcto
        String output = runAppWithInput("12\n");
        // 12 días = 1036800 segundos
        assertTrue(output.contains("1036800"), "Debe calcular correctamente 1036800");
        assertFalse(output.contains("1036800000"),
                "Debe mostrar el valor exacto sin duplicaciones");
    }

    @Test
    void testNumeroGrande() {
        // Verificar con un número grande
        String output = runAppWithInput("100\n");
        // 100 días = 8640000 segundos
        assertTrue(output.contains("8640000"), "Debe calcular correctamente para 100 días");
    }

    @Test
    void testSalidaComplementa() {
        // Verificar que la salida no está vacía después del resultado
        String output = runAppWithInput("3\n");
        assertTrue(output.length() > 30, "La salida debe ser completa con formato");
        assertTrue(output.contains("3") || output.contains("259200"),
                "Debe contener entrada o resultado");
    }
}
