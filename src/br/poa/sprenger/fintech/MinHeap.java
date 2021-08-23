package br.poa.sprenger.fintech;

public class MinHeap<T extends Comparable<T>> extends Heap<T> {
    @Override
    protected void sift_up(int pos) {
        int parentPos = parent(pos);

        if (v[parentPos].compareTo(v[pos]) > 0) {
            T aux = v[parentPos];
            v[parentPos] = v[pos];
            v[pos] = aux;

            sift_up(parentPos);
        }
    }

    @Override
    protected void sift_down(int pos) {
        int leftPos = left(pos);
        int rightPos = right(pos);

        int smallerPos = pos;

        // first, check if one of the children is smaller, updating smallerPos in the case.

        if (isPosValid(leftPos) && v[leftPos].compareTo(v[smallerPos]) < 0) {
            smallerPos = leftPos;
        }

        if (isPosValid(rightPos) && v[rightPos].compareTo(v[smallerPos]) < 0) {
            smallerPos = rightPos;
        }

        // if pos is not equals to the smallerPos, swap the values...

        if (pos != smallerPos) {
            T aux = v[pos];
            v[pos] = v[smallerPos];
            v[smallerPos] = aux;

            sift_down(smallerPos);
        }
    }
}
