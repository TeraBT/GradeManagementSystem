package Application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StudyFX extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    // TODO javafx text(

    @Override
    public void start(Stage primaryStage) {

        Button button1 = new Button("Button #1");
        button1.setOnAction(actionEvent -> System.out.println("hello world"));


        StackPane root = new StackPane(button1);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application #1");
        primaryStage.show();
    }

}