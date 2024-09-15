package org.example.domains;

import lombok.Getter;

import java.security.SecureRandom;

@Getter
public class Order {
    SecureRandom generator = new SecureRandom();

    private Pedido pedido;
    private int numeroDaMesa;

    public Order() {
        this.numeroDaMesa = intGenerator();
        setPedidoAtual(intGenerator());
    }

    public Order(int numeroDaMesa) {
        this.numeroDaMesa = numeroDaMesa;
        setPedidoAtual(intGenerator());
    }

    private int intGenerator () {
        return generator.nextInt(3);
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
