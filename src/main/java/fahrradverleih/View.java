package fahrradverleih;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class View {

    private Model model;
    private Control control;
    private Stage primaryStage;

    public View(Model basisModel, Control basisControl, Stage primaryStage) throws IOException {
        this.control = basisControl;
        this.model = basisModel;
        this.primaryStage = primaryStage;
        showScene(this.primaryStage);
    }

    public void showScene(Stage primaryStage)throws IOException { //allgemeiner schreib so das Resource,Title übergeben wird
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fahrradVerleih.fxml"));
        fxmlLoader.setController(this.control);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Fahrradverleih");
        Scene scene = new Scene(root);
        URL css =  getClass().getResource("/style.css");
        scene.getStylesheets().add(css.toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMessageDialog(Alert.AlertType type, String title, String header, String content){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}


