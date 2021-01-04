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
                "<div class=\"header\">Lonpos Solutions | " + start + "-" + stop + "</div><div class=\"space\"></div>";

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
            e.printStackTrace();
        }
    }

    private String getColor(final int id) {
        switch (id) {
            default:
                return "";
            case 1:
                return "orange";
            case 2:
                return "dark-blue";
            case 3:
                return "dark-green";
            case 4:
                return "pink-skin";
            case 5:
                return "yellow";
            case 6:
                return "light-blue";
            case 7:
                return "darker-grey";
            case 8:
                return "lime-green";
            case 9:
                return "pink";
            case 10:
                return "purple";
            case 11:
                return "red";
            case 12:
                return "light-grey";
        }
    }
}
