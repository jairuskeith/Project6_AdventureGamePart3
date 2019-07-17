package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Buttons extends Adventure implements EventHandler<ActionEvent> {
    static Button enter = new Button("Enter");
    static Button open = new Button("Open");
    static Button save = new Button("Save");
    static Button quit = new Button("Quit");
    private static int y = 10;

    //
    static Button Enter() {
        enter.setTranslateX(25);
        enter.setTranslateY(-10);
        enter.getStyleClass().add("button");
        return enter;
    }

    //
    static Button Open() {
        open.setTranslateX(25);
        open.setTranslateY(-10);
        open.getStyleClass().add("button");
        return open;
    }

    //
    static Button Save() {
        save.setTranslateX(25);
        save.setTranslateY(-10);
        save.getStyleClass().add("button");
        return save;
    }

    //
    static Button Quit() {
        quit.setTranslateX(25);
        quit.setTranslateY(-10);
        quit.getStyleClass().add("button");
        return quit;
    }
}
