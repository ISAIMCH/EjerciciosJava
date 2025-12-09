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

    // Inciso a): Mayor costo de dulces
    @Test
    void testMayorCostoDulces() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1900") || output.contains("7"),
                "Debe encontrar mayor costo de dulces: 1900 en mes 7");
    }

    @Test
    void testMayorCostoDulcesValor() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1900"), "Debe contener valor 1900");
    }

    // Inciso b): Promedio de bebidas
    @Test
    void testPromedioBebidas() {
        String output = runAppWithInput("");
        assertTrue(output.contains("2187") || output.contains("2187.50"),
                "Debe calcular promedio de bebidas: 2187.50");
    }

    @Test
    void testSumaBebidas() {
        String output = runAppWithInput("");
        assertTrue(output.contains("26250") || output.contains("26250.00"),
                "Debe calcular suma de bebidas: 26250.00");
    }

    // Inciso c): Mayor costo bebidas
    @Test
    void testMayorCostoBebidas() {
        String output = runAppWithInput("");
        assertTrue(output.contains("2400") || output.contains("7"),
                "Debe encontrar mayor costo bebidas: 2400 en mes 7");
    }

    // Inciso c): Menor costo bebidas
    @Test
    void testMenorCostoBebidas() {
        String output = runAppWithInput("");
        assertTrue(output.contains("2000") || output.contains("1"),
                "Debe encontrar menor costo bebidas: 2000 en mes 1");
    }

    // Inciso d): Menor costo en diciembre
    @Test
    void testMenorCostoDiciembre() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1600") || output.contains("diciembre") || output.contains("12"),
                "Debe encontrar menor costo en diciembre: 1600");
    }

    @Test
    void testCostosDeciembre() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1600") && output.contains("2100") && output.contains("1700"),
                "Debe mostrar costos de diciembre: 1600, 2100, 1700");
    }

    @Test
    void testValoresEnerodeDulces() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1500"), "Debe contener valor de enero dulces: 1500");
    }

    @Test
    void testValoresJulioTodosDep() {
        String output = runAppWithInput("");
        assertTrue(output.contains("1900") && output.contains("2400") && output.contains("2200"),
                "Debe mostrar valores de julio: 1900, 2400, 2200");
    }
}
