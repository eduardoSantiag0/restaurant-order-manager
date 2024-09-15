package org.example.domains;

import lombok.Getter;

import java.security.SecureRandom;

@Getter
public class Order {
    SecureRandom generator = new SecureRandom();

    private Pedido pedido;
    private int numeroDaMesa;

    private static final int OPCOES_DE_PEDIDO = 3;
    private static final int TOTAL_MESAS = 20;

    public Order() {
        this.numeroDaMesa = intGenerator(20);
        setPedidoAtual(intGenerator(OPCOES_DE_PEDIDO));
    }

    public Order(int numeroDaMesa) {
        this.numeroDaMesa = numeroDaMesa;
        setPedidoAtual(intGenerator(OPCOES_DE_PEDIDO));
    }

    private int intGenerator (int bound) {
        return generator.nextInt(bound);
    }

    private void setPedidoAtual(int comida) {
        switch (comida) {
            case 0:
                this.pedido = Pedido.CARNE;
                break;
            case 1:
                this.pedido = Pedido.VEGANO;
                break;
            case 2:
                this.pedido = Pedido.PEIXE;
                break;
            default:
                this.pedido = null;
                break;
        }
    }

    public Pedido getPedido () {
        return this.pedido;
    }

    public int setNumeroMesa (int numero) {
        return this.numeroDaMesa = numero;
    }

    @Override
    public String toString() {
        return "Pedido=" + pedido +
                " na Mesa=" + numeroDaMesa +
                '|';
    }

}
