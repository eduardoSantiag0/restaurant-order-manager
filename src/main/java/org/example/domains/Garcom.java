package org.example.domains;

import lombok.Getter;

import java.util.List;
import java.util.Queue;

@Getter
public class Garcom implements Runnable{

    private String nome;
    private boolean isRunning;
    private static Queue<Order> filaDePedidos;
    private static List<Mesa> listaDeMesas;

    public Garcom(String nome, Queue<Order> fila, List<Mesa> mesas) {
        this.nome = nome;
        filaDePedidos = fila;
        listaDeMesas = mesas;
        this.isRunning = true;
    }

    public void pararGarcom() {
        this.isRunning = false;
//        thread.interrupt();
    }

    @Override
    public void run() {
        //* Continua procurando por mesas que estão esperando
        while (isRunning) {
                if (filaDePedidos.isEmpty()) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.printf("%s foi interrompido%n", nome);
                        Thread.currentThread().interrupt();
                        isRunning = false;
                    }
                }

                Order order = filaDePedidos.poll();
                Mesa mesaAtual = null;
                if (order == null) { continue; }

                for (Mesa m : listaDeMesas) {
                    if (m.getNumero() == order.getNumeroDaMesa()) {
                        mesaAtual = m;
                        break;
                    }
                }

                System.out.printf("%s está processando o pedido para a Mesa %d: %s  %n", nome, mesaAtual.getNumero(), order.getComida());
                if (mesaAtual != null) {
                    try {
                        mesaAtual.concluirPedido();
                        Thread.sleep(3000);
                        System.out.println(filaDePedidos);
                        System.out.printf("%s concluiu o pedido da Mesa %d, RESTAM: %d \n", nome, mesaAtual.getNumero(), filaDePedidos.size());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        isRunning = false;
                    }
                }
        }

    }


}
