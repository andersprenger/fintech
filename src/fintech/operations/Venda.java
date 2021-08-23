package fintech.operations;

public class Venda implements Comparable<Venda>{
    private int quantidade, preco;

    public Venda(int quantidade, int preco) {
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
    public int compareTo(Venda o) {
        return this.preco - o.preco;
    }
}
