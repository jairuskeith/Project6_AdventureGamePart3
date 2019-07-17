package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getSource() == KeyCode.UP || keyEvent.getSource() == KeyCode.DOWN ||
                keyEvent.getSource() == KeyCode.RIGHT || keyEvent.getSource() == KeyCode.LEFT) {
            System.out.println("poop");

        }
    }
}
