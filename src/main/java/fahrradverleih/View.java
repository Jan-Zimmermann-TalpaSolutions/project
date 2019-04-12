package fahrradverleih;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class View {

    @FXML
    private TextField tage;

    private Model model;
    private Control control;
    private Stage primaryStage;

    public View(Model basisModel, Control basisControl, Stage primaryStage) throws IOException {
        this.control = basisControl;
        this.model = basisModel;
        this.primaryStage = primaryStage;
        showScene(primaryStage);
    }

    public void initComponents() {

    }

    public void showScene(Stage primaryStage)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fahrradVerleih.fxml"));
        tage.setText("LOL");
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}


