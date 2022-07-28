package Application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GradeManagementApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Label heading = new Label("Grade Management System");
        root.setTop(heading);
//        BorderPane.setAlignment(heading, Pos.CENTER);

        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.setTitle("Grade Management System");
        primaryStage.show();
    }
}
