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
    void testVentaLocalidad2() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n2\n3\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("46.80") || output.contains("46,80"),
                "Debe calcular venta de localidad 2: 15.60 * 3 = 46.80");
    }

    @Test
    void testVentaLocalidad5() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n5\n8\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("600") || output.contains("600.00"),
                "Debe calcular venta de localidad 5: 75.00 * 8 = 600.00");
    }

    @Test
    void testVentasMultiples() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n2\n3\n5\n8\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("46.80") || output.contains("600") || output.contains("646"),
                "Debe acumular múltiples ventas");
    }

    @Test
    void testVentaLocalidad1() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n1\n6\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("43.50") || output.contains("43,50"),
                "Debe calcular venta de localidad 1: 7.25 * 6 = 43.50");
    }

    @Test
    void testVentaLocalidad4() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n4\n12\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("360") || output.contains("360.00"),
                "Debe calcular venta de localidad 4: 30.00 * 12 = 360.00");
    }

    @Test
    void testVentaLocalidad3() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n3\n4\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("92") || output.contains("92.00"),
                "Debe calcular venta de localidad 3: 23.00 * 4 = 92.00");
    }

    @Test
    void testRecaudacionTotal() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n2\n3\n5\n8\n3\n4\n1\n6\n4\n12\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("1142.30") || output.contains("1142,30") || output.contains("1142"),
                "Debe calcular recaudación total: 1142.30");
    }

    @Test
    void testPrecioLocalidad1() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n1\n1\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("7.25") || output.contains("7,25"),
                "Precio localidad 1 debe ser 7.25");
    }

    @Test
    void testPrecioLocalidad5() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n5\n1\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("75") || output.contains("75.00"),
                "Precio localidad 5 debe ser 75.00");
    }

    @Test
    void testVentaCeroBoletos() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n1\n0\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("0") || output.contains("0.00"),
                "Venta de 0 boletos debe dar 0");
    }

    @Test
    void testAcumulacionMismaLocalidad() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n1\n5\n1\n10\n1\n3\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("130.50") || output.contains("130,50") || output.contains("130"),
                "AP1 debe acumular 130.50 (7.25 * 18)");
    }

    @Test
    void testClaveTerminadora() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n-1\n";
        String output = runAppWithInput(input);
        assertNotNull(output, "El programa debe ejecutarse con clave -1");
    }

    // TESTS ADICIONALES PARA VALIDAR ACUMULADORES FINALES

    @Test
    void testAP1Final() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n1\n5\n1\n10\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 1: 15") || output.contains("TIPO 1: 15"),
                "AP1 debe ser 15 (5 + 10)");
    }

    @Test
    void testAP2Final() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n2\n3\n2\n7\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 2: 10") || output.contains("TIPO 2: 10"),
                "AP2 debe ser 10 (3 + 7)");
    }

    @Test
    void testAP3Final() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n3\n2\n3\n8\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 3: 10") || output.contains("TIPO 3: 10"),
                "AP3 debe ser 10 (2 + 8)");
    }

    @Test
    void testAP4Final() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n4\n4\n4\n6\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 4: 10") || output.contains("TIPO 4: 10"),
                "AP4 debe ser 10 (4 + 6)");
    }

    @Test
    void testAP5Final() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n5\n2\n5\n3\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 5: 5") || output.contains("TIPO 5: 5"),
                "AP5 debe ser 5 (2 + 3)");
    }

    @Test
    void testTodosAcumuladoresFinal() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n1\n2\n2\n3\n3\n4\n4\n5\n5\n6\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 1: 2"),
                "AP1 debe ser 2");
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 2: 3"),
                "AP2 debe ser 3");
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 3: 4"),
                "AP3 debe ser 4");
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 4: 5"),
                "AP4 debe ser 5");
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 5: 6"),
                "AP5 debe ser 6");
    }

    @Test
    void testRecaudacionFinal() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n1\n10\n2\n5\n3\n2\n4\n1\n5\n1\n-1\n";
        String output = runAppWithInput(input);
        // Cálculo: (7.25*10) + (15.60*5) + (23.00*2) + (30.00*1) + (75.00*1)
        // = 72.50 + 78.00 + 46.00 + 30.00 + 75.00 = 301.50
        assertTrue(output.contains("301.50") || output.contains("RECAUDACION") && output.contains("301"),
                "Recaudación debe ser 301.50");
    }

    @Test
    void testClaveInvalida() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n6\n5\n1\n2\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("Clave inválida") || output.contains("invalida"),
                "Debe mostrar mensaje de clave inválida");
    }

    @Test
    void testMultiplesTransaccionesLocalidad1() {
        String input = "7.25\n15.60\n23.00\n30.00\n75.00\n1\n1\n1\n1\n1\n1\n-1\n";
        String output = runAppWithInput(input);
        assertTrue(output.contains("CANTIDAD BOLETOS TIPO 1: 3"),
                "AP1 debe acumular 3 boletos en 3 transacciones");
    }
}
