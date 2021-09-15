package fintech.operations;

public class Compra implements Operation {
    private int quantidade, preco;

    public Compra(int quantidade, int preco) {
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @Override
    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public int getPreco() {
        return preco;
    }

    @Override
    public int compareTo(Operation o)  {
        return this.getPreco() - o.getPreco();
    }
}