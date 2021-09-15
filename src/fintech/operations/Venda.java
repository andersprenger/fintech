package fintech.operations;

public class Venda implements Operation {
    private int quantidade, preco;

    public Venda(int quantidade, int preco) {
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
