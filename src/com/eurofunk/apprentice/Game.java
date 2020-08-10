package com.eurofunk.apprentice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private ArrayList<Block> blockList = new ArrayList<>();
    private ArrayList<BlockPackage> solutionList = new ArrayList<>();
    private int counter;

    ArrayList<Block> initBlocks() {
        int[][] intArr1 = {
                {1, 1},
                {1, 0},
                {1, 0}
        };
        int[][] intArr2 = {
                {0, 2},
                {2, 2},
                {2, 0}
        };
        int[][] intArr3 = {
                {3, 0},
                {3, 0},
                {3, 3},
                {0, 3}
        };
        int[][] intArr4 = {
                {4, 4, 4, 4},
                {0, 0, 4, 0}
        };
        int[][] intArr5 = {
                {5, 5, 5},
                {5, 0, 5}
        };
        int[][] intArr6 = {
                {6, 6},
                {0, 6},
                {0, 6},
                {0, 6}
        };
        int[][] intArr7 = {
                {0, 7, 0},
                {7, 7, 0},
                {0, 7, 7}
        };
        int[][] intArr8 = {
                {0, 8},
                {8, 8},
                {0, 8}
        };
        int[][] intArr9 = {
                {9, 0, 0},
                {9, 9, 0},
                {0, 9, 9}
        };
        int[][] intArr10 = {
                {0, 0, 10},
                {0, 0, 10},
                {10, 10, 10}
        };
        int[][] intArr11 = {
                {11, 11, 0},
                {11, 11, 11}
        };
        int[][] intArr12 = {
                {12, 0},
                {12, 12}
        };

        Block block1 = new Block(intArr1, 3, true);
        Block block2 = new Block(intArr2, 1, true);
        Block block3 = new Block(intArr3, 3, true);
        Block block4 = new Block(intArr4, 3, true);
        Block block5 = new Block(intArr5, 3, false);
        Block block6 = new Block(intArr6, 3, true);
        Block block7 = new Block(intArr7, 3, true);
        Block block8 = new Block(intArr8, 3, false);
        Block block9 = new Block(intArr9, 3, false);
        Block block10 = new Block(intArr10, 3, false);
        Block block11 = new Block(intArr11, 3,true);
        Block block12 = new Block(intArr12, 3, false);

        blockList.add(block1);
        blockList.add(block2);
        blockList.add(block3);
        blockList.add(block4);
        blockList.add(block5);
        blockList.add(block6);
        blockList.add(block7);
        blockList.add(block8);
        blockList.add(block9);
        blockList.add(block10);
        blockList.add(block11);
        blockList.add(block12);

        return this.blockList;
    }

    public void permute(BlockPackage blockPackage) {
        for (Block block : blockPackage.getBlockList()) {
            for (Variation variation : block.getVariations()) {

                BlockPackage tempBlockPackage = cloneBlockPackage(blockPackage);
                if (!placeBlock(variation.getShape(), variation.getStartIndex(), tempBlockPackage.getGrid())) {
                    continue;
                }
                tempBlockPackage.getBlockList().remove(block);
                if (tempBlockPackage.getBlockList().isEmpty()) {
                    this.solutionList.add(tempBlockPackage);
                    counter++;
                    if (counter % 1000 == 0) generateHTMLFile(this.solutionList, counter - 1000, counter);
                    return;
                }
                permute(tempBlockPackage);
            }
        }
    }

    private boolean placeBlock(int[][] block, int startIndex, int[][] grid) {
        int[] gridCord = findEmptyPos(grid);
        if (gridCord == null) return false;

        for (int height = 0; height < block.length; height++) {
            for (int width = 0; width < block[0].length; width++) {
                if (isTileEmpty(block[height][width])) continue;

                int blockX = height + gridCord[0];
                int blockY = width + gridCord[1] - startIndex;

                if (!placeTile(blockX, blockY, grid, block[height][width])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean placeTile(int blockX, int blockY, int[][] grid, int id) {
        if (isInBounds(blockX, blockY, grid) && isTileEmpty(grid[blockX][blockY])) {
            grid[blockX][blockY] += id;
            return true;
        }
        return false;
    }

    private boolean isInBounds(int blockX, int blockY, int[][] grid) {
        return (blockY >= 0 && blockX >= 0 && blockY < grid[0].length && blockX < grid.length);
    }

    private boolean isTileEmpty(int tile) {
        return tile == 0;
    }

    private BlockPackage cloneBlockPackage(BlockPackage blockPackage) {
        return new BlockPackage(new ArrayList<>(blockPackage.getBlockList()), cloneArray(blockPackage.getGrid()));
    }

    private int[][] cloneArray(int[][] twoDArray) {
        int[][] temp = new int[twoDArray.length][twoDArray[0].length];

        for (int height = 0; height < twoDArray.length; height++) {
            for (int width = 0; width < twoDArray[0].length; width++) {
                temp[height][width] = twoDArray[height][width];
            }
        }
        return temp;
    }

    private int[] findEmptyPos(int[][] grid) {
        for (int height = 0; height < grid.length; height++) {
            for (int width = 0; width < grid[0].length; width++) {
                if (grid[height][width] == 0) return new int[]{height, width};
            }
        }
        return null;
    }

    public void printSolutions() {
        for (int height = 0; height < solutionList.size(); height++) {
            System.out.println("\n" + (height + 1) + ":");
            for (int width = 0; width < solutionList.get(height).getGrid().length; width++) {
                System.out.println(Arrays.toString(solutionList.get(height).getGrid()[width]));
            }
        }
    }

    public void generateHTMLFile(List<BlockPackage> solutions, int start, int stop) {
        // TODO: print last <1000 solutions
        HTMLConverter htmlConverter = new HTMLConverter();
        htmlConverter.generateHTML(solutions, start, stop);
        System.out.println("<=========[ HTML FILE \"Lonpos_" + stop + "\" GENERATED ]=========>");
    }
}
