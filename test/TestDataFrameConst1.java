import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataFrameConst1 {

    private static DataFrame dataFrame1, dataFrame2, dataFrameVide;
    private static ArrayList<String> columnNames1, columnNames2, columnNamesVide;
    private static ArrayList<Integer> index1, index2, index3, indexVide;
    private static ArrayList<ArrayList<String>> data1, data2, dataVide;

    private static ArrayList<String> types1, types2, typesFalse, typesVide;

    public TestDataFrameConst1() {
        columnNames1 = new ArrayList<>(Arrays.asList("Column1", "Column2", "Column3"));
        columnNames2 = new ArrayList<>(Arrays.asList("Column1", "Column2", "Column1"));
        columnNamesVide = new ArrayList<>(List.of());

        index1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        index2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        index3 = new ArrayList<>(Arrays.asList(1, 2, 1));
        indexVide = new ArrayList<>(List.of());

        data1 = new ArrayList<>();
        data1.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        data1.add(new ArrayList<>(Arrays.asList("4", "5", "6")));
        data1.add(new ArrayList<>(Arrays.asList("7", "8", "9")));

        data2 = new ArrayList<>();
        data2.add(new ArrayList<>(Arrays.asList("1", "2", "3", "4")));
        data2.add(new ArrayList<>(Arrays.asList("4", "5", "6", "7")));

        dataVide = new ArrayList<>(List.of());

        types1 = new ArrayList<>(Arrays.asList("INTEGER", "INTEGER", "INTEGER"));
        types2 = new ArrayList<>(Arrays.asList("INTEGER", "STRING", "INTEGER", "STRING"));
        typesFalse = new ArrayList<>(Arrays.asList("STRING", "POISSON", "BOOLEAN"));
        typesVide = new ArrayList<>(List.of());

        dataFrame1 = new DataFrame(data1, index1, columnNames1, types1);
        dataFrameVide = new DataFrame(dataVide, indexVide, columnNamesVide, typesVide);
    }
    @Test
    public void testDataFrameInstanciationByArrays(){
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

        assertEquals(types1, dataFrame1.getTypes(),"getTypes() non fonctionnel");
        assertEquals(new ArrayList<>(), dataFrameVide.getTypes(),"getTypes() non fonctionnel");
    }

    @Test
    public void testDoublonsIndex(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index3, columnNames1, types1);
        }, "IllegalArgumentException non levee");
    }

    @Test
    public void testDoublonsNomsColonnes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index1, columnNames2, types1);
        }, "IllegalArgumentException non levee");
    }

    @Test
    public void testExceptionTailleColonnes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data2, index1, columnNames1, types1);
        }, "IllegalArgumentException non levee");
    }

    @Test
    public void testExceptionTailleIndex(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index2, columnNames1, types1);
        }, "IllegalArgumentException non levee");
    }

    @Test
    public void testExceptionTailleTypes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index1, columnNames1, types2);
        }, "IllegalArgumentException non levee");
    }

    @Test
    public void testExceptionTypes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index1, columnNames1, typesFalse);
        }, "IllegalArgumentException non levee");
    }

    @Test
    public void testExceptionTypesVide(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index1, columnNames1, typesVide);
        }, "IllegalArgumentException non levee");
    }

}
