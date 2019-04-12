package fahrradverleih;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Control implements Initializable {
    private Model model;
    private View view;
    private Stage stage;
    private String currentWarenkorb = "Ihr Warenkorb ist leer";

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
            e.printStackTrace();
        }
    }

    private void changeTable(TableView<Fahrrad> from, TableView<Fahrrad> to){
        Fahrrad item =  from.getSelectionModel().getSelectedItem();
        if(item != null){
            to.getItems().add(item);
            from.getItems().remove(item);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initEventListener();
        initTableColumn();
    }

    private void initTableColumn(){
        vorratFahrrad.setCellValueFactory(new PropertyValueFactory<Fahrrad, String>("name"));
        vorratPreis.setCellValueFactory(new PropertyValueFactory<Fahrrad, String>("preis"));
        vorrat.setItems(model.getData());
        warenkorbFahrrad.setCellValueFactory(new PropertyValueFactory<Fahrrad,String>("name"));
        warenkorbPreis.setCellValueFactory(new PropertyValueFactory<Fahrrad, String>("preis"));
    }

    private void initEventListener(){
        this.berechnen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    ObservableList<Fahrrad> items = warenkorb.getItems();
                    int tageWert = Integer.parseInt(tage.getText());
                    currentWarenkorb = "";
                    currentWarenkorb += "Anzahl Tage:" + tageWert + "\n";
                    if(!items.isEmpty()){
                        double erg = 0;
                        for (Fahrrad item:items) {
                            erg += item.getPreis();
                            currentWarenkorb += item.getName() + " " + item.getPreis() + "\n";
                        }
                        erg *= tageWert;
                        erg = Math.round(erg*100.0)/100.0;
                        preis.setText("" + erg);
                        currentWarenkorb += "Gesamtsumme:" + erg;
                    }else{
                        view.showMessageDialog(Alert.AlertType.ERROR, "Fehler", "invalidArgument", "Bitte fügen Sie ein Produkt ihrem Warenkorb hinzu");
                    }
                }catch(NumberFormatException e){
                    view.showMessageDialog(Alert.AlertType.ERROR, "Fehler", "invalidArgument", "Bitte geben Sie bei \'Anzahl Tage\' eine Zahl ein ");
                }
            }
        });
        this.bestellen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.showMessageDialog(Alert.AlertType.INFORMATION, "Bestellung", "Informationen zu ihrer Bestellung", currentWarenkorb);
            }
        });
        this.hinzufuegen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                changeTable(vorrat, warenkorb);
            }
        });
        this.loeschen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                changeTable(warenkorb, vorrat);
            }
        });
    }
}
