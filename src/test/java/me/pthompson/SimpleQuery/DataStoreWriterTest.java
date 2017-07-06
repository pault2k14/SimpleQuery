package me.pthompson.SimpleQuery;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Provides basic testing of the DataStoreWriter class.
 */
public class DataStoreWriterTest {

    private File dbFile;

    @Before
    public void setup() {

        dbFile = new File(DataStoreFilenames.DB_FILE_NAME.getValue());

        if(dbFile.exists()) {
            dbFile.delete();
        }
    }

    @Test
    public void testWrite() throws Exception {

        String expectedDbFileString = "\"stb1\"|\"the matrix\"|\"warner bros\"|\"2015-05-01\"|\"4.00\"|\"1:30\"\n" +
                "\"stb1\"|\"unbreakable\"|\"buena vista\"|\"2014-04-03\"|\"6.00\"|\"2:05\"\n" +
                "\"stb2\"|\"the hobbit\"|\"warner bros\"|\"2014-04-02\"|\"8.00\"|\"2:45\"\n" +
                "\"stb3\"|\"the matrix\"|\"warner bros\"|\"2014-04-02\"|\"2.00\"|\"1:05\"\n" +
                "\"stb2\"|\"the matrix\"|\"warner bros\"|\"2014-05-01\"|\"4.00\"|\"1:30\"\n" +
                "\"stb3\"|\"deadpool\"|\"20th century fox\"|\"2016-11-21\"|\"4.00\"|\"1:48\"\n" +
                "\"stb2\"|\"blair witch\"|\"artisan entertainment\"|\"2015-04-01\"|\"4.00\"|\"2:16\"\n" +
                "\"stb1\"|\"the revenant\"|\"20th century fox\"|\"2016-04-01\"|\"4.00\"|\"12:36\"\n";

        List<DataStoreItem> dataStoreItems = ExtFileReader.read("data.txt");
        DataStoreWriter dataStoreWriter = new DataStoreWriter();

        for(DataStoreItem dataStoreItem : dataStoreItems) {
            dataStoreWriter.write(dataStoreItem);
        }

        assert(dbFile.exists());
        String contents = fileToString(DataStoreFilenames.DB_FILE_NAME.getValue(), Charset.defaultCharset());

        assert(contents.equals(expectedDbFileString));
    }

    private static String fileToString(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
