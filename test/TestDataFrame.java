import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataFrame {

//    File folderCSV;
//    ArrayList<String> listeCSVFiles;
//    String currentFileName;
//    int currentIndexFileName = 0;
//
//    //FONCTION UTILITAIRE
//    //Return the list of all the files in the File folder (put different csv in this folder to test them)
//    private ArrayList<String> listFilesForFolder(final File folder) {
//        ArrayList<String> list = new ArrayList<>();
//        for (final File fileEntry : folder.listFiles()) {
//            if (fileEntry.isDirectory()) {
//                listFilesForFolder(fileEntry);
//            } else {
//                list.add(fileEntry.getName());
//            }
//        }
//        return list;
//    }
//
//    @BeforeAll
//    public void beforeAllTestOnce(){
//        folderCSV = new File("../resources/csvFiles");
//        listeCSVFiles = listFilesForFolder(folderCSV);
//    }
//
//    @BeforeEach
//    public void beforeEachTest(){
//        currentFileName = listeCSVFiles.get(currentIndexFileName);
//        currentIndexFileName++;
//    }

    @Test
    public void testFirst(){
        assertTrue(true, "test");
    }

}
