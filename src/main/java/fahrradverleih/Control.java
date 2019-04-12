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
    private String currentWarenkorb;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initEventListener();
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

    private void changeTable(TableView<Fahrrad> from, TableView<Fahrrad> to){
        Fahrrad item =  from.getSelectionModel().getSelectedItem();
        if(item != null){
            to.getItems().add(item);
            from.getItems().remove(item);
        }
    }
    private void showMessageDialog(Alert.AlertType type, String title, String header, String content){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    private void initEventListener(){
        this.berechnen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<Fahrrad> items = warenkorb.getItems();
                currentWarenkorb = "";
                try{
                    int tageWert = Integer.parseInt(tage.getText());
                    currentWarenkorb += "Anzahl Tage:" + tageWert + "\n";
                    if(items != null){
                        double erg = 0;
                        for (Fahrrad item:items) {
                            erg += item.getPreis();
                            currentWarenkorb += item.getName() + " " + item.getPreis() + "\n";
                        }
                        erg *= tageWert;
                        preis.setText("" + erg); //currentWarnkorb zwischen speichern
                    }
                }catch(NumberFormatException e){
                    showMessageDialog(Alert.AlertType.ERROR, "Fehler", "Fehler", "Fehler");
                    //präsizieren
                    //Label einfügen und das dann mit Text füllen
                }


            }
        });
        this.bestellen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showMessageDialog(Alert.AlertType.INFORMATION, "Bestellung", "Informationen zu ihrer Bestellung", currentWarenkorb);
                //messageBox sie haben erfolgreich bestellt/ Liste der Bestellung
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
