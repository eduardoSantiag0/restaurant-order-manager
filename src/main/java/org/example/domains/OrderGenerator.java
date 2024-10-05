package org.example.domains;

import java.util.List;
import java.util.Queue;

public class OrderGenerator implements Runnable{

    private Queue<Order> filaDePedidos;
    private List<Mesa> listaMesas;
    private boolean isRunning;
    private final int numeroDePedidosMaximo = 10;
    private int numeroDeMesas;

    private final int TEMPO_POR_PEDIDO = 5000;

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
                    synchronized (filaDePedidos) {
                        filaDePedidos.add(order);
                        contadorDePedidos++;
                        filaDePedidos.notifyAll();
                    }
                    Thread.sleep(TEMPO_POR_PEDIDO);
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
                m.receberPedido(order.getComida());
                System.out.printf("%s para a Mesa %d\n", order.getComida(), m.getNumero());
                return true;
            }
        }
        return false;
    }
}
