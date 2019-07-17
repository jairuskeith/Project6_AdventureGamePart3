package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Map {

    static int playerX = 0; // 0, 10, 20, 30, 40
    static int playerY = 0;

    static int minX = 0;
    static int maxX = 0;
    static int minY = 0;
    static int maxY = 0;
    static int xCoordinate = 0;
    static int yCoordinate = 0;
    static String mapInfo;
    static String[] mapInfoArr;
    static Character symbol;

    static Image person = new Image("MapPics/person.png");
    static ImageView characterImageView = new ImageView(person);
        // characterImageView.setFitWidth(SelectedMap.imagePixelSizeX);
        // characterImageView.setFitHeight(SelectedMap.imagePixelSizeY);
        // Adventure.mapGridPane.getChildren().remove(characterImageView);
        // Adventure.mapGridPane.add(characterImageView, playerX, playerY);


    Map(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    Map(int minimumX, int maximumX, int minimumY, int maximumY) {
        minX = minimumX;
        maxX = maximumX;
        minY = minimumY;
        maxY = maximumY;
        this.playerX = playerX;
        this.playerY = playerY;
    }

    public static void PrintMap() {
        String temp = "";
        int x = 0;
        int y = 0;
        for (int i = minY; i < maxY; i++) { // Column
            for (int j = minX; j < maxX; j++) { // Row
                int k = 0;
                while (k < SelectedMap.mapTiles.size()) {
                    mapInfo =  SelectedMap.mapTiles.get(k);
                    mapInfoArr = mapInfo.split(";");
                    symbol = mapInfoArr[0].charAt(0);

                    if (symbol.equals(SelectedMap.map[j][i])) {
                        temp = mapInfoArr[2];
                    }
                    k++;
                }

                Image image = new Image(temp);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(SelectedMap.imagePixelSizeX);
                imageView.setFitHeight(SelectedMap.imagePixelSizeY);
                Adventure.mapGridPane.add(imageView, x, y);
                x = x + 10;
            }
            x = 0;
            y = y + 10;
        }
    }

    public static void DeleteMap() {
                Adventure.mapGridPane.getChildren().clear();
    }

    public static void PrintPlayer() {
        characterImageView.setFitWidth(SelectedMap.imagePixelSizeX);
        characterImageView.setFitHeight(SelectedMap.imagePixelSizeY);
        Adventure.mapGridPane.getChildren().remove(characterImageView);
        Adventure.mapGridPane.add(characterImageView, playerX, playerY);
    }
}
