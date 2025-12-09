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
 * y verifica que el programa procese correctamente la entrada y salida
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
    }

    @Test
    void testConversionCaso1() {
        // Caso 1: 7 días -> 604800 segundos
        String output = runAppWithInput("7\n");

        assertTrue(output.contains("604800"), "La salida debe contener el resultado 604800 para 7 días");
    }

    @Test
    void testConversionCaso2() {
        // Caso 2: 15 días -> 1296000 segundos
        String output = runAppWithInput("15\n");

        assertTrue(output.contains("1296000"), "La salida debe contener el resultado 1296000 para 15 días");
    }

    @Test
    void testConversionCaso3() {
        // Caso 3: 1 día -> 86400 segundos
        String output = runAppWithInput("1\n");

        assertTrue(output.contains("86400"), "La salida debe contener el resultado 86400 para 1 día");
    }

    @Test
    void testConversionCaso4() {
        // Caso 4: 30 días -> 2592000 segundos
        String output = runAppWithInput("30\n");

        assertTrue(output.contains("2592000"), "La salida debe contener el resultado 2592000 para 30 días");
    }

    @Test
    void testConversionCaso5() {
        // Caso 5: 365 días -> 31536000 segundos
        String output = runAppWithInput("365\n");

        assertTrue(output.contains("31536000"), "La salida debe contener el resultado 31536000 para 365 días");
    }

    @Test
    void testConversionCeroDias() {
        // Cero días deben resultar en 0 segundos
        String output = runAppWithInput("0\n");

        assertTrue(output.contains("0"), "La salida debe contener el resultado 0 para 0 días");
    }

    @Test
    void testFormulaCorrectaEnSalida() {
        // Verificar que se muestra claramente el resultado
        String output = runAppWithInput("5\n");

        // 5 días * 24 * 60 * 60 = 432000
        assertTrue(output.contains("432000"), "La salida debe contener el resultado 432000 para 5 días");
    }

    @Test
    void testSalidaContieneDias() {
        // Verificar que la salida contiene referencia a los días ingresados
        String output = runAppWithInput("10\n");

        // Debe mencionar los días o mostrar el resultado
        assertTrue(output.contains("10") || output.contains("864000"),
                "La salida debe contener referencia a los días o al resultado");
    }

    @Test
    void testSalidaContienePalabrasClaves() {
        // Verificar que contiene palabras clave de resultado
        String output = runAppWithInput("20\n");

        assertTrue(
                output.toUpperCase().contains("RESULTADO") || output.toUpperCase().contains("SEGUNDO")
                        || output.contains("1728000"),
                "La salida debe contener palabras clave como RESULTADO, SEGUNDO o el resultado calculado");
    }
}
