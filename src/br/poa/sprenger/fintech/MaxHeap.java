package br.poa.sprenger.fintech;

public class MaxHeap extends Heap {
    @Override
    protected void sift_up(int pos) {
        int parentPos = parent(pos);

        // if parent is smaller, swap the values...

        if (v[parentPos] < v[pos]) {
            int aux = v[parentPos];
            v[parentPos] = v[pos];
            v[pos] = aux;

            sift_up(parentPos);
        }
    }

    @Override
    protected void sift_down(int pos) {
        int leftPos = left(pos);
        int rightPos = right(pos);

        int biggerPos = pos;

        // first, check if one of the children is bigger, updating biggerPos in the case.

        if (isPosValid(leftPos) && v[leftPos] > v[biggerPos]) {
            biggerPos = leftPos;
        }

        if (isPosValid(rightPos) && v[rightPos] > v[biggerPos]) {
            biggerPos = rightPos;
        }

        // if pos is not equals to the biggerPos, swap the values...

        if (pos != biggerPos) {
            int aux = v[pos];
            v[pos] = v[biggerPos];
            v[biggerPos] = aux;

            sift_down(biggerPos);
        }
    }
}
