import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataFrameConst2  {

    private String csvPath = "resources/csvFiles";
    private String listColumnNames[] = {"niveau","evoActuel","premiereEvo","derniereEvo"};
    private Integer listIndex[] = {0,1,2};
    private Integer nonContiguousListIndex[] = {78,15,0};
    private String listTypes[] = {"Integer","String","String","String"};
    private  ArrayList<ArrayList<String>> listDataFull, listDataMissing;

    public TestDataFrameConst2(){
        //FULL DATA
        listDataFull = new ArrayList<>();
        ArrayList<String> line0Full = new ArrayList<>(Arrays.asList("22","Reptincel","Salameche","Dracaufeu"));
        ArrayList<String> line1Full = new ArrayList<>(Arrays.asList("8","Poussifeu","Poussifeu","Brasegali"));
        ArrayList<String> line2Full = new ArrayList<>(Arrays.asList("65","Laggron","Gobou","Laggron"));
        listDataFull.add(line0Full);
        listDataFull.add(line1Full);
        listDataFull.add(line2Full);

        //SOME ROW MISS DATA ("")
        listDataMissing = new ArrayList<>();
        ArrayList<String> line0Miss = new ArrayList<>(Arrays.asList("22","","","Dracaufeu"));
        ArrayList<String> line1Miss = new ArrayList<>(Arrays.asList("","Poussifeu","Poussifeu","Brasegali"));
        ArrayList<String> line2Miss = new ArrayList<>(Arrays.asList("65","Laggron","Gobou",""));
        listDataMissing.add(line0Miss);
        listDataMissing.add(line1Miss);
        listDataMissing.add(line2Miss);

    }

    @Test
    public void testFileNormalColumn(){
        DataFrame d = new DataFrame(csvPath+"/fileNormal.csv",";");

        ArrayList<String> columnNames = d.getColumnNames();
        int columnNamesSize = columnNames.size();

        //Check columnNames
        assertEquals(listColumnNames.length,columnNamesSize,"Number of column");
        for (int i = 0; i < columnNamesSize; i++) {
            assertEquals(listColumnNames[i],columnNames.get(i),"Column names (i="+i+")");
        }

    }

    @Test
    public void testFileNormalIndex(){
        DataFrame d = new DataFrame(csvPath+"/fileNormal.csv",";");

        ArrayList<Integer> index = d.getIndex();
        int indexSize = index.size();

        //Check Index
        assertEquals(listIndex.length,indexSize,"Number of index");
        for (int i = 0; i < indexSize; i++) {
            assertEquals(listIndex[i],index.get(i),"Index names (i="+i+")");
        }
    }

    @Test
    public void testFileNormalTypes(){
        DataFrame d = new DataFrame(csvPath+"/fileNormal.csv",";");

        ArrayList<String> types = d.getTypes();
        int typeSize = types.size();

        //Check types
        assertEquals(listTypes.length,typeSize,"Number of type");
        for (int i = 0; i < typeSize; i++) {
            assertEquals(listTypes[i].toUpperCase(),types.get(i),"Types names (i="+i+")");
        }

    }

    @Test
    public void testFileNormalData(){
        DataFrame d = new DataFrame(csvPath+"/fileNormal.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int dataSize = data.size();
        int columnNamesSize = d.getColumnNames().size();

        //Check data
        assertEquals(listDataFull.size(),dataSize,"Number of row");
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < columnNamesSize; j++) {
                assertEquals(listDataFull.get(i).get(j),data.get(i).get(j),"The same data (i="+i+",j="+j+")");
            }
        }
    }

    @Test
    public void testFileNormalSizeOfArrays(){
        DataFrame d = new DataFrame(csvPath+"/fileNormal.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int typeSize = d.getTypes().size();
        int columnSizes = d.getColumnNames().size();
        int indexSize = d.getIndex().size();
        int dataSize = data.size();

        assertEquals(typeSize,columnSizes,"Type and column same size test");
        assertEquals(indexSize,dataSize,"Number of index and number of row same size test");

        //For each line of data
        int lineDataSize = 0;
        for (int i = 0; i < dataSize; i++) {
            lineDataSize = data.get(i).size();
            assertEquals(columnSizes,lineDataSize,"Row of data is same size of columnNames (i="+i+")");
        }
    }

    @Test
    public void testFileEmpty(){
        final DataFrame[] d = {null};

        assertThrows(IllegalArgumentException.class, () -> {
            d[0] = new DataFrame(csvPath+"/fileEmpty.csv",";");
        }, "Fichier vide, test du throw de IllegalArgumentException");

        assertEquals(null,d[0],"Dataframe ne doit pas avoir été créé");
    }

    @Test
    public void testFileEmptyLinesColumn(){
        DataFrame d = new DataFrame(csvPath+"/fileEmptyLines.csv",";");

        ArrayList<String> columnNames = d.getColumnNames();
        int columnNamesSize = columnNames.size();

        //Check columnNames
        assertEquals(listColumnNames.length,columnNamesSize,"Number of column");
        for (int i = 0; i < columnNamesSize; i++) {
            assertEquals(listColumnNames[i],columnNames.get(i),"Column names (i="+i+")");
        }

    }

    @Test
    public void testFileEmptyLinesIndex(){
        DataFrame d = new DataFrame(csvPath+"/fileEmptyLines.csv",";");

        ArrayList<Integer> index = d.getIndex();
        int indexSize = index.size();

        //Check Index
        assertEquals(listIndex.length,indexSize,"Number of index");
        for (int i = 0; i < indexSize; i++) {
            assertEquals(listIndex[i],index.get(i),"Index names");
        }

    }

    @Test
    public void testFileEmptyLinesTypes(){
        DataFrame d = new DataFrame(csvPath+"/fileEmptyLines.csv",";");

        ArrayList<String> types = d.getTypes();
        int typeSize = types.size();

        //Check types
        assertEquals(listTypes.length,typeSize,"Number of type");
        for (int i = 0; i < typeSize; i++) {
            assertEquals(listTypes[i].toUpperCase(),types.get(i),"Types names (i="+i+")");
        }

    }

    @Test
    public void testFileEmptyLinesData(){
        DataFrame d = new DataFrame(csvPath+"/fileEmptyLines.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int dataSize = data.size();
        int columnNamesSize = d.getColumnNames().size();

        //Check data
        assertEquals(listDataFull.size(),dataSize,"Number of row");
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < columnNamesSize; j++) {
                assertEquals(listDataFull.get(i).get(j),data.get(i).get(j),"The same data (i="+i+",j="+j+")");
            }
        }
    }

    @Test
    public void testFileEmptyLinesSizeOfArrays(){
        DataFrame d = new DataFrame(csvPath+"/fileEmptyLines.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int typeSize = d.getTypes().size();
        int columnSizes = d.getColumnNames().size();
        int indexSize = d.getIndex().size();
        int dataSize = data.size();

        assertEquals(typeSize,columnSizes,"Type and column same size test");
        assertEquals(indexSize,dataSize,"Number of index and number of row same size test");

        //For each line of data
        int lineDataSize = 0;
        for (int i = 0; i < dataSize; i++) {
            lineDataSize = data.get(i).size();
            assertEquals(columnSizes,lineDataSize,"Row of data is same size of columnNames (i="+i+")");
        }
    }

    @Test
    public void testFileNonContiguousIndexColumn(){
        DataFrame d = new DataFrame(csvPath+"/fileNonContiguousIndex.csv",";");

        ArrayList<String> columnNames = d.getColumnNames();
        int columnNamesSize = columnNames.size();

        //Check columnNames
        assertEquals(listColumnNames.length,columnNamesSize,"Number of column");
        for (int i = 0; i < columnNamesSize; i++) {
            assertEquals(listColumnNames[i],columnNames.get(i),"Column names (i="+i+")");
        }

    }

    @Test
    public void testFileNonContiguousIndexIndex(){
        DataFrame d = new DataFrame(csvPath+"/fileNonContiguousIndex.csv",";");

        ArrayList<Integer> index = d.getIndex();
        int indexSize = index.size();

        //Check Index
        assertEquals(nonContiguousListIndex.length,indexSize,"Number of index");
        for (int i = 0; i < indexSize; i++) {
            assertEquals(nonContiguousListIndex[i],index.get(i),"Index names (i="+i+")");
        }
    }

    @Test
    public void testFileNonContiguousIndexTypes(){
        DataFrame d = new DataFrame(csvPath+"/fileNonContiguousIndex.csv",";");

        ArrayList<String> types = d.getTypes();
        int typeSize = types.size();

        //Check types
        assertEquals(listTypes.length,typeSize,"Number of type");
        for (int i = 0; i < typeSize; i++) {
            assertEquals(listTypes[i].toUpperCase(),types.get(i),"Types names (i="+i+")");
        }

    }

    @Test
    public void testFileNonContiguousIndexData(){
        DataFrame d = new DataFrame(csvPath+"/fileNonContiguousIndex.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int dataSize = data.size();
        int columnNamesSize = d.getColumnNames().size();

        //Check data
        assertEquals(listDataFull.size(),dataSize,"Number of row");
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < columnNamesSize; j++) {
                assertEquals(listDataFull.get(i).get(j),data.get(i).get(j),"The same data (i="+i+",j="+j+")");
            }
        }
    }

    @Test
    public void testFileNonContiguousIndexSizeOfArrays(){
        DataFrame d = new DataFrame(csvPath+"/fileNonContiguousIndex.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int typeSize = d.getTypes().size();
        int columnSizes = d.getColumnNames().size();
        int indexSize = d.getIndex().size();
        int dataSize = data.size();

        assertEquals(typeSize,columnSizes,"Type and column same size test");
        assertEquals(indexSize,dataSize,"Number of index and number of row same size test");

        //For each line of data
        int lineDataSize = 0;
        for (int i = 0; i < dataSize; i++) {
            lineDataSize = data.get(i).size();
            assertEquals(columnSizes,lineDataSize,"Row of data is same size of columnNames (i="+i+")");
        }
    }

    @Test
    public void testFileMissingDataColumn(){
        DataFrame d = new DataFrame(csvPath+"/fileMissingData.csv",";");

        ArrayList<String> columnNames = d.getColumnNames();
        int columnNamesSize = columnNames.size();

        //Check columnNames
        assertEquals(listColumnNames.length,columnNamesSize,"Number of column");
        for (int i = 0; i < columnNamesSize; i++) {
            assertEquals(listColumnNames[i],columnNames.get(i),"Column names (i="+i+")");
        }

    }

    @Test
    public void testFileMissingDataIndex(){
        DataFrame d = new DataFrame(csvPath+"/fileMissingData.csv",";");

        ArrayList<Integer> index = d.getIndex();
        int indexSize = index.size();

        //Check Index
        assertEquals(listIndex.length,indexSize,"Number of index");
        for (int i = 0; i < indexSize; i++) {
            assertEquals(listIndex[i],index.get(i),"Index names (i="+i+")");
        }
    }

    @Test
    public void testFileMissingDataTypes(){
        DataFrame d = new DataFrame(csvPath+"/fileMissingData.csv",";");

        ArrayList<String> types = d.getTypes();
        int typeSize = types.size();

        //Check types
        assertEquals(listTypes.length,typeSize,"Number of type");
        for (int i = 0; i < typeSize; i++) {
            assertEquals(listTypes[i].toUpperCase(),types.get(i),"Types names (i="+i+")");
        }

    }

    @Test
    public void testFileMissingDataData(){
        DataFrame d = new DataFrame(csvPath+"/fileMissingData.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int dataSize = data.size();
        int columnNamesSize = d.getColumnNames().size();

        //Check data
        assertEquals(listDataMissing.size(),dataSize,"Number of row");
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < columnNamesSize; j++) {
                assertEquals(listDataMissing.get(i).get(j),data.get(i).get(j),"The same data (i="+i+",j="+j+")");
            }
        }
    }

    @Test
    public void testFileMissingDataSizeOfArrays(){
        DataFrame d = new DataFrame(csvPath+"/fileMissingData.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int typeSize = d.getTypes().size();
        int columnSizes = d.getColumnNames().size();
        int indexSize = d.getIndex().size();
        int dataSize = data.size();

        assertEquals(typeSize,columnSizes,"Type and column same size test");
        assertEquals(indexSize,dataSize,"Number of index and number of row same size test");

        //For each line of data
        int lineDataSize = 0;
        for (int i = 0; i < dataSize; i++) {
            lineDataSize = data.get(i).size();
            assertEquals(columnSizes,lineDataSize,"Row of data is same size of columnNames (i="+i+")");
        }
    }

    @Test
    public void testFileNotSameNumberTypesColumn(){
        final DataFrame[] d = {null};

        assertThrows(IllegalArgumentException.class, () -> {
            d[0] = new DataFrame(csvPath+"/fileMissingTypes.csv",";");
        }, "Fichier avec un nombre de types et de colonnes different, test du throw de IllegalArgumentException");

        assertEquals(null,d[0],"Dataframe ne doit pas avoir été créé");
    }

    @Test
    public void testFileWrongTypes(){
        final DataFrame[] d = {null};

        assertThrows(IllegalArgumentException.class, () -> {
            d[0] = new DataFrame(csvPath+"/fileWrongTypes.csv",";");
        }, "Fichier avec des types inconnus, test du throw de IllegalArgumentException");

        assertEquals(null,d[0],"Dataframe ne doit pas avoir été créé");
    }

    @Test
    public void testFileDoublonColumnNames(){
        final DataFrame[] d = {null};

        assertThrows(IllegalArgumentException.class, () -> {
            d[0] = new DataFrame(csvPath+"/fileDoublonColumnNames.csv",";");
        }, "Fichier avec des doublons dans les noms de colonnes, test du throw de IllegalArgumentException");

        assertEquals(null,d[0],"Dataframe ne doit pas avoir été créé");
    }

    @Test
    public void testFileDoublonIndex(){
        final DataFrame[] d = {null};

        assertThrows(IllegalArgumentException.class, () -> {
            d[0] = new DataFrame(csvPath+"/fileDoublonIndex.csv",";");
        }, "Fichier avec des doublons dans les numéro d'index, test du throw de IllegalArgumentException");

        assertEquals(null,d[0],"Dataframe ne doit pas avoir été créé");
    }

    @Test
    public void testFileDifferentSeparatorColumn(){
        DataFrame d = new DataFrame(csvPath+"/fileDifferentSeparator.csv",",");

        ArrayList<String> columnNames = d.getColumnNames();
        int columnNamesSize = columnNames.size();

        //Check columnNames
        assertEquals(listColumnNames.length,columnNamesSize,"Number of column");
        for (int i = 0; i < columnNamesSize; i++) {
            assertEquals(listColumnNames[i],columnNames.get(i),"Column names (i="+i+")");
        }

    }

    @Test
    public void testFileDifferentSeparatorIndex(){
        DataFrame d = new DataFrame(csvPath+"/fileDifferentSeparator.csv",",");

        ArrayList<Integer> index = d.getIndex();
        int indexSize = index.size();

        //Check Index
        assertEquals(listIndex.length,indexSize,"Number of index");
        for (int i = 0; i < indexSize; i++) {
            assertEquals(listIndex[i],index.get(i),"Index names (i="+i+")");
        }
    }

    @Test
    public void testFileDifferentSeparatorTypes(){
        DataFrame d = new DataFrame(csvPath+"/fileDifferentSeparator.csv",",");

        ArrayList<String> types = d.getTypes();
        int typeSize = types.size();

        //Check types
        assertEquals(listTypes.length,typeSize,"Number of type");
        for (int i = 0; i < typeSize; i++) {
            assertEquals(listTypes[i].toUpperCase(),types.get(i),"Types names (i="+i+")");
        }

    }

    @Test
    public void testFileDifferentSeparatorData(){
        DataFrame d = new DataFrame(csvPath+"/fileDifferentSeparator.csv",",");

        ArrayList<ArrayList<String>> data = d.getData();
        int dataSize = data.size();
        int columnNamesSize = d.getColumnNames().size();

        //Check data
        assertEquals(listDataFull.size(),dataSize,"Number of row");
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < columnNamesSize; j++) {
                assertEquals(listDataFull.get(i).get(j),data.get(i).get(j),"The same data (i="+i+",j="+j+")");
            }
        }
    }

    @Test
    public void testFileDifferentSeparatorSizeOfArrays(){
        DataFrame d = new DataFrame(csvPath+"/fileDifferentSeparator.csv",",");

        ArrayList<ArrayList<String>> data = d.getData();
        int typeSize = d.getTypes().size();
        int columnSizes = d.getColumnNames().size();
        int indexSize = d.getIndex().size();
        int dataSize = data.size();

        assertEquals(typeSize,columnSizes,"Type and column same size test");
        assertEquals(indexSize,dataSize,"Number of index and number of row same size test");

        //For each line of data
        int lineDataSize = 0;
        for (int i = 0; i < dataSize; i++) {
            lineDataSize = data.get(i).size();
            assertEquals(columnSizes,lineDataSize,"Row of data is same size of columnNames (i="+i+")");
        }
    }

    @Test
    public void testFileMultipleSeparator1(){
        final DataFrame[] d = {null};

        assertThrows(IllegalArgumentException.class, () -> {
            d[0] = new DataFrame(csvPath+"/fileMultipleSeparator.csv",";");
        }, "Fichier avec le mauvais fileSeparator utilisé, test du throw de IllegalArgumentException");

        assertEquals(null,d[0],"Dataframe ne doit pas avoir été créé");
    }

    @Test
    public void testFileMultipleSeparator2(){
        final DataFrame[] d = {null};

        assertThrows(IllegalArgumentException.class, () -> {
            d[0] = new DataFrame(csvPath+"/fileMultipleSeparator.csv",",");
        }, "Fichier avec le mauvais fileSeparator utilisé, test du throw de IllegalArgumentException");

        assertEquals(null,d[0],"Dataframe ne doit pas avoir été créé");
    }



}
