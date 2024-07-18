package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int totalPlatosTipicos = 0;
    private static int totalPlatosCarta = 0;
    private static int totalPlatosInternacionales = 0;
    private static double totalValor = 0;
    private static String nombreUsuario;
    private static List<String> platosOrdenados = new ArrayList<>();

    public static void main(String[] args) {
        nombreUsuario = JOptionPane.showInputDialog(null, " ingrese su nombre :", "BIENVENIDO", JOptionPane.QUESTION_MESSAGE);

        int codMenuPpal;
        do {
            codMenuPpal = mostrarMenuPrincipal();

            switch (codMenuPpal) {
                case 1 -> menuTipico();
                case 2 -> menuCarta();
                case 3 -> menuInternacional();
                case 4 -> mostrarEstadisticas();
                case 5 -> imprimirFactura();
                case 6 -> JOptionPane.showMessageDialog(null, "Gracias por su visita, " + nombreUsuario + ". ¡Hasta luego!", "Despedida", JOptionPane.INFORMATION_MESSAGE);
                default -> JOptionPane.showMessageDialog(null, "No corresponde a un código válido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } while (codMenuPpal != 6);
    }

    private static int mostrarMenuPrincipal() {
        String menu = """
                MENÚ RESTAURANTE

                1 Plato de la casa
                2 Plato a la carta
                3 Plato exotico
                4 Ver estadísticas
                5 Imprimir factura
                6 Salir

                seleccione una opción: """;

        return Integer.parseInt(JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.PLAIN_MESSAGE));
    }

    private static void menuTipico() {
        int codMenuTipico;
        do {
            codMenuTipico = mostrarMenu("MENÚ TÍPICO", new String[]{"sancocho ($12,000)", "Sopa de carne ($8,000)", "pescado ($20,000)"});

            switch (codMenuTipico) {
                case 1 -> procesarPedido("sancocho", 12000, "de la casa");
                case 2 -> procesarPedido("Sopa de carne", 8000, "de la casa");
                case 3 -> procesarPedido("pescado", 20000, "de la casa");
                case 4 -> { }
                default -> JOptionPane.showMessageDialog(null, "No corresponde a un código válido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } while (codMenuTipico != 4);
    }

    private static void menuCarta() {
        int codMenuCarta;
        do {
            codMenuCarta = mostrarMenu("MENÚ A LA CARTA", new String[]{"patacon gigante ($21,000)", "Pollo ($24,000)", "Lomo de cerdo ($24,000)"});

            switch (codMenuCarta) {
                case 1 -> procesarPedido("patacon gigante ", 21000, "carta");
                case 2 -> procesarPedido("Pollo ", 24000, "carta");
                case 3 -> procesarPedido("Lomo de cerdo", 24000, "carta");
                case 4 -> { }
                default -> JOptionPane.showMessageDialog(null, "No corresponde a un código válido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } while (codMenuCarta != 4);
    }

    private static void menuInternacional() {
        int codMenuInternacional;
        do {
            codMenuInternacional = mostrarMenu("menu exotico", new String[]{"hamburguesa ($14,000)", "Pizza ($24,000)", "Tacos ($34,000)"});

            switch (codMenuInternacional) {
                case 1 -> procesarPedido("tacos", 34000, "exoticos");
                case 2 -> procesarPedido("Pizza", 24000, "exoticos");
                case 3 -> procesarPedido("hamburgesa ", 14000, "exoticos");
                case 4 -> { }
                default -> JOptionPane.showMessageDialog(null, "No corresponde a un código válido", "CUIDADO", JOptionPane.WARNING_MESSAGE);
            }
        } while (codMenuInternacional != 4);
    }

    private static int mostrarMenu(String titulo, String[] opciones) {
        StringBuilder menu = new StringBuilder(titulo + "\n\n");
        for (int i = 0; i < opciones.length; i++) {
            menu.append(i + 1).append(". ").append(opciones[i]).append("\n");
        }
        menu.append("4. Regresar\n\n seleccione una opción: ");
        return Integer.parseInt(JOptionPane.showInputDialog(null, menu.toString(), titulo, JOptionPane.PLAIN_MESSAGE));
    }

    private static void procesarPedido(String plato, double precio, String tipo) {
        JOptionPane.showMessageDialog(null, "Se ha solicitado " + plato + ", el costo es de $" + precio, "Pedido", JOptionPane.INFORMATION_MESSAGE);
        platosOrdenados.add(plato + " - $" + precio);
        totalValor += precio;
        switch (tipo) {
            case "casa" -> totalPlatosTipicos++;
            case "carta" -> totalPlatosCarta++;
            case "exotico" -> totalPlatosInternacionales++;
        }
    }

    private static void mostrarEstadisticas() {
        String estadisticas = """
                ESTADÍSTICAS

                Total platos casa: %d
                Total platos a la carta: %d
                Total platos exotico: %d
                Total platos: %d
                Valor total a pagar: $%.2f""".formatted(totalPlatosTipicos, totalPlatosCarta, totalPlatosInternacionales,
                (totalPlatosTipicos + totalPlatosCarta + totalPlatosInternacionales), totalValor);
        JOptionPane.showMessageDialog(null, estadisticas, "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void imprimirFactura() {
        StringBuilder factura = new StringBuilder("FACTURA\n\n")
                .append("Cliente: ").append(nombreUsuario).append("\n\n")
                .append("Platos ordenados:\n");

        for (String plato : platosOrdenados) {
            factura.append("- ").append(plato).append("\n");
        }

        factura.append("\nTotal a pagar: $").append(totalValor);
        JOptionPane.showMessageDialog(null, factura.toString(), "Factura", JOptionPane.INFORMATION_MESSAGE);
    }
}
