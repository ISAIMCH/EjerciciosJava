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

    @Test
    void testTemperaturaCaso1() {
        String input = "8\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("42"), "Debe calcular temperatura de 42 grados");
    }

    @Test
    void testTemperaturaCaso2() {
        String input = "15\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("43.75"), "Debe calcular temperatura de 43.75 grados");
    }

    @Test
    void testTemperaturaCaso3() {
        String input = "40\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("50"), "Debe calcular temperatura de 50 grados");
    }

    @Test
    void testAppExecutes() {
        String input = "20\n";
        String output = runAppWithInput(input);
        assertNotNull(output, "La salida no debe ser nula");
        assertFalse(output.isEmpty(), "La salida debe contener contenido");
    }

    @Test
    void testEntradaNegativa() {
        String input = "-5\n";
        String output = runAppWithInput(input);
        assertTrue(output.toUpperCase().contains("ERROR") || output.toUpperCase().contains("POSITIVO"),
                "Debe mostrar error para número negativo");
    }

    @Test
    void testEntradaCero() {
        String input = "0\n";
        String output = runAppWithInput(input);
        assertTrue(output.toUpperCase().contains("ERROR") || output.toUpperCase().contains("POSITIVO"),
                "Debe mostrar error para cero");
    }
}
