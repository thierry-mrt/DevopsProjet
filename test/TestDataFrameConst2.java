import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataFrameConst2  {

    private String csvPath = "resources/csvFiles";
    private String listColumnNames[] = {"niveau","evoActuel","premiereEvo","derniereEvo"};
    private Integer listIndex[] = {0,1,2};
    private Integer nonContiguousListIndex[] = {78,15,0};
    private String listTypes[] = {"Integer","String","String","String"};
    private  ArrayList<ArrayList<String>> listData;

    public TestDataFrameConst2(){
        listData = new ArrayList<>();
        ArrayList<String> line0 = new ArrayList<>(Arrays.asList("22","Reptincel","Salameche","Dracaufeu"));
        ArrayList<String> line1 = new ArrayList<>(Arrays.asList("8","Poussifeu","Poussifeu","Brasegali"));
        ArrayList<String> line2 = new ArrayList<>(Arrays.asList("65","Laggron","Gobou","Laggron"));
        listData.add(line0);listData.add(line1);listData.add(line2);
    }

    @Test
    public void testFileNormalColumn(){
        DataFrame d = new DataFrame(csvPath+"/fileNormal.csv",";");

        ArrayList<String> columnNames = d.getColumnNames();
        int columnNamesSize = columnNames.size();

        //Check columnNames
        assertEquals(listColumnNames.length,columnNamesSize,"Number of column");
        for (int i = 0; i < columnNamesSize; i++) {
            assertEquals(listColumnNames[i],columnNames.get(i),"Column names");
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
            assertEquals(listIndex[i],index.get(i),"Index names");
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
            assertEquals(listTypes[i].toUpperCase(),types.get(i),"Types names");
        }

    }

    @Test
    public void testFileNormalData(){
        DataFrame d = new DataFrame(csvPath+"/fileNormal.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int dataSize = data.size();
        int columnNamesSize = d.getColumnNames().size();

        //Check data
        assertEquals(listData.size(),dataSize,"Number of row");
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < columnNamesSize; j++) {
                assertEquals(listData.get(i).get(j),data.get(i).get(j),"The same data");
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
            assertEquals(columnSizes,lineDataSize,"Row of data is same size of columnNames");
        }
    }

    @Test
    public void testFileEmpty(){
        DataFrame d = new DataFrame(csvPath+"/fileEmpty.csv",";");

        ArrayList<String> columnNames = d.getColumnNames();
        ArrayList<Integer> index = d.getIndex();
        ArrayList<ArrayList<String>> data = d.getData();
        ArrayList<String> types = d.getTypes();

        int columnNamesSize = columnNames.size();
        int indexSize = index.size();
        int dataSize = data.size();
        int typeSize = types.size();

        assertEquals(0,dataSize,"Empty data");
        assertEquals(0,columnNamesSize,"Empty columnNames");
        assertEquals(0,indexSize,"Empty index");
        assertEquals(0,typeSize,"Empty types");
    }

    @Test
    public void testFileEmptyLinesColumn(){
        DataFrame d = new DataFrame(csvPath+"/fileEmptyLines.csv",";");

        ArrayList<String> columnNames = d.getColumnNames();
        int columnNamesSize = columnNames.size();

        //Check columnNames
        assertEquals(listColumnNames.length,columnNamesSize,"Number of column");
        for (int i = 0; i < columnNamesSize; i++) {
            assertEquals(listColumnNames[i],columnNames.get(i),"Column names");
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
            assertEquals(listTypes[i].toUpperCase(),types.get(i),"Types names");
        }

    }

    @Test
    public void testFileEmptyLinesData(){
        DataFrame d = new DataFrame(csvPath+"/fileEmptyLines.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int dataSize = data.size();
        int columnNamesSize = d.getColumnNames().size();

        //Check data
        assertEquals(listData.size(),dataSize,"Number of row");
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < columnNamesSize; j++) {
                assertEquals(listData.get(i).get(j),data.get(i).get(j),"The same data");
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
            assertEquals(columnSizes,lineDataSize,"Row of data is same size of columnNames");
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
            assertEquals(listColumnNames[i],columnNames.get(i),"Column names");
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
            assertEquals(nonContiguousListIndex[i],index.get(i),"Index names");
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
            assertEquals(listTypes[i].toUpperCase(),types.get(i),"Types names");
        }

    }

    @Test
    public void testFileNonContiguousIndexData(){
        DataFrame d = new DataFrame(csvPath+"/fileNonContiguousIndex.csv",";");

        ArrayList<ArrayList<String>> data = d.getData();
        int dataSize = data.size();
        int columnNamesSize = d.getColumnNames().size();

        //Check data
        assertEquals(listData.size(),dataSize,"Number of row");
        for (int i = 0; i < dataSize; i++) {
            for (int j = 0; j < columnNamesSize; j++) {
                assertEquals(listData.get(i).get(j),data.get(i).get(j),"The same data");
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
            assertEquals(columnSizes,lineDataSize,"Row of data is same size of columnNames");
        }
    }

//    @Test
//    public void testFileNonContiguousIndex(){
//        DataFrame d = new DataFrame(csvPath+"/fileNonContiguousIndex.csv",";");
//    }

}
