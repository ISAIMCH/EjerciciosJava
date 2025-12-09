package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Pruebas unitarias para la clase App (Problema 2.1)
 * 
 * Valida el cálculo correcto de temperatura de un grillo
 * Fórmula: T = N / 4 + 40
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
    void testTemperaturaCaso1() {
        // N = 8: T = 8/4 + 40 = 2 + 40 = 42
        String input = "8\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("42"), "Debe calcular temperatura de 42 grados para N=8");
        assertTrue(output.toUpperCase().contains("RESULTADO") || output.toUpperCase().contains("TEMPERATURA"),
                "Debe mostrar formato claro con palabra RESULTADO o TEMPERATURA");
    }

    @Test
    void testTemperaturaCaso2() {
        // N = 15: T = 15/4 + 40 = 3.75 + 40 = 43.75
        String input = "15\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("43.75") || output.contains("43,75"),
                "Debe calcular temperatura de 43.75 grados para N=15");
        assertTrue(output.contains("15"), "Debe mostrar el número de sonidos ingresados");
    }

    @Test
    void testTemperaturaCaso3() {
        // N = 40: T = 40/4 + 40 = 10 + 40 = 50
        String input = "40\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("50"), "Debe calcular temperatura de 50 grados para N=40");
    }

    @Test
    void testTemperaturaValorPequeno() {
        // N = 4: T = 4/4 + 40 = 1 + 40 = 41
        String input = "4\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("41"), "Debe calcular temperatura de 41 grados para N=4");
    }

    @Test
    void testTemperaturaValorGrande() {
        // N = 100: T = 100/4 + 40 = 25 + 40 = 65
        String input = "100\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("65"), "Debe calcular temperatura de 65 grados para N=100");
    }

    @Test
    void testTemperaturaCero() {
        // N = 0: T = 0/4 + 40 = 0 + 40 = 40
        String input = "0\n";
        String output = runAppWithInput(input);
        assertTrue(
                output.toUpperCase().contains("ERROR") || output.toUpperCase().contains("POSITIVO")
                        || output.toUpperCase().contains("VÁLIDO"),
                "Debe mostrar error para número cero (no positivo)");
    }

    @Test
    void testEntradaNegativa() {
        // N = -5: Debe mostrar error
        String input = "-5\n";
        String output = runAppWithInput(input);
        assertTrue(
                output.toUpperCase().contains("ERROR") || output.toUpperCase().contains("POSITIVO")
                        || output.toUpperCase().contains("VÁLIDO"),
                "Debe mostrar error para número negativo");
    }

    @Test
    void testEntradaNegativaGrande() {
        // N = -50: Debe mostrar error
        String input = "-50\n";
        String output = runAppWithInput(input);
        assertTrue(
                output.toUpperCase().contains("ERROR") || output.toUpperCase().contains("POSITIVO")
                        || output.toUpperCase().contains("VÁLIDO"),
                "Debe mostrar error para número negativo grande");
    }

    @Test
    void testFormatoSalida() {
        String input = "20\n";
        String output = runAppWithInput(input);
        // N = 20: T = 20/4 + 40 = 5 + 40 = 45
        assertTrue(output.contains("45"), "Debe mostrar resultado 45 para N=20");
        assertFalse(output.contains("4545") || output.contains("455"),
                "No debe duplicar o variar el resultado");
    }

    @Test
    void testPrecisionDecimal() {
        // N = 10: T = 10/4 + 40 = 2.5 + 40 = 42.5
        String input = "10\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("42.5") || output.contains("42,5"),
                "Debe mostrar decimales correctamente 42.5 para N=10");
    }

    @Test
    void testSalidaCompleta() {
        String input = "8\n";
        String output = runAppWithInput(input);
        assertTrue(output.length() > 30, "La salida debe ser completa y significativa");
        assertTrue(output.contains("8") || output.contains("42"),
                "Debe contener entrada o resultado");
    }

    @Test
    void testSalidaNoVacia() {
        String input = "5\n";
        String output = runAppWithInput(input);
        assertNotNull(output, "La salida no debe ser nula");
        assertFalse(output.isEmpty(), "La salida debe contener contenido");
    }

    @Test
    void testTemperaturaPrecisaDosDecimales() {
        // N = 25: T = 25/4 + 40 = 6.25 + 40 = 46.25
        String input = "25\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("46.25") || output.contains("46,25"),
                "Debe mostrar 46.25 con precisión");
    }
}
