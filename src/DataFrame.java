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
     */
    public DataFrame(String filePath, String fileSeparator) {
        //Test doublons colonnes/index
        //Test données manquantes (au milieu ou à la fin)
        this.data = new ArrayList<>();
        this.columnNames = new ArrayList<>();
        this.index = new ArrayList<>();
        this.types = new ArrayList<>();

        File file = new File(filePath);
        BufferedReader br = null ;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber, colNumber;
            lineNumber = colNumber = 0;

            //For each line of the .csv file
            while ((line = br.readLine()) != null) {
                //If the line contains at least one character
                if(line.length() > 0){
                    String[] separated = line.split("\\"+fileSeparator);  //Escape the delimiter

                    //if its the line for the type
                    if(lineNumber == 0){
                        if(createTypes(separated) == -1){
                            //THROW EXCEPTION UNKNOW TYPE
                            data = null;
                            columnNames = null;
                            index = null;
                            types = null;
                            throw new RuntimeException();
                        }
                    }else{
                        if(lineNumber == 1){  //if its line for the columnNames
                            if(separated.length != types.size()){
                                //THROW EXCEPTION NOT SAME SIZE OF ROW
                                data = null;
                                columnNames = null;
                                index = null;
                                types = null;
                                throw new RuntimeException();
                            }
                            createcolumnNames(separated);
                        }else{ //if its a data line
                            if(separated.length != types.size()+1){
                                //THROW EXCEPTION NOT SAME SIZE OF ROW
                                data = null;
                                columnNames = null;
                                index = null;
                                types = null;
                                throw new RuntimeException();
                            }
                            ArrayList<String> listData = new ArrayList<>();
                            for (int i = 0; i < separated.length; i++) {
                                if(colNumber == 0){
                                    this.index.add(parseInt(separated[i]));
                                }else{
                                    listData.add(separated[i]);
                                }
                                colNumber++;
                            }
                            this.data.add(listData);
                        }
                    }
                    colNumber = 0;
                    lineNumber++;
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param listTypes List of the types in the csv file
     * @return the number of column/types if ok else return -1
     */
    private int createTypes(String[] listTypes) {
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
                return -1;  //Non valid type
            }
            validType = false;

        }

        return listTypes.length;

    }

    private void createcolumnNames(String[] listColumnNames){
        //For each column names
        for (int i = 0 ; i < listColumnNames.length ; i++){
            columnNames.add(listColumnNames[i]);
        }
    }

}
