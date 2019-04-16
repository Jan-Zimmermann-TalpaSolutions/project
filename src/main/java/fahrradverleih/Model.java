package fahrradverleih;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    private ObservableList<Fahrrad> fahrrads = FXCollections.observableArrayList(
            new Fahrrad("Mountainbike", 10.5),
            new Fahrrad("Hollandbike", 8.7),
            new Fahrrad("E-Bike", 15.3),
            new Fahrrad("BMX", 13.2),
            new Fahrrad("Roller", 5.6)
    );

    public ObservableList<Fahrrad> getData(){
        return this.fahrrads;
    }

}
