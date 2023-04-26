import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataFrameSelectionLignes {

    private DataFrame original;
    Integer[] indexToSelectNormalOrder = {3,4,5};
    Integer[] indexToSelectWrongOrder = {5,0,2};
    Integer[] indexToSelectNonExistingIndex = {5,25,0};
    Integer[] indexToSelectDoublonIndex = {0,1,2,2};

    Integer[] indexToSelectVide = {};

    public TestDataFrameSelectionLignes(){
        original = new DataFrame("resources/csvFiles/fileTestSousEnsembleLignes.csv",";");
    }

    @Test
    public void testSelectionLignesNormalOrder(){

        int numberOfRowToSelect = indexToSelectNormalOrder.length;
        DataFrame newDf = original.selectLinesByIndex(indexToSelectNormalOrder);

        //Compare nombre de lignes sélectionnée
        assertEquals(numberOfRowToSelect,newDf.getData().size(),"Nombre de lignes de données dans le nouveau dataframe");

        //Compare size property of dataframe
        assertEquals(newDf.getIndex().size(),newDf.getData().size(),"Compare nombre d'index et nombre de lignes");
        assertEquals(newDf.getColumnNames().size(),newDf.getTypes().size(),"Compare nombre de noms de colonnes et nombre de types");

        //Compare valeurs des colonnes/types
        assertEquals(original.getColumnNames(),newDf.getColumnNames(),"Compare noms des colonnes");
        assertEquals(original.getTypes(),newDf.getTypes(),"Compare types");

        //Compare si même index
        //Compare ordre des index
        //Compare valeurs des lignes
        int currentInd;
        int previousInd = -1;
        for (int i = 0; i < newDf.getIndex().size(); i++) {
            Integer indexTested = newDf.getIndex().get(i); //Valeur de l'index que l'on teste
            currentInd = original.getIndex().indexOf(indexTested); // Indice dans le tableau d'index de original pour l'index testé
            int indexTestedi = newDf.getIndex().indexOf(indexTested); //Indice dans le tableau d'index du nouveau pour l'index qu'on teste

            assertTrue(original.getIndex().contains(indexTested),"Test si index présent dans original ("+i+")");

            assertTrue(currentInd > previousInd,"Compare si même ordre dans les index ("+i+")");

            assertEquals(original.getData().get(currentInd),newDf.getData().get(indexTestedi),"Compare si même valeurs de lignes pour même index ("+i+")");

            previousInd = currentInd;
        }
    }

    @Test
    public void testSelectionLignesWrongOrder(){

        int numberOfRowToSelect = indexToSelectWrongOrder.length;
        DataFrame newDf = original.selectLinesByIndex(indexToSelectWrongOrder);

        //Compare nombre de lignes sélectionnée
        assertEquals(numberOfRowToSelect,newDf.getData().size(),"Nombre de lignes de données dans le nouveau dataframe");

        //Compare size property of dataframe
        assertEquals(newDf.getIndex().size(),newDf.getData().size(),"Compare nombre d'index et nombre de lignes");
        assertEquals(newDf.getColumnNames().size(),newDf.getTypes().size(),"Compare nombre de noms de colonnes et nombre de types");

        //Compare valeurs des colonnes/types
        assertEquals(original.getColumnNames(),newDf.getColumnNames(),"Compare noms des colonnes");
        assertEquals(original.getTypes(),newDf.getTypes(),"Compare types");

        //Compare si même index
        //Compare ordre des index
        //Compare valeurs des lignes
        int currentInd;
        int previousInd = -1;
        for (int i = 0; i < newDf.getIndex().size(); i++) {
            Integer indexTested = newDf.getIndex().get(i); //Valeur de l'index que l'on teste
            currentInd = original.getIndex().indexOf(indexTested); // Indice dans le tableau d'index de original pour l'index testé
            int indexTestedi = newDf.getIndex().indexOf(indexTested); //Indice dans le tableau d'index du nouveau pour l'index qu'on teste

            assertTrue(original.getIndex().contains(indexTested),"Test si index présent dans original ("+i+")");

            assertTrue(currentInd > previousInd,"Compare si même ordre dans les index ("+i+")");

            assertEquals(original.getData().get(currentInd),newDf.getData().get(indexTestedi),"Compare si même valeurs de lignes pour même index ("+i+")");

            previousInd = currentInd;
        }
    }

    @Test
    public void testSelectionLignesNonExistingIndex(){
        final DataFrame[] d = {null};

        assertThrows(IllegalArgumentException.class, () -> {
            d[0] = original.selectLinesByIndex(indexToSelectNonExistingIndex);
        }, "Index demandé non existant dans l'original, test du throw de IllegalArgumentException");

        assertEquals(null,d[0],"Dataframe ne doit pas avoir été créé");
    }

    @Test
    public void testSelectionLignesDoublonIndex(){
        final DataFrame[] d = {null};

        assertThrows(IllegalArgumentException.class, () -> {
            d[0] = original.selectLinesByIndex(indexToSelectDoublonIndex);
        }, "Doublon dans les index demandés, test du throw de IllegalArgumentException");

        assertEquals(null,d[0],"Dataframe ne doit pas avoir été créé");
    }

    @Test
    public void testSelectionLignesIndexVide(){

        int numberOfRowToSelect = 0;
        DataFrame newDf = original.selectLinesByIndex(indexToSelectVide);

        //Compare nombre de lignes sélectionnée
        assertEquals(numberOfRowToSelect,newDf.getData().size(),"Nombre de lignes de données dans le nouveau dataframe");

        //Compare size property of dataframe
        assertEquals(newDf.getIndex().size(),newDf.getData().size(),"Compare nombre d'index et nombre de lignes");
        assertEquals(newDf.getColumnNames().size(),newDf.getTypes().size(),"Compare nombre de noms de colonnes et nombre de types");

        //Compare valeurs des colonnes/types
        assertEquals(original.getColumnNames(),newDf.getColumnNames(),"Compare noms des colonnes");
        assertEquals(original.getTypes(),newDf.getTypes(),"Compare types");
    }

}
