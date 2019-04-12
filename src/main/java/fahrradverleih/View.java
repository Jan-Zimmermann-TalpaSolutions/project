package fahrradverleih;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View {

    private Model model;
    private Control control;
    private Stage primaryStage;

    public View(Model basisModel, Control basisControl, Stage primaryStage) throws IOException {
        this.control = basisControl;
        this.model = basisModel;
        this.primaryStage = primaryStage;
        showScene(primaryStage);
    }

    public void showScene(Stage primaryStage)throws IOException { //allgemeiner schreib so das Resource,Title übergeben wird
        //Parent root = FXMLLoader.load(getClass().getResource("/fahrradVerleih.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fahrradVerleih.fxml"));
        fxmlLoader.setController(this.control);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Fahrradverleih");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}


