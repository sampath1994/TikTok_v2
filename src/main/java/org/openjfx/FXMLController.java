package org.openjfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLController {

    @FXML
    private Button set;
    @FXML
    private Button pause;
    @FXML
    private Button reset;
    @FXML
    private Button minimize;
    @FXML
    private Button close;
    @FXML
    private Label time;
    @FXML
    private Label distraction;

    Thread thread;
    boolean isStopped = false;
    int distractionCount = 0;

    public void initialize() {
        // TODO
    }

    @FXML
    private void set(ActionEvent event) {
        distractionCount = 0;
        distraction.setText("0");
        isStopped = false;
        time.getText();
        //time.setDisable(true);
        thread = new Thread() {
            public void run() {
                int i = 0;
                while (!isStopped) {
                    try {
                        i++;
                        int minute = i / 60;
                        int seconds = i % 60;
                        setTime((minute == 0 ? "0" : String.valueOf(minute)) + ":" + (seconds == 0 ? "0" : String.valueOf(seconds)));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
        set.setDisable(true);
    }

    @FXML
    private void pause(ActionEvent event) {
        isStopped = true;
        set.setDisable(false);
    }

    @FXML
    private void reset(ActionEvent event) {
        distractionCount++;
        distraction.setText(String.valueOf(distractionCount));
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    private void setTime(String t) {
        synchronized (this) {
            Platform.runLater(() -> time.setText(t));
        }
    }
}
