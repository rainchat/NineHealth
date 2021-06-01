package com.rainchat.ninehealth.utilitis.object;

import java.util.Random;

public class MinAndMax {
    int min;
    int max;

    public MinAndMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public MinAndMax(int min) {
        this.min = min;
        this.max = min;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getRandom() {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
