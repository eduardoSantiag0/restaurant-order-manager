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


    private static final int TOTAL_MESAS = 20;
    private static final int NUMERO_INICIAL_PEDIDOS = 5;
    private static final int TOTAL_THREADS = 5;

    private static ExecutorService executorService;

    private static List<Garcom> listaDeGarcons = new ArrayList<>();
    private static ConcurrentLinkedQueue<Order> filaDePedidos = new ConcurrentLinkedQueue<>();
    private static List<Mesa> listaDeMesas = new ArrayList<>();

    private static final OrderGenerator orderGenerator1 = new OrderGenerator(filaDePedidos, listaDeMesas,TOTAL_MESAS);
    private static final OrderGenerator orderGenerator2 = new OrderGenerator(filaDePedidos, listaDeMesas,TOTAL_MESAS);

    private static void setUp() {

        executorService = Executors.newFixedThreadPool(TOTAL_THREADS);

        for (int i = 0; i < TOTAL_MESAS; i++) {
            Mesa mesa = new Mesa(i, filaDePedidos);
            listaDeMesas.add(mesa);
        }

        for (int i = 0; i < NUMERO_INICIAL_PEDIDOS; i++) {
            Order pedido = new Order();
            filaDePedidos.add(pedido);
        }

    }

    private static void iniciandoThreads() {
//        String [] nomesGarcons =  {"GLAUBER ROCHA", "AKIRA KUROSAWA", "WIM WENDERS"};
        String [] nomesGarcons =  {"WES ANDERSON", "EDGAR WRIGHT", "GUS VAN SANT"};
        Arrays.stream(nomesGarcons).map(n -> new Garcom(n, filaDePedidos, listaDeMesas)).forEach(listaDeGarcons::add);
        listaDeGarcons.forEach(g -> executorService.execute(g));

        executorService.execute(orderGenerator1);
        executorService.execute(orderGenerator2);
    }


    public static void main(String[] args) {
        setUp();
        System.out.printf("NÚMERO DE PEDIDOS %d\n\n", filaDePedidos.size());
        System.out.printf("NÚMERO DE MESAS  %d\n\n", listaDeMesas.size());
        System.out.println(filaDePedidos);
        iniciandoThreads();
        System.out.println("\n");

        executorService.shutdown();
    }
}