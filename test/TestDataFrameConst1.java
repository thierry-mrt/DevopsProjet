import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test de la classe DataFrame
 */
public class TestDataFrameConst1 {

    private DataFrame dataFrame1, dataFrame2, dataFrameVide;
    private ArrayList<String> columnNames1, columnNames2, columnNamesVide;
    private ArrayList<Integer> index1, index2, index3, indexVide;
    private ArrayList<ArrayList<String>> data1, data2, dataVide, dataUnevenLines;

    private ArrayList<String> types1, types2, typesFalse, typesVide;

    /**
     * Constructeur de la classe de test
     */
    public TestDataFrameConst1() {
        columnNames1 = new ArrayList<>(Arrays.asList("Column1", "Column2", "Column3")); // tous les noms sont différents
        columnNames2 = new ArrayList<>(Arrays.asList("Column1", "Column2", "Column1")); // deux colonnes ont le même nom
        columnNamesVide = new ArrayList<>(List.of());

        index1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        index2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        index3 = new ArrayList<>(Arrays.asList(1, 2, 1)); // deux index ont la même valeur
        indexVide = new ArrayList<>(List.of());

        data1 = new ArrayList<>(); // tableau de lignes de tailles égales
        data1.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        data1.add(new ArrayList<>(Arrays.asList("4", "5", "6")));
        data1.add(new ArrayList<>(Arrays.asList("7", "8", "9")));

        data2 = new ArrayList<>(); // tableau de lignes de tailles égales
        data2.add(new ArrayList<>(Arrays.asList("1", "2", "3", "4")));
        data2.add(new ArrayList<>(Arrays.asList("4", "5", "6", "7")));

        dataUnevenLines = new ArrayList<>(); // tableau de lignes de tailles inégales
        dataUnevenLines.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        dataUnevenLines.add(new ArrayList<>(Arrays.asList("4")));
        dataUnevenLines.add(new ArrayList<>(Arrays.asList("4", "5")));

        dataVide = new ArrayList<>(List.of());

        types1 = new ArrayList<>(Arrays.asList("INTEGER", "INTEGER", "INTEGER")); // tous les types sont autorisés
        types2 = new ArrayList<>(Arrays.asList("INTEGER", "STRING", "INTEGER", "STRING")); // tous les types sont autorisés
        typesFalse = new ArrayList<>(Arrays.asList("STRING", "POISSON", "BOOLEAN")); // POISSON n'est pas un type autorisé
        typesVide = new ArrayList<>(List.of());

        dataFrame1 = new DataFrame(data1, index1, columnNames1, types1); // tableau de lignes de tailles égales
        dataFrame2 = new DataFrame(dataUnevenLines, index1, columnNames1, types1); // tableau de lignes de tailles inégales
        dataFrameVide = new DataFrame(dataVide, indexVide, columnNamesVide, typesVide); // tableau vide
    }

    /**
     * Teste si les instances de DataFrame sont correctement créées
     */
    @Test
    @Order(1)
    public void testDataFrameInstanciationByArrays(){
        assertEquals(DataFrame.class, dataFrame1.getClass(), "DataFrame non instancie correctement");
        assertEquals(DataFrame.class, dataFrame2.getClass(), "DataFrame avec lignes à taille inégales non instancie correctement");
        assertEquals(DataFrame.class, dataFrameVide.getClass(), "DataFrame vide non instancie correctement");
    }

    /**
     * Teste si les getters fonctionnent correctement et que les données sont correctement instanciées pour les tableaux de lignes de tailles égales et inégales
     */
    @Test
    @Order(2)
    public void testGetters(){
        assertEquals(columnNames1, dataFrame1.getColumnNames(),"getColumnNames() non fonctionnel");
        assertEquals(new ArrayList<String>(), dataFrameVide.getColumnNames(),"getColumnNames() non fonctionnel");

        assertEquals(index1, dataFrame1.getIndex(),"getIndex() non fonctionnel");
        assertEquals(new ArrayList<>(), dataFrameVide.getIndex(),"getIndex() non fonctionnel");

        assertEquals(data1, dataFrame1.getData(),"getData() non fonctionnel");
        assertEquals(new ArrayList<>(), dataFrameVide.getData(),"getData() pour lignes à taille égale non fonctionnel");

        assertEquals(types1, dataFrame1.getTypes(),"getTypes() non fonctionnel");
        assertEquals(new ArrayList<>(), dataFrameVide.getTypes(),"getTypes() non fonctionnel");

        ArrayList<ArrayList<String>> expectedUnevenLines = new ArrayList<>();
        expectedUnevenLines.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        expectedUnevenLines.add(new ArrayList<>(Arrays.asList("4","","")));
        expectedUnevenLines.add(new ArrayList<>(Arrays.asList("4", "5","")));
        assertEquals(expectedUnevenLines, dataFrame2.getData(),"getData() pour ligne à taille inégale non fonctionnel");
    }

    /**
     * Teste si une exception est levée lorsque le tableau des index contient des doublons
     */
    @Test
    public void testDoublonsIndex(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index3, columnNames1, types1);
        }, "Des index de lignes sont en double & IllegalArgumentException non levee");
    }

    /**
     * Teste si une exception est levée lorsque le tableau des noms de colonnes contient des doublons
     */
    @Test
    public void testDoublonsNomsColonnes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index1, columnNames2, types1);
        }, "Des noms de colonnes sont en double & IllegalArgumentException non levee");
    }

    /**
     * Teste si une exception est levée lorsque le tableau des indices ne contient pas le même nombre d'éléments que de lignes
     */
    @Test
    public void testExceptionTailleIndex(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index2, columnNames1, types1);
        }, "Nombre d'indices de lignes différent du nombre actuel de lignes & IllegalArgumentException non levee");
    }

    /**
     * Teste si une exception est levée lorsque le tableau des types de données ne contient pas le même nombre d'éléments que le tableau des noms de colonnes
     */
    @Test
    public void testExceptionTailleTypes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index1, columnNames1, types2);
        }, "Nombre de types de données fournies différent du nombre de colonnes & IllegalArgumentException non levee");
    }

    /**
     * Teste si une exception est levée lorsque le tableau des types de données contient un type non autorisé
     */
    @Test
    public void testExceptionTypes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index1, columnNames1, typesFalse);
        }, "Un ou plusieurs types de données erronés ont été fournis & IllegalArgumentException non levee");
    }

    /**
     * Teste si une exception est levée lorsque le tableau des types de données est vide
     */
    @Test
    public void testExceptionTypesVide(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data1, index1, columnNames1, typesVide);
        }, "Aucun type n'a été fourni & IllegalArgumentException non levee");
    }

    /**
     * Teste si une exception est levée lorsqu'il n'y a pas suffisamment de nom de colonnes
     */
    @Test
    public void testExceptionTailleLignes(){
        assertThrows(IllegalArgumentException.class, () -> {
            dataFrame2 = new DataFrame(data2, index1, columnNames1, types1);
        }, "Nombres de colonnes insuffisant & IllegalArgumentException non levee");
    }

}
