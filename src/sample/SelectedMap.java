package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class SelectedMap {

    public static int ROWSMAX = 0; // North and South
    public static int COLSMAX = 0; // East and West
    public static char[][] map;
    public static int imagePixelSizeX = 0;
    public static int imagePixelSizeY = 0;

    static String itemsFile = "";
    static ArrayList<String> mapTiles = new ArrayList<>();
    static String[] args;

    SelectedMap(String[] selectedMap) {args = selectedMap;}

    // Scans the text file and creates the map
    static void CreateMap(boolean printMap) throws FileNotFoundException {
        // System.out.print("Please select a map: ");

        System.out.println("args.length: " + args.length);

        // HARD CODED MAP INPUT
        Scanner input = new Scanner(args[0]);
        File file = new File(input.nextLine());
        input = new Scanner(file);


        // READS THE ROW AND COLUMN SIZE FROM THE MAP
        ROWSMAX = input.nextInt();
        COLSMAX = input.nextInt();
        map = new char[COLSMAX][ROWSMAX];
        System.out.println("Map Dimensions: " + ROWSMAX + "x" + COLSMAX);

        String currentLine = input.nextLine();
        System.out.println(currentLine);
        if (input.hasNextLine()) {
            Boolean endOfFile = false;
            for (int y = 0; y < ROWSMAX; y++) {
                currentLine = input.nextLine();
                if (printMap) {System.out.println(" ");}
                if (!endOfFile) {
                    for (int x = 0; x < COLSMAX; x++) {
                        if (x < currentLine.length()) {
                            map[x][y] = currentLine.charAt(x);
                            if (printMap) {System.out.print(map[x][y]);}
                            if (!input.hasNextLine()) { endOfFile = true; }
                            if (printMap) {System.out.print(" ");}
                        }
                    }
                }
            }
            System.out.println(" ");
            imagePixelSizeX = input.nextInt();
            imagePixelSizeY = input.nextInt();
            System.out.println("Pixel size: " + imagePixelSizeX + "x" + imagePixelSizeY);
            itemsFile = input.nextLine();
            itemsFile = input.nextLine();
            System.out.println("itemsFile: " + itemsFile);

            int i = 0;
            while (input.hasNextLine()) {
                currentLine = input.nextLine();
                mapTiles.add(currentLine);
                i++;
            }
        }
    }
}
