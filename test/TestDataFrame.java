import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataFrame {

    private static DataFrame dataFrame1, dataFrame2, dataFrame3, dataFrameVide;
    private static ArrayList<String> columnNames1, columnNames2, columnNames3, columnNamesVide;
    private static ArrayList<Integer> index1, index2, index3, indexVide;
    private static ArrayList<ArrayList<Object>> data1 , data2 , data3, dataVide;

    @BeforeAll
    public static void init() {
        columnNames1 = new ArrayList<>(Arrays.asList("Column1", "Column2", "Column3"));
        columnNames2 = new ArrayList<>(Arrays.asList("Column1", "Column2", "Column1"));
        columnNames3 = new ArrayList<>(Arrays.asList("Strings", "Entiers", "Booleens"));
        columnNamesVide = new ArrayList<>(List.of());

        index1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        index2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        index3 = new ArrayList<>(Arrays.asList(1, 2, 1, 1, 5));
        indexVide = new ArrayList<>(List.of());

        data1 = new ArrayList<>();
        data1.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        data1.add(new ArrayList<>(Arrays.asList("4", "5", "6")));
        data1.add(new ArrayList<>(Arrays.asList("7", "8", "9")));

        data2 = new ArrayList<>();
        data2.add(new ArrayList<>(Arrays.asList("1", "2", "3", "4")));
        data2.add(new ArrayList<>(Arrays.asList("4", "5", "6","7")));

        data3 = new ArrayList<>();
        data3.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        data3.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        data3.add(new ArrayList<>(Arrays.asList(true, false, true)));

        dataVide = new ArrayList<>(List.of());

        dataFrame1 = new DataFrame(data1, index1, columnNames1);
        dataFrame3 = new DataFrame(data3, index1, columnNames3);
        dataFrameVide = new DataFrame(dataVide, indexVide, columnNamesVide);
    }
    @Test
    public void testDataFrameInstanciation(){
        assertEquals(DataFrame.class, dataFrame1.getClass(), "DataFrame non instancie correctement");
        assertEquals(DataFrame.class, dataFrameVide.getClass(), "DataFrame vide non instancie correctement");
    }

    @Test
    public void testGetters(){
        assertEquals(columnNames1, dataFrame1.getColumnNames(),"getColumnNames() non fonctionnel");
        assertEquals(new ArrayList<String>(), dataFrameVide.getColumnNames(),"getColumnNames() non fonctionnel");

        assertEquals(index1, dataFrame1.getIndex(),"getIndex() non fonctionnel");
        assertEquals(new ArrayList<>(), dataFrameVide.getIndex(),"getIndex() non fonctionnel");

        assertEquals(data1, dataFrame1.getData(),"getData() non fonctionnel");
        assertEquals(new ArrayList<>(), dataFrameVide.getData(),"getData() non fonctionnel");
    }

    @Test
    public void testTypesElementsColonne(){
        for (int i = 0; i < dataFrame1.getColumnNames().size(); i++) {
            for (int j = 0; j < dataFrame1.getData().get(i).size(); j++) {
                assertEquals(dataFrame1.getData().get(i).get(0).getClass(), dataFrame1.getData().get(i).get(j).getClass(),
                        "les données de la colonne " + i + " ne sont pas du même type");
            }
        }
    }
    @Test
    public void testDoublonsIndex(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index3, columnNames1);
        }, "IllegalArgumentException non levee");
    }

    @Test
    public void testDoublonsNomsColonnes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index1, columnNames2);
        }, "IllegalArgumentException non levee");
    }

    @Test
    public void testExceptionTailleColonnes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data2, index1, columnNames1);
        }, "IllegalArgumentException non levee");
    }

    @Test
    public void testExceptionTailleIndex(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index2, columnNames1);
        }, "IllegalArgumentException non levee");
    }


}
