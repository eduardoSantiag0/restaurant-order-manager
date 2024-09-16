package org.example.domains;

import lombok.Getter;

import java.security.SecureRandom;

@Getter
public class Order {
    SecureRandom generator = new SecureRandom();

    private Comida comida;
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
                this.comida = Comida.CARNE;
                break;
            case 1:
                this.comida = Comida.VEGANO;
                break;
            case 2:
                this.comida = Comida.PEIXE;
                break;
            default:
                this.comida = null;
                break;
        }
    }

    public Comida getComida() {
        return this.comida;
    }

    public int setNumeroMesa (int numero) {
        return this.numeroDaMesa = numero;
    }

    @Override
    public String toString() {
        return "Pedido=" + comida +
                " na Mesa=" + numeroDaMesa +
                '|';
    }

}
