package fintech.util;

import fintech.operations.Operation;

public class OperationsMaxHeap extends OperationsHeap {
    @Override
    protected void sift_up(int pos) {
        int parentPos = parent(pos);

        // if parent is smaller, swap the values...

        if (v[parentPos].compareTo(v[pos]) < 0) {
            Operation aux = v[parentPos];
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

        if (isPosValid(leftPos) && v[leftPos].compareTo(v[biggerPos]) > 0) {
            biggerPos = leftPos;
        }

        if (isPosValid(rightPos) && v[rightPos].compareTo(v[biggerPos]) > 0) {
            biggerPos = rightPos;
        }

        // if pos is not equals to the biggerPos, swap the values...

        if (pos != biggerPos) {
            Operation aux = v[pos];
            v[pos] = v[biggerPos];
            v[biggerPos] = aux;

            sift_down(biggerPos);
        }
    }
}
