package br.poa.sprenger.fintech;

public abstract class Heap {

    protected int v[];
    private int used;

    public Heap() {
        used = 0;
        v = new int[100];
    }

    protected int left(int i) {
        return 2 * i + 1;
    }

    protected int right(int i) {
        return 2 * i + 2;
    }

    protected int parent(int i) {
        return (i - 1) / 2;
    }

    protected abstract void sift_up(int pos);

    public void put(int data) {
        v[used] = data;
        sift_up(used);
        used++;
    }

    protected abstract void sift_down(int pos);

    protected boolean isPosValid(int pos) {
        return pos < used;
    }

    public int get() {
        int res = v[0];
        v[0] = v[--used];
        sift_down(0);
        return res;
    }

    private int len(int a) {
        int res = 0;
        while (a > 0) {
            res++;
            a /= 10;
        }
        return res;
    }

    private void print(int b, int elem, int sp) {
        int i, j;

        System.out.println("");
        for (j = 0; j < used; j++) System.out.print(v[j] + " ");
        System.out.println("");

        while (true) {
            for (j = 0; j <= sp / 2; j++) System.out.print(" ");
            for (i = b; i < b + elem; i++) {
                if (i == used) return;
                int aux = len(v[i]);
                System.out.print(v[i]);
                for (j = 0; j < sp - aux; j++) System.out.print(" ");
            }
            System.out.println("");
            b = b + elem;
            elem = 2 * elem;
            sp = sp / 2;
        }
    }

    public void print() {
        System.out.println("");
        print(0, 1, 64);
        System.out.println("");
    }
}