package com.ofekrus.giniappstask.model;

public class NumberItem  implements Comparable<NumberItem>{

    private int number;
    private boolean isPairEqualToZero = false;

    @Override
    public int compareTo(NumberItem compareNum) {
        int compareNumber=((NumberItem)compareNum).getNumber();
        return this.number-compareNumber;
    }

    public int getNumber() {
        return number;
    }

    public boolean isPairEqualToZero() {
        return isPairEqualToZero;
    }

    public void setPairEqualToZero(boolean pairEqualToZero) {
        isPairEqualToZero = pairEqualToZero;
    }
}
