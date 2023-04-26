import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDataFrameStats {

    private DataFrame dataFrame;
    float expected, actual;

    public TestDataFrameStats() {
        ArrayList<String> columnNames1 = new ArrayList<>(Arrays.asList("Entiers", "Flottants", "Strings","Vide"));
        ArrayList<Integer> index1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<ArrayList<String>> data1 = new ArrayList<>();
        data1.add(new ArrayList<>(Arrays.asList("1", "2.5", "Hello")));
        data1.add(new ArrayList<>(Arrays.asList("3", "3.5", "Hola")));
        data1.add(new ArrayList<>(Arrays.asList("5", "3.0", "Allo")));
        ArrayList<String> types1 = new ArrayList<>(Arrays.asList("INTEGER", "FLOAT", "STRING","INTEGER"));
        dataFrame = new DataFrame(data1, index1, columnNames1, types1);
    }

    @Test
    public void testCalculerMoyenneColonneEntiers() {
        expected = 3;
        actual = dataFrame.calculerMoyenneColonne("Entiers");
        assertEquals(expected, actual, "Moyenne de la colonne Entiers incorrecte");
    }

    @Test
    public void testCalculerMoyenneColonneFlottants() {
        expected = 3;
        actual = dataFrame.calculerMoyenneColonne("Flottants");
        assertEquals(expected, actual, "Moyenne de la colonne Flottants incorrecte");
    }

    @Test
    public void testCalculerMoyenneColonneVide() {
        expected = 0;
        actual = dataFrame.calculerMoyenneColonne("Vide");
        assertEquals(expected, actual, "Moyenne de la colonne Vide incorrecte");
    }

    @Test
    public void testCalculerMoyenneColonneInexistante() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerMoyenneColonne("Panier"),
                "La colonne Panier n'existe pas");
    }

    @Test
    public void testCalculerMoyenneColonneNonNumerique() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerMoyenneColonne("Strings"),
                "La colonne Strings n'est pas numérique");
    }

    @Test
    public void testCalculerMaximumColonneEntiers() {
        expected = 5;
        actual = dataFrame.calculerLeMaximumColonne("Entiers");
        assertEquals(expected, actual, "Maximum de la colonne Entiers incorrecte");
    }

    @Test
    public void testCalculerMaximumColonneVide() {
        expected = 0;
        actual = dataFrame.calculerLeMaximumColonne("Vide");
        assertEquals(expected, actual, "Maximum de la colonne Vide incorrecte");
    }

    @Test
    public void testCalculerMaximumColonneInexistante() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMaximumColonne("Panier"),
                "La colonne Panier n'existe pas");
    }

    @Test
    public void testCalculerMaximumColonneNonNumerique() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMaximumColonne("Strings"),
                "La colonne Strings n'est pas numérique");
    }

    @Test
    public void testCalculerMinimumColonneEntiers() {
        expected = 1;
        actual = dataFrame.calculerLeMinimumColonne("Entiers");
        assertEquals(expected, actual, "Minimum de la colonne Entiers incorrecte");
    }

    @Test
    public void testCalculerMinimumColonneFlottants() {
        expected = 2.5F;
        actual = dataFrame.calculerLeMinimumColonne("Flottants");
        assertEquals(expected, actual, "Minimum de la colonne Flottants incorrecte");
    }

    @Test
    public void testCalculerMinimumColonneVide() {
        expected = 0;
        actual = dataFrame.calculerLeMinimumColonne("Vide");
        assertEquals(expected, actual, "Minimum de la colonne Vide incorrecte");
    }

    @Test
    public void testCalculerMinimumColonneInexistante() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMinimumColonne("Panier"),
                "La colonne Panier n'existe pas");
    }

    @Test
    public void testCalculerMinimumColonneNonNumerique() {
        assertThrows(IllegalArgumentException.class, () -> dataFrame.calculerLeMinimumColonne("Strings"),
                "La colonne Strings n'est pas numérique");
    }

}
