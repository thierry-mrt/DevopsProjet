import java.util.ArrayList;

public class DataFrame {

    private ArrayList<ArrayList<Object>> data;
    private ArrayList<Integer> index;
    private ArrayList<String> columnNames;

    public ArrayList<ArrayList<Object>> getData() {
        return data;
    }
    public ArrayList<Integer> getIndex() {
        return index;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }


    public DataFrame(ArrayList<ArrayList<Object>> data, ArrayList<Integer> index, ArrayList<String> columnNames) {

        if (data.size() != columnNames.size()) {
            throw new IllegalArgumentException("Le nombre de colonnes de données est différent du nombre de noms de colonnes");
        }

        int max = 0;
        for (ArrayList<Object> colonne : data) {
            if (colonne.size() > max) max = colonne.size();
        }
        if (max != index.size()) {
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
    }

}
