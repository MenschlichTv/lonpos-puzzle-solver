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

    public boolean equals(Variation variation) {
        if (variation.getShape().length != this.getShape().length || variation.getShape()[0].length != this.getShape()[0].length) return false;
        for (int width = 0; width < variation.getShape().length; width++) {
            for (int height = 0; height < variation.getShape()[width].length; height++) {
                if (variation.getShape()[width][height] != this.getShape()[width][height]) return false;
            }
        }
        return true;
    }
}
