package br.poa.sprenger.fintech;

public class MinHeap extends Heap {
    @Override
    protected void sift_up(int pos) {
        int parentPos = parent(pos);

        if (v[parentPos] > v[pos]) {
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

        int smallerPos = pos;

        // first, check if one of the children is smaller, updating smallerPos in the case.

        if (isPosValid(leftPos) && v[leftPos] < v[smallerPos]) {
            smallerPos = leftPos;
        }

        if (isPosValid(rightPos) && v[rightPos] < v[smallerPos]) {
            smallerPos = rightPos;
        }

        // if pos is not equals to the smallerPos, swap the values...

        if (pos != smallerPos) {
            int aux = v[pos];
            v[pos] = v[smallerPos];
            v[smallerPos] = aux;

            sift_down(smallerPos);
        }
    }
}
