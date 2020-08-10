package com.eurofunk.apprentice;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HTMLConverter {

    public void generateHTML(List<BlockPackage> blockPackages, int start, int stop) {
        String setup = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>LONPOS View</title>\n" +
                "    <link rel=\"stylesheet\" href=\"index.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"header\">Lonpos Solutions |" + start + "-" + stop + "</div><div class=\"space\"></div>";

        String end = "\n" +
                "</body>\n" +
                "</html>";

        String data = setup;

        for (int i = start; i < stop; i++) {
            int[][] currGrid = blockPackages.get(i).getGrid();

            data = data.concat("<div class=\"grid\">\n");
            for (int row = 0; row < currGrid.length; row++) {
                for (int column = 0; column < currGrid[row].length; column++) {
                    String color = getColor(currGrid[row][column]);
                    String tile = "    <div class=\"tile " + color + "\"></div>\n";
                    data = data.concat(tile);
                }
            }
            data = data.concat("</div>\n");
        }
        createFile(data.concat(end), stop);
    }

    private void createFile(final String data, final int stop) {
        try {
            FileWriter myWriter = new FileWriter("lonpos_" + stop + ".html");
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error alla");
            e.printStackTrace();
        }
    }

    private String getColor(final int id) {
        switch (id) {
            default:
                return "";
            case 1:
                return "red";
            case 2:
                return "blue";
            case 3:
                return "yellow";
            case 4:
                return "violet";
            case 5:
                return "darkRed";
            case 6:
                return "pink";
            case 7:
                return "lime";
            case 8:
                return "turquoise";
            case 9:
                return "orange";
            case 10:
                return "darkBlue";
            case 11:
                return "lightGreen";
            case 12:
                return "lightBlue";
        }
    }
}
