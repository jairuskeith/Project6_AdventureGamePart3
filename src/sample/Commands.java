package sample;

import java.io.Serializable;
import java.util.Vector;

class Commands implements Serializable {

    static String input;
    static String returnMsg;
    static String items;
    static String requestedItem = "";

    Commands(String userInput) {input = userInput;}

    public static int yCoordinate = 0;
    public static int xCoordinate = 0;

    public static String[] commandArray;
    public static Vector<String> inventory = new Vector<>();

    //
    static String GetUserInput() {
        System.out.println("Please enter a command: ");
        commandArray = input.split(" ");
        System.out.println("");
        if (commandArray[0].equalsIgnoreCase("go") || commandArray[0].equalsIgnoreCase("g")) {
            if (commandArray[1].equals(null)) {
                returnMsg = "You must enter a valid cardinal direction after " + "'" + commandArray[0] + "'";
                System.out.println("You must enter a valid cardinal direction after " + "'" + commandArray[0] + "'");
            } else {
                returnMsg = ParameterCheck();
            }
        } else if (commandArray[0].equalsIgnoreCase("inventory") || commandArray[0].equalsIgnoreCase("i")) {
            returnMsg = Inventory();
        } else if (commandArray[0].equalsIgnoreCase("take") || commandArray[0].equalsIgnoreCase("t")) {
            returnMsg = Take();
        } else if (commandArray[0].equalsIgnoreCase("drop") || commandArray[0].equalsIgnoreCase("d")) {
            returnMsg = Drop();
        } else if (!commandArray[0].equalsIgnoreCase("quit") && !commandArray[0].equalsIgnoreCase("q")) {
            returnMsg = "'" + commandArray[0] + "'" + " is not a valid command.";
            System.out.println("'" + commandArray[0] + "'" + " is not a valid command.");
        }
        return returnMsg;
    }

    private static String ParameterCheck() {
        if ((commandArray[1].equalsIgnoreCase("north") || commandArray[1].equalsIgnoreCase("east") || commandArray[1].equalsIgnoreCase("south") || commandArray[1].equalsIgnoreCase("west"))) {
            returnMsg = Move(commandArray[1]);
        } else {
            System.out.println("'" + commandArray[1] + "'" + " is not a valid direction.");
            returnMsg = "'" + commandArray[1] + "'" + " is not a valid direction.";
        }
        return returnMsg;
    }

    //
    public static String Move(String direction) {
        if (direction.equalsIgnoreCase("south") && (yCoordinate < (SelectedMap.ROWSMAX - 1))) {
            yCoordinate++;
            returnMsg = "Moving " + direction.toLowerCase() + "...";
            System.out.println("Moving " + direction.toLowerCase() + "...");
        } else if (direction.equalsIgnoreCase("north") && (yCoordinate != 0)) {
            yCoordinate--;
            returnMsg = "Moving " + direction.toLowerCase() + "...";
            System.out.println("Moving " + direction.toLowerCase() + "...");
        } else if (direction.equalsIgnoreCase("east") && ((xCoordinate) < SelectedMap.COLSMAX - 1)) {
            xCoordinate++;
            returnMsg = "Moving " + direction.toLowerCase() + "...";
            System.out.println("Moving " + direction.toLowerCase() + "...");
        } else if (direction.equalsIgnoreCase("west") && (xCoordinate != 0)) {
            xCoordinate--;
            returnMsg = "Moving " + direction.toLowerCase() + "...";
            System.out.println("Moving " + direction.toLowerCase() + "...");
        } else {
            returnMsg = "Out of bounds";
            System.out.println("Out of bounds");
        }
        returnMsg += "\nYou are at location: "  + "(" + yCoordinate + "," + xCoordinate + ") in terrain " + SelectedMap.map[xCoordinate][yCoordinate];
        System.out.println("You are at location: "  + "(" + yCoordinate + "," + xCoordinate + ") in terrain " + SelectedMap.map[xCoordinate][yCoordinate]);
        System.out.println("");
        return returnMsg;
    }

    // The 'take' command that will pick up the requested item and add it to the player's inventory
    // Removes the item from the map/item array
    public static String Take() {
        String[] temp;
        boolean itemExists = false;
        String remaining;
        if (ItemsFound() != null) {
            temp = ItemsFound().split(",");
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].equals(RequestedItem())) {
                    returnMsg = "you picked up " + temp[i]; // Returns a message that tells the user what they picked up
                    inventory.add(temp[i]);
                    Items.items[yCoordinate][xCoordinate] = temp[i];
                    itemExists = true;
                }
            }
            Items.items[yCoordinate][xCoordinate] = "";
            for (int i = 0; i < temp.length; i++) {
                if (!temp[i].equals(RequestedItem())) {
                    Items.items[yCoordinate][xCoordinate] += temp[i];
                }
            }
            if (Items.items[yCoordinate][xCoordinate].equals("")) {
                Items.items[yCoordinate][xCoordinate] = null;
            }
        } else {
            returnMsg = "No items found";
        }
        if (!itemExists) {
            returnMsg = RequestedItem() + "was not found";
        }
        System.out.println(returnMsg);
        return returnMsg;
    }

    public static String Drop() {
        boolean itemExists = false;
        if (inventory.size() == 0) {
            returnMsg = "Your inventory is empty";
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                if (RequestedItem().equals(inventory.get(i))) {
                    itemExists = true;
                    if (Items.items[yCoordinate][xCoordinate] == null) {
                        Items.items[yCoordinate][xCoordinate] = RequestedItem();
                    } else {
                        Items.items[yCoordinate][xCoordinate] += "," + RequestedItem();
                    }
                    inventory.remove(i);
                    returnMsg = "you dropped " + RequestedItem(); // Returns a message that tells the user what they picked up
                }
            }
            if (!itemExists) {
                returnMsg = RequestedItem() + "was not found in your inventory";
            }
        }
        System.out.println("Drop(): " + returnMsg);
        return returnMsg;
    }

    // Holds the players Inventory in an array call inventory[]
    private static String Inventory() {
        System.out.println("Inventory: ");
        returnMsg = "Your inventory: \n";
        for(int i = 0; i < inventory.size(); i++) {
            returnMsg += inventory.get(i);
            returnMsg += "\n";
            System.out.println(returnMsg);
        }

        if (inventory.size() == 0) {
            returnMsg = "Your inventory is empty";
        }


        return returnMsg;
    }

    // Returns a String object taken from the commandArray[]. User input for what item they want to pickup
    private static String RequestedItem() {
        requestedItem = commandArray[1];
        if (1 < commandArray.length) {
            requestedItem += " ";
        }
        for (int i = 2; i < commandArray.length; i++) {
            requestedItem += commandArray[i];
            if (i+1 < commandArray.length) {
                requestedItem += " ";
            }
        }
        System.out.println("requestedItem: " + requestedItem);
        return requestedItem;
    }

    // Checks if there are any items where the player is currently located on the map
    public static String ItemsFound() {
        if (Items.items[yCoordinate][xCoordinate] != null) {
            items = Items.items[yCoordinate][xCoordinate];
            System.out.println(items);
        } else {
            items = null;
        }
        return items;
    }
}
