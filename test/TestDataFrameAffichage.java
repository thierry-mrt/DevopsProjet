import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDataFrameAffichage {

    @Test
    public void testAffichage() {
        ArrayList<String> columnNames1 = new ArrayList<>(Arrays.asList("Column1", "Column2", "Column3"));
        ArrayList<Integer> index1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<ArrayList<String>> data1 = new ArrayList<>();
        data1.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        data1.add(new ArrayList<>(Arrays.asList("4", "5", "6")));
        data1.add(new ArrayList<>(Arrays.asList("7", "8", "9")));
        ArrayList<String> types1 = new ArrayList<>(Arrays.asList("INTEGER", "INTEGER", "INTEGER"));

        DataFrame dataFrame1 = new DataFrame(data1, index1, columnNames1, types1);
        dataFrame1.afficher();
    }

    @Test
    public void testAffichagePremieresLignes(){

    }

    @Test
    public void testAffichageDernieresLignes(){

    }
}
