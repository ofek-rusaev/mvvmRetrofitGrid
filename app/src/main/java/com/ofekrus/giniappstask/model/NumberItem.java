package com.ofekrus.giniappstask.model;

import androidx.annotation.Nullable;

public class NumberItem  implements Comparable<NumberItem>{

    private int number;
    private boolean isPairEqualToZero;

    public NumberItem(int number, boolean isPairEqualToZero) {
        this.number = number;
        this.isPairEqualToZero = isPairEqualToZero;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        NumberItem num = (NumberItem) obj;
        if(this.number == num.number) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(NumberItem compareNumItem) {
        int compareNumber = compareNumItem.getNumber();
        return this.number-compareNumber;
    }

    public int getNumber() {
        return number;
    }

    public boolean isPairEqualToZero() {
        return isPairEqualToZero;
    }
}
