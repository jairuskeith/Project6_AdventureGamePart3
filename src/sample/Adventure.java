package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.*;

// TODO be able to move the character with arrow keys
// TODO the user must be able to open a FileChooser when the first save the game

public class Adventure extends Application implements EventHandler<ActionEvent> {

    String filename = "Saved Game";
    SaveData saveData;


    static String keyInput = "";

    static String[] arguments;

    static TextArea textArea = new TextArea();
    static TextField input = new TextField();
    static GridPane mapGridPane = new GridPane();
    static GridPane gridPane;
    static VBox layout;
    static HBox hbox;

    static SelectedMap selectMap;
    static Map map = new Map(0, 5, 0, 5);
    static Map player = new Map(0, 0);

    String inputText;
    String totalMessage;

    static int mapxCoordinate = 0;
    static int mapyCoordinate = 0;

    static int playerX = 0; // 0, 10, 20, 30, 40
    static int playerY = 0;
    static int minX = 0;
    static int maxX = 4;
    static int minY = 0;
    static int maxY = 4;

    public static void main(String[] args) throws FileNotFoundException {
        arguments = args;
        selectMap = new SelectedMap(args);
        selectMap.CreateMap(true); // Gets the map from the .txt file and stores in a 2 dimensional array
        Items.CreateItems(true);
        map.PrintMap();
        player.PrintPlayer();
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.showSaveDialog(primaryStage);

        input.setOnAction(this);
        Buttons.Enter().setOnAction(this);
        Buttons.Open().setOnAction(this);
        Buttons.Save().setOnAction(this);
        Buttons.Quit().setOnAction(this);
        primaryStage.setTitle("Jairus Keith Program 6: Adventure Game Pt3"); // Set the stage title
        Scene scene = new Scene(createContent());

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        UpdateTextArea(true, "go north");
                        UpdateMap();
                        UpdatePlayer();
                        System.out.println("UP");
                        break;
                    case DOWN:
                        UpdateTextArea(true, "go south");
                        UpdateMap();
                        UpdatePlayer();
                        System.out.println("DOWN");
                        break;
                    case LEFT:
                        UpdateTextArea(true, "go west");
                        UpdateMap();
                        UpdatePlayer();
                        System.out.println("LEFT");
                        break;
                    case RIGHT: // East
                        UpdateTextArea(true, "go east");
                        UpdateMap();
                        UpdatePlayer();
                        System.out.println("RIGHT");
                        break;
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show(); // Display the stage
    }

    private Parent createContent() {
        hbox = new HBox(Buttons.Enter(), Buttons.Open(), Buttons.Save(), Buttons.Quit());
        hbox.setSpacing(75);
        layout = new VBox(20, textArea, input, hbox);
        textArea.setPrefHeight(150);
        textArea.setWrapText(true);
        textArea.selectPositionCaret(textArea.getLength());
        textArea.deselect();
        gridPane = new GridPane();
        gridPane.setHgap(200);
        gridPane.setConstraints(mapGridPane, 0, 0);
        gridPane.setConstraints(layout, 0, 1);
        gridPane.getChildren().addAll(mapGridPane, layout);

        return gridPane;
    }

    private void UpdateTextArea(boolean keyPressed, String keyInput) {
        int caretPosition = textArea.caretPositionProperty().get();

        if (keyPressed) {
            inputText = keyInput;
        } else {
            inputText = input.getText();
        }
        if (!inputText.isEmpty()) {
            textArea.setScrollTop(Double.MIN_VALUE);
            Commands commands = new Commands(inputText);
            totalMessage = textArea.getText();
            totalMessage = totalMessage + "'" + inputText + "'" + "\n" + commands.GetUserInput() + "\n\n";
            if (Commands.ItemsFound() != null) {
                totalMessage = totalMessage + "You found " + Commands.ItemsFound() + "\n";
            }
            textArea.setText(totalMessage);
            textArea.positionCaret(caretPosition + 100);
            input.clear();
        }
    }

    // The map is update when the player puts in a command
    private void UpdateMap() {
        if (Commands.xCoordinate < minX || Commands.xCoordinate > maxX) {
            if (Commands.commandArray[1].equalsIgnoreCase("west")) {
                minX--;
                maxX--;
            } else if (Commands.commandArray[1].equalsIgnoreCase("east")) {
                if (Commands.xCoordinate < SelectedMap.COLSMAX) {
                    minX++;
                    maxX++;
                }
            }
        } else if (Commands.yCoordinate < minY || Commands.yCoordinate > maxY) {
            if (Commands.commandArray[1].equalsIgnoreCase("north")) {
                if (Commands.yCoordinate < SelectedMap.ROWSMAX) {
                    minY--;
                    maxY--;
                }
            } else if (Commands.commandArray[1].equalsIgnoreCase("south")) {
                minY++;
                maxY++;
            }
        }
        map = new Map(minX, maxX, minY, maxY);
        map.PrintMap();
    }

    private void UpdatePlayer() {
        if (Commands.commandArray[1].equalsIgnoreCase("west")) {
            if (playerX > 0) {
                playerX -= 10;
            }
        } else if (Commands.commandArray[1].equalsIgnoreCase("east")) {
            if (playerX < 40) {
                playerX += 10;
            }
        } else if (Commands.commandArray[1].equalsIgnoreCase("north")) {
            if (playerY > 0) {
                playerY -= 10;
            }
        } else if (Commands.commandArray[1].equalsIgnoreCase("south")) {
            if (playerY < 40) {
                playerY += 10;
            }
        }
        player = new Map(playerX, playerY);
        player.PrintPlayer();
    }

    //
    private void OpenGame(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            saveData = (SaveData) in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        arguments = saveData.getArgs();
        Items.items = saveData.getItems();
        Commands.inventory = saveData.getInventory();
        textArea.setText(saveData.getTotalMessage());
        Commands.xCoordinate = saveData.getxCoordinate();
        Commands.yCoordinate = saveData.getyCoordinate();
        minX = saveData.getMinX();
        maxX = saveData.getMaxX()+1;
        minY = saveData.getMinY();
        maxY = saveData.getMaxY()+1;
        playerX = saveData.getPlayerX();
        playerY = saveData.getPlayerY();

        System.out.println(saveData);
    }

    // Saves game
    private void SaveGame(Serializable data, String fileName) throws IOException {
        map = new Map(minX, maxX, minY, maxY);
        saveData = new SaveData(arguments, Items.items, Commands.inventory, totalMessage, Commands.xCoordinate, Commands.yCoordinate, minX, maxX, minY, maxY, playerX, playerY);

        // save the object to file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(saveData);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == Buttons.Enter() || actionEvent.getSource() == input) { // Send
            UpdateTextArea(false, "");
            UpdateMap();
            if (Commands.commandArray.length >= 2) {
                UpdatePlayer();
            } else {
                player.PrintPlayer();
            }
        } else if (actionEvent.getSource() == Buttons.Open()) { // OpenGame
            try {
                OpenGame(filename);
                Map.DeleteMap();
                selectMap = new SelectedMap(arguments);
                UpdateMap();
                selectMap.CreateMap(true);
                map.PrintMap();
                player = new Map(playerX, playerY);
                player.PrintPlayer();
                //   selectMap = new SelectedMap();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource() == Buttons.Save()) { // SaveGame
            try {
                SaveGame(saveData, filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource() == Buttons.Quit()) { // Quit
            System.exit(0);
        }
    }
}




