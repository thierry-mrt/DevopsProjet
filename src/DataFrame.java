import java.io.*;
import java.lang.reflect.InvocationTargetException;
import static java.lang.Integer.parseInt;
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
     * First line is the types of each column
     * Second line is the name of each column
     * First column is the name of each line
     * @param filePath Absolute path to a .csv file
     * @param fileSeparator Delimiter used in the .csv file
     * Throw IllegalArgumentException si:
     * - un type inconnu est passé
     * - le fichier est vide
     * - il manque soit les types, les noms de colonnes ou les index
     * - il y a des doublons dans le nom des colonnes ou le nom des index
     * - le mauvais separateur est passé
     * - fichier n'existe pas
     * - les index ne sont pas des integers
     */
    public DataFrame(String filePath, String fileSeparator) {
        this.data = new ArrayList<>();
        this.columnNames = new ArrayList<>();
        this.index = new ArrayList<>();
        this.types = new ArrayList<>();

        File file = new File(filePath);
        BufferedReader br = null ;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber;
            lineNumber = 0;

            //For each line of the .csv file
            while ((line = br.readLine()) != null) {
                //If the line contains at least one character
                if(line.length() > 0){
                    String[] separated = line.split("\\"+fileSeparator);  //Escape the delimiter

                    //if its the line for the type
                    if(lineNumber == 0){
                        if(createTypes(separated) == false){
                            //THROW EXCEPTION UNKNOW TYPE
                            data = null;
                            columnNames = null;
                            index = null;
                            types = null;
                            throw new IllegalArgumentException("Unknown Type");
                        }
                    }else{
                        if(lineNumber == 1){  //if its line for the columnNames
                            if(separated.length != types.size()){
                                //THROW EXCEPTION NOT SAME SIZE OF ROW
                                data = null;
                                columnNames = null;
                                index = null;
                                types = null;
                                throw new IllegalArgumentException("Not the same size between Column Names and Types");
                            }
                            createcolumnNames(separated);
                        }else{ //if its a data line
                            ArrayList<String> listLineData = new ArrayList<>();
                            for (int i = 0; i < separated.length; i++) {
                                if(i == 0){ //if its the index column
                                    //Si l'index existe dèjà (doublon) on arrête
                                    if(isDoublonIndex(parseInt(separated[i]))){
                                        throw new IllegalArgumentException("Two index are the same -> impossible");
                                    }else{
                                        this.index.add(parseInt(separated[i]));
                                    }
                                }else{
                                    listLineData.add(separated[i]);
                                }
                            }

                            //Pour ajouter des données vides s'il n'y a pas le même nombre de données que de colonnes pour une ligne
                            if(separated.length-1 < columnNames.size()){
                                addEmptyRemainingData(listLineData,columnNames.size()-(separated.length-1));
                            }
                            this.data.add(listLineData);
                        }
                    }
                    lineNumber++;
                }
            }

            //Si le fichier est vide ou si un des éléments est manquant
            if(index.size() == 0 || columnNames.size() == 0 || types.size() == 0){
                data = null;
                columnNames = null;
                index = null;
                types = null;
                throw new IllegalArgumentException("Empty file or not enough line/column (need Types, Column and Index). Format is : \n" +
                        "Type1;Type2;Type3;...\n" +
                        "ColName1;ColName2;ColName3;...\n" +
                        "Index1;Data11;Data12;....\n" +
                        "Index2;Data21;Data22;....\n" +
                        "Index3;Data31;Data32;....\n");
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found : "+e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Index Names are not Integer : "+e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param name : columnNames to test
     * @return true true if the name is a doublon , false otherwise
     */
    private boolean isDoublonColumnNames(String name){
        return this.columnNames.contains(name);
    }

    /**
     * @param index : index to test
     * @return true if the index is a doublon , false otherwise
     */
    private boolean isDoublonIndex(Integer index){
        return this.index.contains(index);
    }

    /**
     * Add empty string to the end of the line data
     * @param listLineData : List of data for a line
     * @param nbRemainingEmptyData : Number of remaining data to add
     */
    private void addEmptyRemainingData(ArrayList<String> listLineData, int nbRemainingEmptyData) {
        for (int i = 0; i < nbRemainingEmptyData; i++) {
            listLineData.add("");
        }
    }

    /**
     * @param listTypes List of the types in the csv file
     * @return the number of column/types if ok else return -1
     */
    private boolean createTypes(String[] listTypes) {
        boolean validType = false;
        //For each types
        for (int i = 0 ; i < listTypes.length ; i++){
            for (int j = 0; j < possibleTypes.length ; j++) {
                if(listTypes[i].toUpperCase().equals(possibleTypes[j])){
                    validType = true;
                    break;
                }
            }
            if(validType){
                types.add(listTypes[i].toUpperCase());
            }else{
                return false;  //Non valid type
            }
            validType = false;

        }

        return true;

    }

    /**
     * Insert in columnNames all the names
     * @param listColumnNames : List of the different column names
     */
    private void createcolumnNames(String[] listColumnNames){
        //For each column names
        for (int i = 0 ; i < listColumnNames.length ; i++){
            if(isDoublonColumnNames(listColumnNames[i])){
                throw new IllegalArgumentException("Two column have the same name -> impossible");
            }else{
                columnNames.add(listColumnNames[i]);
            }
        }
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
  

     /* Affiche les premières lignes du DataFrame
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

    /**
     * Vérifie si la colonne contient des données numériques
     * @param colonne le nom de la colonne
     * @return vrai si la colonne contient des données numériques, faux sinon
     */
    private boolean columnIsNumeric(String colonne) {
        int indexColonne = columnNames.indexOf(colonne);
        if (!(types.get(indexColonne).equals("INTEGER") || types.get(indexColonne).equals("FLOAT"))) {
            throw new IllegalArgumentException("La colonne " + colonne + " ne contient pas des données numériques");
        }
        return true;
    }

    /**
     * Vérifie si la colonne existe
     * @param colonne le nom de la colonne
     * @return vrai si la colonne existe, faux sinon
     */
    private boolean columnDoesExist(String colonne) {
        if (!columnNames.contains(colonne)) {
            throw new IllegalArgumentException("La colonne " + colonne + " n'existe pas");
        }
        return true;
    }

    /**
     * Calcule la moyenne des valeurs d'une colonne
     * @param colonne le nom de la colonne
     * @return la liste des valeurs de la colonne ou 0 si la colonne est vide
     */
    public Float calculerMoyenneColonne(String colonne){
        assert columnDoesExist(colonne);
        assert columnIsNumeric(colonne);
        if (data.size() == 0) return 0F;

        int indexColonne = columnNames.indexOf(colonne);
        if (data.size() == 0) {
            return 0F;
        }
        float somme = 0;
        for (ArrayList<String> line: data) {
            if (line.get(indexColonne).equals("")) {
                continue;
            }
            somme += Float.parseFloat(line.get(indexColonne));
        }
        return somme / data.size();
    }

    /**
     * Calcule la valeur minimale d'une colonne
     * @param colonne le nom de la colonne
     * @return la valeur minimale de la colonne ou 0 si la colonne est vide
     */
    public Float calculerLeMinimumColonne(String colonne){
        assert columnDoesExist(colonne);
        assert columnIsNumeric(colonne);
        if (data.size() == 0) return 0F;

        int indexColonne = columnNames.indexOf(colonne);
        float minimum = Float.MAX_VALUE;
        for (ArrayList<String> line: data) {
            if (line.get(indexColonne).equals("")) {
                continue;
            }
            float valeur = Float.parseFloat(line.get(indexColonne));
            if (valeur < minimum) {
                minimum = valeur;
            }
        }
        return minimum==Float.MAX_VALUE?0:minimum;
    }

    /**
     * Calcule la valeur maximale d'une colonne
     * @param colonne le nom de la colonne
     * @return la valeur maximale de la colonne ou 0 si la colonne est vide
     */
    public Float calculerLeMaximumColonne(String colonne) {
        assert columnDoesExist(colonne);
        assert columnIsNumeric(colonne);
        if (data.size() == 0) return 0F;

        int indexColonne = columnNames.indexOf(colonne);
        float maximum = 0;
        for (ArrayList<String> line : data) {
            if (line.get(indexColonne).equals("")) {
                continue;
            }
            float valeur = Float.parseFloat(line.get(indexColonne));
            if (valeur > maximum) {
                maximum = valeur;
            }
        }
        return maximum;
    }


}
