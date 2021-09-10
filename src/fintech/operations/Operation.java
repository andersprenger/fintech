package fintech.operations;

public interface Operation extends Comparable<Operation> {
    int getQuantidade();

    int getPreco();
}
