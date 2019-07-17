package sample;

import java.io.Serializable;
import java.util.Vector;

public class SaveData implements Serializable {

    // stupid example for transient
    transient private Thread myThread;

    private String[] args;
    private String[][] items;
    private Vector<String> inventory;
    private String totalMessage;
    private int xCoordinate;
    private int yCoordinate;
    int minX;
    int maxX;
    int minY;
    int maxY;
    int playerX;
    int playerY;

    private Map map;


    public SaveData(String[] args, String[][] items, Vector<String> inventory, String totalMessage, int xCoordinate, int yCoordinate, int minX, int maxX, int minY, int maxY, int playerX, int playerY) {
        this.args = args;
        this.items = items;
        this.inventory = inventory;
        this.totalMessage = totalMessage;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.playerX = playerX;
        this.playerY = playerY;
    }


    public void setArgs() {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public void setItems(String[][] items) {
        this.items = items;
    }

    public String[][] getItems() {
        return items;
    }

    public void setInventory(Vector<String> inventory) {
        this.inventory = inventory;
    }

    public Vector<String> getInventory() {
        return inventory;
    }

    public void setTotalMessage(String totalMessage) {
        this.totalMessage = totalMessage;
    }

    public String getTotalMessage() {
        return totalMessage;
    }

    public void setxCoordinate() {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate() {
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setMinX() {
        this.minX = minX;
    }

    public int getMinX() {
        return minX;
    }

    public void setMaxX() {
        this.maxX = maxX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMinY() {
        this.minY = minY;
    }

    public int getMinY() {
        return minY;
    }

    public void setMaxY() {
        this.maxY = maxY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setPlayerX() {
        this.playerY = playerX;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerY() {
        this.playerY = playerY;
    }

    public int getPlayerY() {
        return playerY;
    }
}