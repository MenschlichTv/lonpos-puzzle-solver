package com.eurofunk.apprentice;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private List<Variation> variations = new ArrayList<>();
    private int rotations;
    private boolean doMirror;

    Block(int[][] block, int rotationAmount, boolean mirror) {
        this.variations.add(new Variation(block, calculateStartIndex(block)));
        this.doMirror = mirror;
        this.rotations = rotationAmount;

        generateVariations();
    }

    private void generateVariations() {
        for (int i = 0; i < this.rotations; i++) {
            createRotatedVariations(this.variations.size() - 1);
        }
        if (this.doMirror) {
            for (int i = 0; i <= this.rotations; i++) {
                createMirroredVariations(i);
            }
        }
    }

    private void createRotatedVariations(int index) {
        int[][] baseForm = this.variations.get(index).getShape();
        int[][] rotatedForm = new int[baseForm[0].length][baseForm.length];

        for (int height = 0; height < rotatedForm.length; height++) {
            for (int width = 0; width < rotatedForm[0].length; width++) {
                rotatedForm[height][width] = baseForm[width][baseForm[0].length -1 - height];
            }
        }
        this.variations.add(new Variation(rotatedForm, calculateStartIndex(rotatedForm)));
    }

    private void createMirroredVariations(int index) {
        int[][] baseForm = this.variations.get(index).getShape();
        int[][] mirroredForm = new int[baseForm.length][baseForm[0].length];

        for (int height = 0; height < baseForm.length; height++) {
            for (int width = 0; width < baseForm[0].length; width++) {
                mirroredForm[height][width] = baseForm[height][baseForm[0].length -1 -width];
            }
        }
        this.variations.add(new Variation(mirroredForm, calculateStartIndex(mirroredForm)));
    }

    public List<Variation> getVariations() {
        return this.variations;
    }

    public int calculateStartIndex(int[][] twoDArray) {
        for (int i = 0; i < twoDArray[0].length; i++) {
            if (twoDArray[0][i] != 0) return i;
        }
        return twoDArray[0].length -1;
    }
}
