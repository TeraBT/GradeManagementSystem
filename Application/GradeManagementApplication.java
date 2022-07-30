package Application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GradeManagementApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        HBox options = new HBox();
        Button addModuleButton = new Button("add module");
        Button showAveragesButton = new Button ("show averages");
        options.getChildren().addAll(addModuleButton, showAveragesButton);

        root.setTop(options);
        root.setPadding(new Insets(5.));
        HBox.setMargin(options, new Insets(10., 2., 2.,2.));

        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.setTitle("Grade Management System");
        primaryStage.show();
    }
}
