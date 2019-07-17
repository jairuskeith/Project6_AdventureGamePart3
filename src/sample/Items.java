package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Items {

    private static int ROWSMAX = 0; // North and South
    private static int COLSMAX = 0; // East and West
    public static String[][] items = new String[SelectedMap.ROWSMAX][SelectedMap.COLSMAX];
    private static String[] temp = new String[3];

    static void CreateItems(boolean printItems) throws FileNotFoundException {
        Scanner input = new Scanner(SelectedMap.itemsFile);
        File file = new File(input.nextLine());
        input = new Scanner(file);
        while (input.hasNextLine()) {
            String currentLine = input.nextLine();
            System.out.println(currentLine);

            temp = currentLine.split(";");
            if (items[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] == null) {
                items[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = temp[2];
            } else {
                items[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] += "," + temp[2];
            }
            System.out.println(items[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])]);
        }
    }
}

