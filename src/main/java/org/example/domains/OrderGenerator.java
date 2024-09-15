package org.example.domains;

import java.util.List;
import java.util.Queue;

public class OrderGenerator implements Runnable{

    private Queue<Order> filaDePedidos;
    private List<Mesa> listaMesas;
    private boolean isRunning;
    private final int numeroDePedidosMaximo = 10;
    private int numeroDeMesas;

    public OrderGenerator(Queue<Order> fila, List<Mesa> listaMesas, int numeroDeMesas) {
        this.isRunning = true;
        this.filaDePedidos = fila;
        this.listaMesas = listaMesas;
        this.numeroDeMesas = numeroDeMesas;
    }

    @Override
    public void run() {
        int contadorDePedidos = 0;
        while (isRunning) {
            if (contadorDePedidos >= numeroDePedidosMaximo) {
                isRunning = false;
                System.out.println("Limite de Pedidos no Dia foi Atingido");
                break;
            }
            try {
                Order order = new Order();
//                Order order = new Order(numeroDeMesas);
                boolean mesaLivre = associarMesaEPedido(order);
                if (mesaLivre) {
                    filaDePedidos.add(order);
                    contadorDePedidos++;
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Order generation interrupted.");
                return;
            }
        }
    }

    private boolean associarMesaEPedido(Order order) {
        for (Mesa m : listaMesas) {
            if (m.getNumero() == order.getNumeroDaMesa() && m.getEstadoAtual() != Mesa.Estado.ESPERANDO) {
                m.receberPedido(order.getPedido());
                System.out.printf("%s para a Mesa %d\n", order.getPedido(), m.getNumero());
                return true;
            }
        }
        return false;
    }
}
