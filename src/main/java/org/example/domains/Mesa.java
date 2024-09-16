package org.example.domains;

import lombok.Getter;

import java.util.Queue;

@Getter
public class Mesa {

    public final int numero;
    public Comida comidaAtual;
    private Estado estadoAtual;
    private boolean prioridade;
    private static Queue<Order> filaDePedidos;

    public enum Estado {
        VAZIA, // Quando é criada
        ESPERANDO, // Tem um pedido
    }

    public Mesa(int numero,Queue<Order> fila ) {
        this.numero = numero;
        this.estadoAtual = Estado.VAZIA;
        this.prioridade = ((numero & 5) == 0);

        if (filaDePedidos == null) {
            filaDePedidos = fila;
        }
    }

    //* Recebendo Pedido
    public void receberPedido (Comida novoComida) {
        this.estadoAtual = Estado.ESPERANDO;
    }

    //* Concluir Mesa
    public void concluirPedido () {
        if (this.estadoAtual == Estado.ESPERANDO) {
            this.estadoAtual = Estado.VAZIA;
            System.out.printf("Mesa %d está satisfeita e %s\n", numero, estadoAtual);
            this.comidaAtual = null;
        }
    }

}
