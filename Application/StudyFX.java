package Application;

import DataManagement.DataManager;
import Logic.GradeManagementSystem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StudyFX extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    // TODO load gms then add grids for subgrids

    @Override
    public void start(Stage primaryStage) {



        Label SPLabel1 = new Label("Study Program");
        TextField SPText1  = new TextField();
        Button SPButton1 = new Button("add study program");




        GridPane SPGridPane = new GridPane();
        SPGridPane.setHgap(10.);
        SPGridPane.setVgap(5.);
        SPGridPane.addRow(0, SPLabel1, SPText1, SPButton1);

//        GridPane SP

        GridPane root = new GridPane();
        root.setHgap(20.);
        root.setVgap(20.);
        root.addRow(0, SPGridPane);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Grade Management System");
        primaryStage.show();
    }

}