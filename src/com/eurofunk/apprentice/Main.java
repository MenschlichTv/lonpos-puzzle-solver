package com.eurofunk.apprentice;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        ArrayList<Block> blockList = game.initBlocks();

        game.permute(new BlockPackage(blockList,  5, 11));
        game.printSolutions();
        //game.generateHTMLFile();
    }

}
