import fahrradverleih.Fahrrad;
import fahrradverleih.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFahrrad {

    @Test
    void justAnExample(){
        Model model = new Model();
        ObservableList<Fahrrad> fahrrads = FXCollections.observableArrayList(
                new Fahrrad("Mountainbike", 10.5),
                new Fahrrad("Hollandbike", 8.7),
                new Fahrrad("E-Bike", 15.3),
                new Fahrrad("BMX", 13.2),
                new Fahrrad("Roller", 5.6)
        );
        Assertions.assertEquals(model.getData().get(1).getPreis(), fahrrads.get(1).getPreis());
    }

}
