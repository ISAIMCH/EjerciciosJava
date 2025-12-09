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
    void testContarCerosCaso1() {
        String input = "5\n0\n0\n5\n0\n10\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("3"), "Debe contar 3 ceros");
    }

    @Test
    void testContarCerosCaso2() {
        String input = "4\n10\n20\n30\n40\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("0"), "Debe mostrar 0 ceros");
    }

    @Test
    void testContarCerosCaso3() {
        String input = "3\n0\n0\n0\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("3"), "Debe contar 3 ceros");
    }

    @Test
    void testAppExecutes() {
        String input = "2\n5\n0\n";
        String output = runAppWithInput(input);
        assertNotNull(output, "La salida no debe ser nula");
        assertFalse(output.isEmpty(), "La salida debe contener contenido");
    }
}
