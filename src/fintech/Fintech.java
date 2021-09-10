package fintech;

import fintech.operations.Compra;
import fintech.operations.Venda;
import fintech.util.MaxOperationsHeap;
import fintech.util.MinOperationsHeap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Fintech {
    private final MaxOperationsHeap compras;
    private final MinOperationsHeap vendas;
    private int lucro;
    private int acoesNegociadas;

    public static void main(String[] args) {
        Fintech fintech = new Fintech();
        Scanner s = new Scanner(System.in);
        String p = s.next();
        fintech.run(p.contains(".txt") ? p : p + ".txt");
        System.out.println(fintech);
    }

    public Fintech() {
        compras = new MaxOperationsHeap();
        vendas = new MinOperationsHeap();
        lucro = 0;
    }

    public void run(String fileName) {
        Path path = Path.of(fileName).toAbsolutePath();
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] splitLine = line.split(" ");

                if (splitLine[0].equals("C")) {
                    Compra c = new Compra(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
                    compras.put(c);
                } else if (splitLine[0].equals("V")) {
                    Venda v = new Venda(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
                    vendas.put(v);
                }

                trade();
            }

            while (compras.size() > 0 && vendas.size() > 0) {
                if (compras.peek() == null || vendas.peek() == null) {
                    break;
                } else if ((compras.peek().getPreco() - vendas.peek().getPreco() <= 0)) {
                    break;
                } else {
                    trade();
                }
            }

        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    private void trade() {
        if (compras.peek() == null || vendas.peek() == null) {
            return;
        } else if (compras.peek().getPreco() - vendas.peek().getPreco() > 0) {
            Compra c = (Compra) compras.get();
            Venda v = (Venda) vendas.get();

            int diffQuantidade = c.getQuantidade() - v.getQuantidade();
            int diffPreco = c.getPreco() - v.getPreco();

            if (diffQuantidade > 0) { // mais compras do que vendas
                lucro += v.getQuantidade() * diffPreco;
                acoesNegociadas += v.getQuantidade();
                compras.put(new Compra(diffQuantidade, c.getPreco()));
            }

            else if (diffQuantidade < 0) { // mais vendas do que compras
                lucro += c.getQuantidade() * diffPreco;
                acoesNegociadas += c.getQuantidade();
                vendas.put(new Venda(-diffQuantidade, v.getPreco()));
            }

            else { // numero de vendas eh igual a compras
                lucro += c.getQuantidade() * diffPreco;
                acoesNegociadas += c.getQuantidade();
            }
        }
    }

    @Override
    public String toString() {
        return "Fintech\n" +
                "Lucro: " + lucro +
                "\nAções negociadas: " + acoesNegociadas +
                "\nCompras pendentes: " + compras.size() +
                "\nVendas pendentes: " + vendas.size();
    }
}