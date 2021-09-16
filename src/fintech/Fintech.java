package fintech;

import fintech.operations.Compra;
import fintech.operations.Venda;
import fintech.util.OperationsMaxHeap;
import fintech.util.OperationsMinHeap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Fintech {
    private final OperationsMaxHeap compras;
    private final OperationsMinHeap vendas;
    private long lucro;
    private long acoesNegociadas;

    public static void main(String[] args) {
        Fintech fintech = new Fintech();
        Scanner s = new Scanner(System.in);
        String p = s.next();
        long t0 = System.currentTimeMillis();
        fintech.run(p.contains(".txt") ? p : p + ".txt");
        long t1 = System.currentTimeMillis();
        System.out.println(fintech);
        System.out.println("Tempo de execução: " + (t1 - t0) + "ms");
    }

    public Fintech() {
        compras = new OperationsMaxHeap();
        vendas = new OperationsMinHeap();
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
                }

                else if (splitLine[0].equals("V")) {
                    Venda v = new Venda(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
                    vendas.put(v);
                }

                trade();
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    private void trade() {
        if (compras.size() == 0 || vendas.size() == 0 ) {
            return;
        }

        if (compras.peek().getPreco() >= vendas.peek().getPreco()) { // possível fazer compra e venda

            Compra compra = (Compra) compras.get();
            Venda venda = (Venda) vendas.get();

            if (compra.getQuantidade() > venda.getQuantidade()) { // mais compras que vendas
                compras.put(new Compra(compra.getQuantidade() - venda.getQuantidade(), compra.getPreco()));

                lucro += (compra.getPreco() - venda.getPreco()) * venda.getQuantidade();
                acoesNegociadas += venda.getQuantidade();
            }

            else if (venda.getQuantidade() > compra.getQuantidade()) { // mais vendas que compras
                vendas.put(new Venda(venda.getQuantidade() - compra.getQuantidade(), venda.getPreco()));

                lucro += (compra.getPreco() - venda.getPreco()) * compra.getQuantidade();
                acoesNegociadas += compra.getQuantidade();
            }

            else { // mesmo numero de compras e vendas...
                lucro += (compra.getPreco() - venda.getPreco()) * compra.getQuantidade();
                acoesNegociadas += compra.getQuantidade();
            }

            if (compras.peek().getPreco() >= vendas.peek().getPreco()) { // possível continuar fazendo compra e venda
                trade();
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