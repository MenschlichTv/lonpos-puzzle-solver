package com.eurofunk.apprentice;

public class Variation {
    private int[][] shape;
    private int startIndex;

    Variation(final int[][] shape, final int startIndex) {
        this.shape = shape;
        this.startIndex = startIndex;
    }

    public int[][] getShape() {
        return shape;
    }

    public int getStartIndex() {
        return startIndex;
    }
}
