package Application;

import DataManagement.DataManager;
import Logic.GradeManagementSystem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StudyFX extends Application{

    public static void main(String[] args) {
        GradeManagementSystem gms;
        launch(args);
    }

    // TODO load gms then add grids for subgrids

    @Override
    public void start(Stage primaryStage) {

        GridPane startUpPane = new GridPane();
        Button supButton = new Button("Load modules");
        startUpPane.addRow(0, supButton);
        int i = 0;
        supButton.setOnAction(e -> {
                try {
                    DataManager.loadStudyPrograms();
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Loading failed.");
                    alert.show();
                }
        });
        Scene startUp = new Scene(startUpPane, 600, 400);




        Label SPLabel1 = new Label("Study Program");
        TextField SPText1  = new TextField();
        Button SPButton1 = new Button("add study program");

        GridPane SPGridPane = new GridPane();
        SPGridPane.setHgap(10.);
        SPGridPane.setVgap(5.);
        SPGridPane.addRow(0, SPLabel1, SPText1, SPButton1);

        GridPane root = new GridPane();
        root.setHgap(20.);
        root.setVgap(20.);
        root.addRow(0, SPGridPane);

        Scene sceneMain = new Scene(root, 600, 400);


        primaryStage.setScene(startUp);
        primaryStage.setTitle("Grade Management System");
        primaryStage.show();
    }

}