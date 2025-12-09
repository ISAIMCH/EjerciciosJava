package com.javatutor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

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
    void testBuscarContactoExistente() {
        String input = "3\nAna\n555-1001\nCarlos\n555-2002\nDiana\n555-3003\nCarlos\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-2002"));
    }

    @Test
    void testBuscarContactoNoExistente() {
        String input = "3\nAna\n555-1001\nCarlos\n555-2002\nDiana\n555-3003\nFernando\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("No encontrado"));
    }

    @Test
    void testBuscarPrimerContacto() {
        String input = "3\nAna\n555-1001\nCarlos\n555-2002\nDiana\n555-3003\nAna\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-1001"));
    }

    @Test
    void testBuscarUltimoContacto() {
        String input = "3\nAna\n555-1001\nCarlos\n555-2002\nDiana\n555-3003\nDiana\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-3003"));
    }

    @Test
    void testBuscarCaseInsensitive() {
        String input = "2\nJuan\n555-1001\nMaria\n555-2002\njuan\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-1001"));
    }

    @Test
    void testBuscarMixedCase() {
        String input = "2\nJuan\n555-1001\nMaria\n555-2002\nJUAN\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-1001"));
    }

    @Test
    void testDirectorioUnContacto() {
        String input = "1\nPedro\n555-1001\nPedro\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Contactos registrados:"));
        assertTrue(output.contains("Pedro - 555-1001"));
        assertTrue(output.contains("Encontrado"));
    }

    @Test
    void testDirectorioMultiplesContactos() {
        String input = "5\nAlicia\n555-0001\nBeatriz\n555-0002\nCarla\n555-0003\nDiana\n555-0004\nElena\n555-0005\nCarla\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Alicia - 555-0001"));
        assertTrue(output.contains("Elena - 555-0005"));
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-0003"));
    }

    @Test
    void testTelefonoFormat() {
        String input = "2\nPedro\n123-4567\nJose\n987-6543\nJose\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("987-6543"));
    }

    @Test
    void testNombreLargo() {
        String input = "1\nJuan Diego Martinez Perez\n555-1234567890\nJuan Diego Martinez Perez\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-1234567890"));
    }

    @Test
    void testMultiplesbusquedas() {
        String input = "3\nRoberto\n555-0001\nRicardo\n555-0002\nRaul\n555-0003\nRicardo\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Contactos registrados:"));
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-0002"));
    }

    @Test
    void testBusquedaMayuscula() {
        String input = "2\nalex\n555-1001\nbeth\n555-2002\nALEX\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-1001"));
    }

    @Test
    void testNombreConEspacios() {
        String input = "1\nJuan Pablo\n555-1001\nJuan Pablo\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Encontrado"));
        assertTrue(output.contains("555-1001"));
    }
}
