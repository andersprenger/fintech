package fintech.operations;

public class Compra implements Comparable<Compra> {
    private int quantidade, preco;

    public Compra(int quantidade, int preco) {
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getPreco() {
        return preco;
    }

    @Override
    public int compareTo(Compra o) {
        return this.preco - o.preco;
    }
}