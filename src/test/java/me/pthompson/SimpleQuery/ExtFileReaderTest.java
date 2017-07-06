package me.pthompson.SimpleQuery;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Provides basic testing of the ExtFileReader class.
 */
public class ExtFileReaderTest {

    private String TEST_FILE = "testFile";

    @Test
    public void testReadWithData() throws Exception {

        ArrayList<DataStoreItem> expectedDataStoreItems = new ArrayList<>();
        expectedDataStoreItems.add(new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30"));
        expectedDataStoreItems.add(new DataStoreItem("stb1", "unbreakable", "buena vista",
                "2014-04-03", "6.00", "2:05"));
        expectedDataStoreItems.add(new DataStoreItem("stb2", "the hobbit", "warner bros",
                "2014-04-02", "8.00", "2:45"));
        expectedDataStoreItems.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));
        expectedDataStoreItems.add(new DataStoreItem("stb2", "the matrix", "warner bros",
                "2014-05-01", "4.00", "1:30"));
        expectedDataStoreItems.add(new DataStoreItem("stb3", "deadpool", "20th century fox",
                "2016-11-21", "4.00", "1:48"));
        expectedDataStoreItems.add(new DataStoreItem("stb2", "blair witch", "artisan entertainment",
                "2015-04-01", "4.00", "2:16"));
        expectedDataStoreItems.add(new DataStoreItem("stb1", "the revenant", "20th century fox",
                "2016-04-01", "4.00", "12:36"));

        List<DataStoreItem> dataStoreItems = ExtFileReader.read("data.txt");

        for(int i = 0; i < expectedDataStoreItems.size(); ++i) {
            assert(expectedDataStoreItems.get(i).equals(dataStoreItems.get(i)));
        }

    }

    @Test(expected = FileNotFoundException.class)
    public void testReadNoData() throws Exception {

        File testFile = new File(TEST_FILE);

        if(testFile.exists()) {
            testFile.delete();
            testFile.createNewFile();
        }

        ExtFileReader.read(TEST_FILE);

        // Shouldn't make it this far
        fail("Exception not thorwn");
    }
}
