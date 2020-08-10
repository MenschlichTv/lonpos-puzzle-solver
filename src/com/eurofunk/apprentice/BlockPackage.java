package com.eurofunk.apprentice;

import java.util.ArrayList;

public class BlockPackage {
    private int[][] grid;
    private ArrayList<Block> blockList;

    BlockPackage(ArrayList<Block> blockList, int height, int width) {
        this.blockList = blockList;
        this.grid = new int[height][width];
    }

    BlockPackage(ArrayList<Block> blockList, int[][] grid) {
        this.blockList = blockList;
        this.grid = grid;
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public ArrayList<Block> getBlockList() {
        return this.blockList;
    }

}
