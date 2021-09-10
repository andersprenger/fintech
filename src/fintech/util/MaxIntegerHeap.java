package fintech.util;

import java.util.Scanner;

public class MaxIntegerHeap {

    public static void main( String[] args ) {
        MaxIntegerHeap H = new MaxIntegerHeap();

        Scanner input = new Scanner( System.in );

        while ( input.hasNext() ) {
            String temp = input.next();

            if ( temp.equals( "quit" ) ) System.exit(0);
            if ( temp.equals( "get" ) )  System.out.println( H.get() );
            if ( temp.matches( "[0-9]+" ) ) H.put( Integer.parseInt( temp ) );

            H.print();
        }
    }

    protected Integer v[];
    private int used;

    public MaxIntegerHeap() {
        used = 0;
        v = new Integer[100];
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

    protected void sift_up(int pos) {
        int parentPos = parent(pos);

        // if parent is smaller, swap the values...

        if (v[parentPos].compareTo(v[pos]) < 0) {
            Integer aux = v[parentPos];
            v[parentPos] = v[pos];
            v[pos] = aux;

            sift_up(parentPos);
        }
    }

    public void put(Integer data) {
        v[used] = data;
        sift_up(used);
        used++;
    }

    protected void sift_down(int pos) {
        int leftPos = left(pos);
        int rightPos = right(pos);

        int biggerPos = pos;

        // first, check if one of the children is bigger, updating biggerPos in the case.

        if (isPosValid(leftPos) && v[leftPos].compareTo(v[biggerPos]) > 0) {
            biggerPos = leftPos;
        }

        if (isPosValid(rightPos) && v[rightPos].compareTo(v[biggerPos]) > 0) {
            biggerPos = rightPos;
        }

        // if pos is not equals to the biggerPos, swap the values...

        if (pos != biggerPos) {
            Integer aux = v[pos];
            v[pos] = v[biggerPos];
            v[biggerPos] = aux;

            sift_down(biggerPos);
        }
    }

    protected boolean isPosValid(int pos) {
        return pos < used;
    }

    public Integer get() {
        Integer res = v[0];
        v[0] = v[--used];
        sift_down(0);
        return res;
    }

    public Integer peek() {
        return isPosValid(0) ? v[0] : null;
    }

    public int size() {
        return used;
    }

 /* magic print stuff... idk how to make it work with generic types... */

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
//*/
}