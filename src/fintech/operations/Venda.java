package fintech.operations;

public class Venda implements Operation {
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
    public int compareTo(Operation o)  {
        return this.getPreco() - o.getPreco();
    }
}
