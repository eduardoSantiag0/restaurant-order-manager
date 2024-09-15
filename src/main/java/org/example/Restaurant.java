package org.example;

import org.example.domains.Garcom;
import org.example.domains.Mesa;
import org.example.domains.Order;
import org.example.domains.OrderGenerator;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {

    private static ExecutorService executorService;

    private static List<Garcom> listaDeGarcons = new ArrayList<>();
    private static ConcurrentLinkedQueue<Order> filaDePedidos = new ConcurrentLinkedQueue<>();
    private static List<Mesa> listaDeMesas = new ArrayList<>();

    private static final OrderGenerator orderGenerator1 = new OrderGenerator(filaDePedidos, listaDeMesas);
    private static final OrderGenerator orderGenerator2 = new OrderGenerator(filaDePedidos, listaDeMesas);

    private static final int NUMERO_DE_MESAS = 20;
    private static final int NUMERO_INICIAL_PEDIDOS = 5;
    private static final int TOTAL_THREADS = 5;

    private static void setUp() {

        executorService = Executors.newFixedThreadPool(TOTAL_THREADS);

        for (int i = 0; i < NUMERO_DE_MESAS; i++) {
            Mesa mesa = new Mesa(i, filaDePedidos);
            listaDeMesas.add(mesa);
        }

        for (int i = 0; i < NUMERO_INICIAL_PEDIDOS; i++) {
            Order pedido = new Order(i);
            filaDePedidos.add(pedido);
        }

    }

    private static void iniciandoThreads() {
        String [] nomesGarcons =  {"GLAUBER ROCHA", "AKIRA KUROSAWA", "WIM WENDERS"};
        Arrays.stream(nomesGarcons).map(n -> new Garcom(n, filaDePedidos, listaDeMesas)).forEach(listaDeGarcons::add);
        listaDeGarcons.forEach(g -> executorService.execute(g));

        executorService.execute(orderGenerator1);
        executorService.execute(orderGenerator2);
    }


    public static void main(String[] args) {
        setUp();
        iniciandoThreads();

        System.out.println(filaDePedidos);

        System.out.printf("NÚMERO DE PEDIDOS %d\n\n", filaDePedidos.size());

        executorService.shutdown();
    }
}