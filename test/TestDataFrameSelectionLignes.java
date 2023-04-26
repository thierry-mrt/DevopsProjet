import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataFrameSelectionLignes {

    private DataFrame original;

    public TestDataFrameSelectionLignes(){
        original = new DataFrame("resources/csvFiles/fileTestSousEnsembleLignes.csv",";");
    }

    @Test
    public void testSelectionLignesNormalOrder(){
        Integer[] indexToSelect = {3,4,5};
        int numberOfRowToSelect = indexToSelect.length;
        DataFrame newDf = original.selectLinesByIndex(indexToSelect);

        //Compare nombre de lignes sélectionnée
        assertEquals(numberOfRowToSelect,newDf.getData().size(),"Nombre de lignes de données dans le nouveau dataframe");

        //Compare size property of dataframe
        assertEquals(newDf.getIndex().size(),newDf.getData().size(),"Compare nombre d'index et nombre de lignes");
        assertEquals(newDf.getColumnNames().size(),newDf.getTypes().size(),"Compare nombre de noms de colonnes et nombre de types");

        //Compare valeurs des colonnes/types
        assertEquals(original.getColumnNames(),newDf.getColumnNames(),"Compare noms des colonnes");
        assertEquals(original.getTypes(),newDf.getTypes(),"Compare types");

        //Compare ordre des index
        int currentInd;
        int previousInd = -1;
        for (int i = 0; i < newDf.getIndex().size(); i++) {
            Integer index = newDf.getIndex().get(i);
            currentInd = original.getIndex().indexOf(index);
            assertTrue(currentInd > previousInd,"Compare si même ordre dans les index");
            previousInd = currentInd;
        }

        //Compare si même index

        //Compare valeurs des lignes
//        for (int i = 0; i < newDf.getData().size(); i++) {
//
//        }
    }






}
