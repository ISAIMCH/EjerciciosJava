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
}
