import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe de test de la classe DataFrame
 */
public class TestDataFrameAffichage {

    private DataFrame dataFrame1, dataFrame2;
    private String expectedOutput, actualOutput;
    private ByteArrayOutputStream outContent;

    /**
     * Constructeur de la classe de test
     */
    public TestDataFrameAffichage(){
        outContent = new ByteArrayOutputStream(); // pour récupérer le texte affiché dans la console

        // dataframe a lignes égales
        ArrayList<String> columnNames1 = new ArrayList<>(Arrays.asList("Column1", "Column2", "Column3"));
        ArrayList<Integer> index1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<ArrayList<String>> data1 = new ArrayList<>();
        data1.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        data1.add(new ArrayList<>(Arrays.asList("4", "5", "6")));
        data1.add(new ArrayList<>(Arrays.asList("7", "8", "9")));
        ArrayList<String> types1 = new ArrayList<>(Arrays.asList("INTEGER", "INTEGER", "INTEGER"));
        dataFrame1 = new DataFrame(data1, index1, columnNames1, types1);

        // dataframe a lignes inégales
        ArrayList<String> columnNames2 = new ArrayList<>(Arrays.asList("Column1", "Column2", "Column3"));
        ArrayList<Integer> index2 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<ArrayList<String>> data2 = new ArrayList<>();
        data2.add(new ArrayList<>(Arrays.asList("1", "2", "3")));
        data2.add(new ArrayList<>(Arrays.asList("4")));
        data2.add(new ArrayList<>(Arrays.asList("7", "8")));
        ArrayList<String> types2 = new ArrayList<>(Arrays.asList("INTEGER", "INTEGER", "INTEGER"));
        dataFrame2 = new DataFrame(data2, index2, columnNames2, types2);
    }

    /**
     * Test de l'affichage d'un DataFrame à lignes égales
     */
    @Test
    public void testAffichageDataframeLignesEgales() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        dataFrame1.afficher();
        expectedOutput = "	Column1	Column2	Column3\n1	1	2	3\n2	4	5	6\n3	7	8	9\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage du tableau à lignes de taille égale incorrect");
    }

    /**
     * Test de l'affichage d'un DataFrame à lignes inégales
     */
    @Test
    public void testAffichageDataframeLignesInegales() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        dataFrame2.afficher();
        expectedOutput =  "	Column1	Column2	Column3\n1	1	2	3\n2	4\t\t\n3	7	8\t\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage du tableau à lignes de taille inégale incorrect");
    }

    /**
     * Test de l'affichage des premières lignes d'un DataFrame à lignes égales
     */
    @Test
    public void testAffichagePremieresLignesTailleEgale(){
        // lower bound : 1
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        dataFrame1.afficherPremieresLignes(1);
        expectedOutput = "	Column1	Column2	Column3\n1	1	2	3\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage des 1 premières lignes du tableau à lignes égales incorrect");

        // upper bound : number of rows
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        dataFrame1.afficherPremieresLignes(dataFrame1.getData().size());
        expectedOutput = "	Column1	Column2	Column3\n1	1	2	3\n2	4	5	6\n3	7	8	9\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage des 3 premières lignes du tableau à lignes égales incorrect");
    }

    /**
     * Test de l'affichage des premières lignes d'un DataFrame à lignes inégales
     */
    @Test
    public void testAffichagePremieresLignesTailleInegale(){
        // lower bound : 1
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        dataFrame2.afficherPremieresLignes(1);
        expectedOutput = "	Column1	Column2	Column3\n1	1	2	3\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage des 1 premières lignes du tableau à lignes inégales  incorrect");

        // upper bound : number of rows
        outContent.reset();
        dataFrame2.afficherPremieresLignes(dataFrame2.getData().size());
        // j'ai compensé les espaces vides avec des ""
        expectedOutput = "	Column1	Column2	Column3\n1	1	2	3\n2	4\t\t\n3	7	8\t\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage des 3 premières lignes du tableau à lignes inégales incorrect");
    }

    /**
     * Test de l'affichage des dernières lignes d'un DataFrame à lignes egales
     */
    @Test
    public void testAffichageDernieresLignesTailleEgale(){
        // lower bound : 1
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        dataFrame1.afficherDernieresLignes(1);
        expectedOutput = "	Column1	Column2	Column3\n3	7	8	9\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage des 1 dernières lignes du tableau à lignes égales incorrect");

        // upper bound : number of rows
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        dataFrame1.afficherDernieresLignes(dataFrame1.getData().size());
        expectedOutput = "	Column1	Column2	Column3\n1	1	2	3\n2	4	5	6\n3	7	8	9\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage des 3 dernières lignes du tableau à lignes égales incorrect");
    }

    /**
     * Test de l'affichage des dernières lignes d'un DataFrame à lignes inégales
     */
    @Test
    public void testAffichageDernieresLignesTailleInegale(){
        // lower bound : 1
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        dataFrame2.afficherDernieresLignes(1);
        expectedOutput = "	Column1	Column2	Column3\n3	7	8	\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage des 1 dernières lignes du tableau à lignes inégales incorrect");

        // upper bound : number of rows
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        dataFrame2.afficherDernieresLignes(dataFrame2.getData().size());
        expectedOutput = "	Column1	Column2	Column3\n1	1	2	3\n2	4\t\t\n3	7	8\t\n";
        actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, "Affichage des 3 dernières lignes du tableau à lignes inégales incorrect");
    }

    /**
     * Teste les exceptions levées lors de l'affichage des premières et dernières lignes sur un nombre de lignes nul ou négatif
     */
    @Test
    public void testExceptionAfichageLignesNulOuNegatif(){
        // Premières lignes
        assertThrows(IllegalArgumentException.class, () -> dataFrame1.afficherPremieresLignes(0),
                "Le nombre de lignes à afficher doit être strictement positif");
        assertThrows(IllegalArgumentException.class, () -> dataFrame1.afficherPremieresLignes(-1),
                "Le nombre de lignes à afficher doit être strictement positif");
        // Dernieres lignes
        assertThrows(IllegalArgumentException.class, () -> dataFrame2.afficherDernieresLignes(0),
                "Le nombre de lignes à afficher doit être strictement positif");
        assertThrows(IllegalArgumentException.class, () -> dataFrame2.afficherDernieresLignes(-1),
                "Le nombre de lignes à afficher doit être strictement positif");
    }

    /**
     * Teste les exceptions levées lors de l'affichage des premières et dernières lignes sur un nombre de lignes excessif
     */
    @Test
    public void testExceptionAffichageLignesExcessif(){
        // Premières lignes
        assertThrows(IllegalArgumentException.class, () -> dataFrame1.afficherPremieresLignes(4),
                "Le nombre de lignes à afficher doit être inférieur ou égal au nombre de lignes du dataframe");
        // Dernieres lignes
        assertThrows(IllegalArgumentException.class, () -> dataFrame2.afficherDernieresLignes(4),
                "Le nombre de lignes à afficher doit être inférieur ou égal au nombre de lignes du dataframe");
    }
}
