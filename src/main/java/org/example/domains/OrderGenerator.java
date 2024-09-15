package org.example.domains;

import java.util.List;
import java.util.Queue;

public class OrderGenerator implements Runnable{

    private Queue<Order> filaDePedidos;
    private List<Mesa> listaMesas;
    private boolean isRunning;
    private final int numeroDePedidosMaximo = 10;

    public OrderGenerator(Queue<Order> fila, List<Mesa> listaMesas) {
        this.isRunning = true;
        this.filaDePedidos = fila;
        this.listaMesas = listaMesas;
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
                associarMesaEPedido(order);
                filaDePedidos.add(order);
                contadorDePedidos++;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Order generation interrupted.");
                return;
            }
        }
    }

    private void associarMesaEPedido(Order order) {
        for (Mesa m : listaMesas) {
            if (m.getNumero() == order.getNumeroDaMesa() && m.getEstadoAtual() != Mesa.Estado.ESPERANDO) {
                m.receberPedido(order.getPedido());
                System.out.printf("%s para a Mesa %d\n", order.getPedido(), m.getNumero());
                break;
            }
        }
    }
}
