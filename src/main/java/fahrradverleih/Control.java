package fahrradverleih;

import javafx.beans.binding.ObjectExpression;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Control implements Initializable {
    private Model model;
    private View view;
    private Stage stage;

    @FXML
    private TextField tage;
    @FXML
    private TableView<Fahrrad> vorrat;
    @FXML
    private TableColumn vorratFahrrad;
    @FXML
    private TableColumn vorratPreis;
    @FXML
    private TableView<Fahrrad> warenkorb;
    @FXML
    private TableColumn warenkorbFahrrad;
    @FXML
    private TableColumn warenkorbPreis;
    @FXML
    private Button hinzufuegen;
    @FXML
    private Button loeschen;
    @FXML
    private Button berechnen;
    @FXML
    private TextField preis;
    @FXML
    private Button bestellen;


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
        this.berechnen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<Fahrrad> items = warenkorb.getItems();
                try{
                    int tageWert = Integer.parseInt(tage.getText());
                    if(items != null){
                        double erg = 0;
                        for (Fahrrad item:items) {
                            erg += item.getPreis();
                        }
                        erg *= tageWert;
                        preis.setText("" + erg);
                    }
                }catch(NumberFormatException e){
                    //Label einfügen und das dann mit Text füllen
                }


            }
        });
        this.bestellen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        this.hinzufuegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               Fahrrad item =  vorrat.getSelectionModel().getSelectedItem();
               if(item != null){
                   warenkorb.getItems().add(item);
                   vorrat.getItems().remove(item);
               }
            }
        });
        this.loeschen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Fahrrad item =  warenkorb.getSelectionModel().getSelectedItem();
                if(item != null){
                    vorrat.getItems().add(item);
                    warenkorb.getItems().remove(item);
                }
            }
        });
        initTableColumn();

    }

    private void initTableColumn(){
        vorratFahrrad.setCellValueFactory(new PropertyValueFactory<Fahrrad, String>("name"));
        vorratPreis.setCellValueFactory(new PropertyValueFactory<Fahrrad, String>("preis"));
        vorrat.setItems(fahrrads);

        warenkorbFahrrad.setCellValueFactory(new PropertyValueFactory<Fahrrad,String>("name"));
        warenkorbPreis.setCellValueFactory(new PropertyValueFactory<Fahrrad, String>("preis"));
    }


    private ObservableList<Fahrrad> fahrrads = FXCollections.observableArrayList(
            new Fahrrad("Mountainbike", 10.5),
            new Fahrrad("Hollandbike", 8.7),
            new Fahrrad("E-Bike", 15.3),
            new Fahrrad("BMX", 13.2),
            new Fahrrad("Roller", 5.6)
            );
}
