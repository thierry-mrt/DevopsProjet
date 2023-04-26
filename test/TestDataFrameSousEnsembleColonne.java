import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestDataFrameSousEnsembleColonne{

    private DataFrame dataFrame;

    /**
     * Constructeur de la classe de test
     */
    public TestDataFrameSousEnsembleColonne() {
        ArrayList<String> columnNames1 = new ArrayList<>(Arrays.asList("Fruit", "Marque", "Abreviation","Prenom","Animal"));
        ArrayList<Integer> index1 = new ArrayList<>(Arrays.asList(1, 2, 3,4,5,6,7,8));
        ArrayList<ArrayList<String>> data1 = new ArrayList<>();
        data1.add(new ArrayList<>(Arrays.asList("Pomme", "Nike", "PSG","Didier","Chat")));
        data1.add(new ArrayList<>(Arrays.asList("Banane", "Adidas", "OM","Jean","Chien")));
        data1.add(new ArrayList<>(Arrays.asList("Orange", "Puma", "OL","Pierre","Lapin")));
        data1.add(new ArrayList<>(Arrays.asList("Poire", "Reebok", "ASSE","Paul","Poisson")));
        data1.add(new ArrayList<>(Arrays.asList("Fraise", "Umbro", "FCN","Jacques","Souris")));
        data1.add(new ArrayList<>(Arrays.asList("Cerise", "Kappa", "FCM","Pascal","Lion")));
        data1.add(new ArrayList<>(Arrays.asList("Abricot", "Le Coq Sportif", "FCGB","Pierre","Tigre")));
        data1.add(new ArrayList<>(Arrays.asList("Mangue", "Hummel", "MHSC","Pierre","Loup")));
        ArrayList<String> types1 = new ArrayList<>(Arrays.asList("STRING", "STRING", "STRING","STRING","STRING"));
        dataFrame = new DataFrame(data1, index1, columnNames1, types1);
    }

    /**
     * Test de la méthode sousEnsembleColonne sur des colonnes existantes continues
     */
    @Test
    public void testSousEnsembleColonneExistantContinu(){
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("Fruit", "Marque", "Abreviation"));
        DataFrame subDataFrame = dataFrame.sousEnsembleColonnes(labels);

        ArrayList<String> expectedLabels = new ArrayList<>(Arrays.asList("Fruit", "Marque", "Abreviation"));
        ArrayList<Integer> expectedIndex = new ArrayList<>(Arrays.asList(1, 2, 3,4,5, 6,7,8));
        ArrayList<ArrayList<String>> expectedData = new ArrayList<>();
        expectedData.add(new ArrayList<>(Arrays.asList("Pomme", "Nike", "PSG")));
        expectedData.add(new ArrayList<>(Arrays.asList("Banane", "Adidas", "OM")));
        expectedData.add(new ArrayList<>(Arrays.asList("Orange", "Puma", "OL")));
        expectedData.add(new ArrayList<>(Arrays.asList("Poire", "Reebok", "ASSE")));
        expectedData.add(new ArrayList<>(Arrays.asList("Fraise", "Umbro", "FCN")));
        expectedData.add(new ArrayList<>(Arrays.asList("Cerise", "Kappa", "FCM")));
        expectedData.add(new ArrayList<>(Arrays.asList("Abricot", "Le Coq Sportif", "FCGB")));
        expectedData.add(new ArrayList<>(Arrays.asList("Mangue", "Hummel", "MHSC")));
        ArrayList<String> expectedTypes = new ArrayList<>(Arrays.asList("STRING", "STRING", "STRING"));

        assertEquals(expectedData, subDataFrame.getData(), "Les données du sous ensemble de colonnes ne sont pas correctes");
        assertEquals(expectedIndex, subDataFrame.getIndex(), "Les index du sous ensemble de colonnes ne sont pas correctes");
        assertEquals(expectedLabels, subDataFrame.getColumnNames(), "Les noms des colonnes du sous ensemble de colonnes ne sont pas correctes");
        assertEquals(expectedTypes, subDataFrame.getTypes(), "Les types du sous ensemble de colonnes ne sont pas correctes");
    }

    /**
     * Test de la méthode sousEnsembleColonne sur des colonnes existantes non continues
     */
    @Test
    public void testSousEnsembleColonneExistantNonContinu(){
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("Animal", "Marque", "Prenom"));
        DataFrame subDataFrame = dataFrame.sousEnsembleColonnes(labels);

        ArrayList<String> expectedLabels = new ArrayList<>(Arrays.asList("Animal", "Marque", "Prenom"));
        ArrayList<Integer> expectedIndex = new ArrayList<>(Arrays.asList(1, 2, 3,4,5,6,7,8));
        ArrayList<ArrayList<String>> expectedData = new ArrayList<>();
        expectedData.add(new ArrayList<>(Arrays.asList("Chat", "Nike", "Didier")));
        expectedData.add(new ArrayList<>(Arrays.asList("Chien", "Adidas", "Jean")));
        expectedData.add(new ArrayList<>(Arrays.asList("Lapin", "Puma", "Pierre")));
        expectedData.add(new ArrayList<>(Arrays.asList("Poisson", "Reebok", "Paul")));
        expectedData.add(new ArrayList<>(Arrays.asList("Souris", "Umbro", "Jacques")));
        expectedData.add(new ArrayList<>(Arrays.asList("Lion", "Kappa", "Pascal")));
        expectedData.add(new ArrayList<>(Arrays.asList("Tigre", "Le Coq Sportif", "Pierre")));
        expectedData.add(new ArrayList<>(Arrays.asList("Loup", "Hummel", "Pierre")));
        ArrayList<String> expectedTypes = new ArrayList<>(Arrays.asList("STRING", "STRING", "STRING"));

        assertEquals(expectedData, subDataFrame.getData(), "Les données du sous ensemble de colonnes ne sont pas correctes");
        assertEquals(expectedIndex, subDataFrame.getIndex(), "Les index du sous ensemble de colonnes ne sont pas correctes");
        assertEquals(expectedLabels, subDataFrame.getColumnNames(), "Les noms des colonnes du sous ensemble de colonnes ne sont pas correctes");
        assertEquals(expectedTypes, subDataFrame.getTypes(), "Les types du sous ensemble de colonnes ne sont pas correctes");
    }

    /**
     * Test de la méthode sousEnsembleColonne sur un tableau de labels à doublons
     */
    @Test
    public void testSousEnsembleColonneDoublons(){
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("Fruit", "Marque", "Fruit"));
        assertThrows(IllegalArgumentException.class, () -> dataFrame.sousEnsembleColonnes(labels), 
        "Un sous ensemble de nom de colonnes avec doublons a été fourni");
    }

    /**
     * Test de la méthode sousEnsembleColonne sur des colonnes non existantes
     */
    @Test
    public void testSousEnsembleColonneNonExistant(){
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("Ballons", "Poissons", "Villes","Stars","Drapeaux"));
        assertThrows(Exception.class, () -> dataFrame.sousEnsembleColonnes(labels), 
        "Un sous ensemble de nom de colonnes non existantes a été fourni");
    }

    /**
     * Test de la méthode sousEnsembleColonne sur des colonnes existantes et non existantes
     */
    @Test
    public void testSousEnsembleColonneExistantNonExistant(){
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("Fruit", "Marque", "Abreviation","Stars","Drapeaux"));
        assertThrows(Exception.class, () -> dataFrame.sousEnsembleColonnes(labels), 
        "Certains noms de colonnes sont inexistants");
    }

    /**
     * Test de la méthode sousEnsembleColonne sur un nombre de colonnes au dessus du nombre de colonnes du DataFrame
     */
     @Test
    public void testSousEnsembleColonneTropGrand(){
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("Fruit", "Marque", "Abreviation","Animal","Prenom","Ballons","Poissons","Villes","Stars","Drapeaux"));
        assertThrows(Exception.class, () -> dataFrame.sousEnsembleColonnes(labels),
        "Le nombre de colonnes demandées est supérieur au nombre de colonnes du DataFrame original");
    }

    /**
     * Test de la méthode sousEnsembleColonne sur un nombre de colonnes null
     */
    @Test
    public void testSousEnsembleColonneNull(){
        ArrayList<String> labels = null;
        assertThrows(Exception.class, () -> dataFrame.sousEnsembleColonnes(labels),
        "Le nombre de colonnes demandées est null");
    }   

}