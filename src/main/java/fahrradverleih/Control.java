package fahrradverleih;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Control implements Initializable {
    private Model model;
    private View view;
    private Stage stage;

    @FXML
    private TextField tage;


    public Control(Stage stage) {
        this.model = new Model();
        this.stage = stage;
        try{
            this.view = new View(this.model, this, this.stage);
        } catch(IOException e){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tage.setText("geht das auch ? ");
    }
    //ActionListener eigentlich auch im Control
    //mein Exceptions auch
    //Vermittlungsschicht zwischen Daten und View
}
