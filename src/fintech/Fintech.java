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
        if (compras.peek() == null || vendas.peek() == null) {
            return;
        }

        else if (compras.peek().getPreco() - vendas.peek().getPreco() >= 0) {
            Compra c = (Compra) compras.get();
            Venda v = (Venda) vendas.get();

            if (c.getQuantidade() > v.getQuantidade()) { // mais compras do que vendas
                lucro += v.getQuantidade() * c.getPreco() - v.getPreco();
                acoesNegociadas += v.getQuantidade();
                compras.put(new Compra(c.getQuantidade() - v.getQuantidade(), c.getPreco()));
            }

            else if (v.getQuantidade() > c.getQuantidade()) { // mais vendas do que compras
                lucro += c.getQuantidade() * c.getPreco() - v.getPreco();
                acoesNegociadas += c.getQuantidade();
                vendas.put(new Venda(c.getQuantidade() - v.getQuantidade(), v.getPreco()));
            }

            else { // numero de vendas eh igual a compras
                lucro += c.getQuantidade() * c.getPreco() - v.getPreco();
                acoesNegociadas += c.getQuantidade();
            }
        }

        if (compras.peek().getPreco() - vendas.peek().getPreco() > 0) {
            trade();
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