import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDataFrameStats {

    private DataFrame dataFrame;
    float expected, actual;

    /**
     * Constructeur de la classe de test
     */
    public TestDataFrameStats() {
        ArrayList<String> columnNames1 = new ArrayList<>(Arrays.asList("Entiers", "Flottants", "Strings","Vide","Booleans"));
        ArrayList<Integer> index1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<ArrayList<String>> data1 = new ArrayList<>();
        data1.add(new ArrayList<>(Arrays.asList("1", "2.5", "Hello","","true")));
        data1.add(new ArrayList<>(Arrays.asList("3", "3.5", "Hola","","false")));
        data1.add(new ArrayList<>(Arrays.asList("5", "3.0", "Allo","","true")));
        ArrayList<String> types1 = new ArrayList<>(Arrays.asList("INTEGER", "FLOAT", "STRING","INTEGER","BOOLEAN"));
        dataFrame = new DataFrame(data1, index1, columnNames1, types1);
    }

    /**
     * Test de la méthode calculerSommeColonne sur des colonnes de type entier
     */
    @Test
    public void testCalculerSommeColonneEntiers() {
        expected = 9;
        actual = dataFrame.calculerSommeColonne("Entiers");
        assertEquals(expected, actual, "Somme de la colonne Entiers incorrecte");
    }

    /**
     * Test de la méthode calculerSommeColonne sur des colonnes de type flottant
     */
    @Test
    public void testCalculerSommeColonneFlottants() {
        expected = 9;
        actual = dataFrame.calculerSommeColonne("Flottants");
        assertEquals(expected, actual, "Somme de la colonne Flottants incorrecte");
    }

    /**
     * Test de la méthode calculerSommeColonne sur des colonnes de données vides
     */
    @Test
    public void testCalculerSommeColonneVide() {
        expected = 0;
        actual = dataFrame.calculerSommeColonne("Vide");
        assertEquals(expected, actual, "Somme de la colonne Vide incorrecte");
    }

    /**
     * Test de la méthode calculerSommeColonne sur un DataFrame vide
     */
    @Test
    public void testCalculerSommeColonneDataFrameVide() {
        ArrayList<String> columnNames = new ArrayList<>(Arrays.asList("Entiers", "Flottants", "Strings","Vide","Booleans"));
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>(Arrays.asList("INTEGER", "FLOAT", "STRING","INTEGER","BOOLEAN"));
        DataFrame dataFrameVide = new DataFrame(data, index, columnNames, types);
        expected = 0;
        actual = dataFrameVide.calculerSommeColonne("Entiers");
        assertEquals(expected, actual, "Somme de la colonne Entiers incorrecte");
    }

    /**
     * Test de la méthode calculerSommeColonne sur des colonnes inexistantes
     */
    @Test
    public void testCalculerSommeColonneInexistante() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerSommeColonne("Panier"),
                "La colonne Panier n'existe pas");
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerSommeColonne(""),
                "La colonne  n'existe pas");
    }

    /**
     * Test de la méthode calculerSommeColonne sur des colonnes non numériques
     */
    @Test
    public void testCalculerSommeColonneNonNumerique() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerSommeColonne("Strings"),
                "La colonne Strings n'est pas numérique (non-sommable)");
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerSommeColonne("Booleans"),
                "La colonne Booleans n'est pas numérique (non-sommable)");
    }

    /**
     * Test de la méthode calculerMoyenneColonne sur des colonnes de type entier
     */
    @Test
    public void testCalculerMoyenneColonneEntiers() {
        expected = 3;
        actual = dataFrame.calculerMoyenneColonne("Entiers");
        assertEquals(expected, actual, "Moyenne de la colonne Entiers incorrecte");
    }

    /**
     * Test de la méthode calculerMoyenneColonne sur des colonnes de type flottant
     */
    @Test
    public void testCalculerMoyenneColonneFlottants() {
        expected = 3;
        actual = dataFrame.calculerMoyenneColonne("Flottants");
        assertEquals(expected, actual, "Moyenne de la colonne Flottants incorrecte");
    }

    /**
     * Test de la méthode calculerMoyenneColonne sur des colonnes de données vides
     */
    @Test
    public void testCalculerMoyenneColonneVide() {
        expected = 0;
        actual = dataFrame.calculerMoyenneColonne("Vide");
        assertEquals(expected, actual, "Moyenne de la colonne Vide incorrecte");
    }

    /**
     * Test de la méthode calculerMoyenneColonne sur des colonnes inexistantes
     */
    @Test
    public void testCalculerMoyenneColonneInexistante() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerMoyenneColonne("Panier"),
                "La colonne Panier n'existe pas");
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerMoyenneColonne(""),
                "La colonne  n'existe pas");
    }

    /**
     * Test de la méthode calculerMoyenneColonne sur des colonnes non numériques
     */
    @Test
    public void testCalculerMoyenneColonneNonNumerique() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerMoyenneColonne("Strings"),
                "La colonne Strings n'est pas numérique (non-moyenable)");
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerMoyenneColonne("Booleans"),
                "La colonne Booleans n'est pas numérique (non-moyenable)");
    }

    /**
     * Test de la méthode calculerLeMaximumColonne sur des colonnes de type entier
     */
    @Test
    public void testCalculerMaximumColonneEntiers() {
        expected = 5;
        actual = dataFrame.calculerLeMaximumColonne("Entiers");
        assertEquals(expected, actual, "Maximum de la colonne Entiers incorrecte");
    }

    /**
     * Test de la méthode calculerLeMaximumColonne sur des colonnes de type flottant
     */
    @Test
    public void testCalculerMaximumColonneFlottants() {
        expected = 3.5F;
        actual = dataFrame.calculerLeMaximumColonne("Flottants");
        assertEquals(expected, actual, "Maximum de la colonne Flottants incorrecte");
    }

    /**
     * Test de la méthode calculerLeMaximumColonne sur des colonnes de données vides
     */
    @Test
    public void testCalculerMaximumColonneVide() {
        expected = 0;
        actual = dataFrame.calculerLeMaximumColonne("Vide");
        assertEquals(expected, actual, "Maximum de la colonne Vide incorrecte");
    }

    /**
     * Test de la méthode calculerLeMaximumColonne sur des colonnes inexistantes
     */
    @Test
    public void testCalculerMaximumColonneInexistante() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMaximumColonne("Panier"),
                "La colonne Panier n'existe pas");
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMaximumColonne(""),
                "La colonne  n'existe pas");
    }

    /**
     * Test de la méthode calculerLeMaximumColonne sur des colonnes non numériques
     */
    @Test
    public void testCalculerMaximumColonneNonNumerique() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMaximumColonne("Strings"),
                "La colonne Strings n'est pas numérique (non-maximisable)");
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMaximumColonne("Booleans"),
                "La colonne Booleans n'est pas numérique (non-maximisable)");
    }

    /**
     * Test de la méthode calculerLeMinimumColonne sur des colonnes de type entier
     */
    @Test
    public void testCalculerMinimumColonneEntiers() {
        expected = 1;
        actual = dataFrame.calculerLeMinimumColonne("Entiers");
        assertEquals(expected, actual, "Minimum de la colonne Entiers incorrecte");
    }

    /**
     * Test de la méthode calculerLeMinimumColonne sur des colonnes de type flottant
     */
    @Test
    public void testCalculerMinimumColonneFlottants() {
        expected = 2.5F;
        actual = dataFrame.calculerLeMinimumColonne("Flottants");
        assertEquals(expected, actual, "Minimum de la colonne Flottants incorrecte");
    }

    /**
     * Test de la méthode calculerLeMinimumColonne sur des colonnes de données vides
     */
    @Test
    public void testCalculerMinimumColonneVide() {
        expected = 0;
        actual = dataFrame.calculerLeMinimumColonne("Vide");
        assertEquals(expected, actual, "Minimum de la colonne Vide incorrecte");
    }

    /**
     * Test de la méthode calculerLeMinimumColonne sur des colonnes inexistantes
     */
    @Test
    public void testCalculerMinimumColonneInexistante() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMinimumColonne("Panier"),
                "La colonne Panier n'existe pas");
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMinimumColonne(""),
                "La colonne  n'existe pas");
    }

    /**
     * Test de la méthode calculerLeMinimumColonne sur des colonnes non numériques
     */
    @Test
    public void testCalculerMinimumColonneNonNumerique() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMinimumColonne("Strings"),
                "La colonne Strings n'est pas numérique (non-minimisable)");
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMinimumColonne("Booleans"),
                "La colonne Booleans n'est pas numérique (non-minimisable)");
    }
}
