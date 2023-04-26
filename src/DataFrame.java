import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DataFrame {

    private ArrayList<ArrayList<String>> data;
    private ArrayList<Integer> index;
    private ArrayList<String> columnNames;
    private ArrayList<String> types;

    private String possibleTypes[] = {"STRING","INTEGER","BOOLEAN","FLOAT"};

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
    public DataFrame(ArrayList<ArrayList<String>> data, ArrayList<Integer> index, ArrayList<String> columnNames) {

        if (data.size() != columnNames.size()) {
            throw new IllegalArgumentException("Le nombre de colonnes de données est différent du nombre de noms de colonnes");
        }

        int max = 0;
        for (ArrayList<String> colonne : data) {
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
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isDoublonColumnNames(String name){
        return this.columnNames.contains(name);
    }

    private boolean isDoublonIndex(Integer index){
        return this.index.contains(index);
    }

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

}
