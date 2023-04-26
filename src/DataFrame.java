import java.util.ArrayList;
import java.util.Arrays;

public class DataFrame {

    private ArrayList<ArrayList<String>> data;
    private ArrayList<Integer> index;
    private ArrayList<String> columnNames;
    private ArrayList<String> types;
    private String[] possibleTypes = {"STRING", "INTEGER", "BOOLEAN", "FLOAT"}; // types autorisés

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }
    public ArrayList<Integer> getIndex() {
        return index;
    }
    public ArrayList<String> getColumnNames() {
        return columnNames;
    }
    public ArrayList<String> getTypes() {
        return types;
    }

    /**
     * Constructeur de la classe DataFrame
     * @param data tableau de données (lignes du DataFrame)
     * @param index tableau d'index (un index par ligne)
     * @param columnNames tableau de noms de colonnes (un nom par colonne)
     * @param types tableau de types de données (un type par colonne)
     */
    public DataFrame(ArrayList<ArrayList<String>> data, ArrayList<Integer> index, ArrayList<String> columnNames, ArrayList<String> types) {

        if (types.size()==0 && columnNames.size()!=0) {
            throw new IllegalArgumentException("Aucun type de données n'a été fourni");
        }

        for (int i = 0; i < types.size(); i++) {
            types.set(i, types.get(i).toUpperCase());
            if (!Arrays.asList(possibleTypes).contains(types.get(i))) {
                throw new IllegalArgumentException("Le type " + types.get(i) + " n'est pas autorisé");
            }
        }

        int nombreColonnes = columnNames.size();

        for (ArrayList<String> line: data) {
            while (line.size() < columnNames.size()) {
                line.add("");
            }
            if (line.size() > nombreColonnes) {
                throw new IllegalArgumentException("Une ligne de données a plus d'éléments que le nombre de colonnes fournies");
            }
        }

        int nombreLignes = data.size();

        if (nombreColonnes != types.size()) {
            throw new IllegalArgumentException("Le nombre de colonnes de données est différent du nombre de types fournis");
        }
        if (nombreLignes != index.size()) {
            throw new IllegalArgumentException("Le nombre d'index est différent du nombre de lignes");
        }

        boolean contientDoublonsColonnes = false;
        for (int i = 0; i < columnNames.size(); i++) {
            for (int j = i + 1; j < columnNames.size(); j++) {
                if (columnNames.get(i).equals(columnNames.get(j))) {
                    contientDoublonsColonnes = true;
                    break;
                }
            }
        }
        if (contientDoublonsColonnes) {
            throw new IllegalArgumentException("Le nom des colonnes ne doit pas être identique");
        }

        boolean contientDoublonsIndex = false;
        for (int i = 0; i < index.size(); i++) {
            for (int j = i + 1; j < index.size(); j++) {
                if (index.get(i).equals(index.get(j))) {
                    contientDoublonsIndex = true;
                    break;
                }
            }
        }
        if (contientDoublonsIndex) {
            throw new IllegalArgumentException("Les index ne doivent pas être identiques");
        }

        this.data = data;
        this.columnNames = columnNames;
        this.index = index;
        this.types = types;
    }

    /**
     * Affiche le DataFrame
     */
    public void afficher(){
        System.out.println("\t" + String.join("\t", columnNames));
        for (int i = 0; i < data.size(); i++) {
            System.out.println(index.get(i) + "\t" + String.join("\t", data.get(i)));
        }
    }

    /**
     * Affiche les premières lignes du DataFrame
     * @param premieresLignes nombre de lignes à afficher
     */
    public void afficherPremieresLignes(int premieresLignes){
        if (premieresLignes <= 0) {
            throw new IllegalArgumentException("Le nombre de lignes demandées est null ou négatif");
        }
        if (premieresLignes > data.size()) {
            throw new IllegalArgumentException("Le nombre de lignes demandées est supérieur au nombre de lignes du DataFrame");
        }
        System.out.println("\t" + String.join("\t", columnNames));
        for (int i = 0; i < premieresLignes; i++) {
            System.out.println(index.get(i) + "\t" + String.join("\t", data.get(i)));
        }
    }

    /**
     * Affiche les dernières lignes du DataFrame
     * @param dernieresLignes nombre de lignes à afficher
     */
    public void afficherDernieresLignes(int dernieresLignes){
        if (dernieresLignes <= 0) {
            throw new IllegalArgumentException("Le nombre de lignes demandées est nul ou négatif");
        }
        if (dernieresLignes > data.size()) {
            throw new IllegalArgumentException("Le nombre de lignes demandées est supérieur au nombre de lignes du DataFrame");
        }
        System.out.println("\t" + String.join("\t", columnNames));
        for (int i = data.size() - dernieresLignes; i < data.size(); i++) {
            System.out.println(index.get(i) + "\t" + String.join("\t", data.get(i)));
        }
    }

}
