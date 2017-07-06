package me.pthompson.SimpleQuery;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Provides basic testing of the QueryRunner class.
 */
public class QueryRunnerTest {

    @Before
    public void setup() throws Exception {
        File dbFile = new File(DataStoreFilenames.DB_FILE_NAME.getValue());

        if(dbFile.exists()) {
            dbFile.delete();
        }

        DataStoreWriter dataStoreWriter = new DataStoreWriter();
        List<DataStoreItem> dataStoreItems = ExtFileReader.read("data.txt");

        for(DataStoreItem dataStoreItem : dataStoreItems) {
            dataStoreWriter.write(dataStoreItem);
        }
    }

    @Test
    public void testMainNoData() throws Exception {

        String expectedOutput = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        QueryRunner.main("");

        assert(baos.toString().equals(expectedOutput));
    }

    @Test
    public void testMainWithSelect() throws Exception {

        String expectedOutput = "the matrix\r\n" +
                "unbreakable\r\n" + "the hobbit\r\n" +
                "the matrix\r\n" + "the matrix\r\n" +
                "deadpool\r\n" + "blair witch\r\n" +
                "the revenant\r\n";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        String[] args = {"-s", "TITLE"};

        QueryRunner.main(args);

        assert(baos.toString().equals(expectedOutput));
    }

    @Test
    public void testMainWithFilter() throws Exception {
        String expectedOutput = "blair witch\r\n" + "deadpool\r\n" + "the hobbit\r\n" +
                "the matrix\r\n" + "the matrix\r\n" + "the matrix\r\n" + "the revenant\r\n" +
                "unbreakable\r\n";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        String[] args = {"-s", "TITLE", "-o", "TITLE"};

        QueryRunner.main(args);

        assert(baos.toString().equals(expectedOutput));
    }

    @Test
    public void testMainWithOrder() throws Exception {
        String expectedOutput = "the matrix\r\n" + "the matrix\r\n" + "the matrix\r\n";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        String[] args = {"-s", "TITLE", "-o", "TITLE", "-f", "TITLE=the matrix"};

        QueryRunner.main(args);

        assert(baos.toString().equals(expectedOutput));
    }

    @Test
    public void testMainWithLoad() throws Exception {
        String dataFilename = "data.txt";
        File dbFile = new File(DataStoreFilenames.DB_FILE_NAME.getValue());

        if(dbFile.exists()) {
            dbFile.delete();
        }

        String expectedOutput = "Successfully loaded data file " + dataFilename +"\r\n";

        String expectedDbFileString = "\"stb1\"|\"the matrix\"|\"warner bros\"|\"2015-05-01\"|\"4.00\"|\"1:30\"\n" +
                "\"stb1\"|\"unbreakable\"|\"buena vista\"|\"2014-04-03\"|\"6.00\"|\"2:05\"\n" +
                "\"stb2\"|\"the hobbit\"|\"warner bros\"|\"2014-04-02\"|\"8.00\"|\"2:45\"\n" +
                "\"stb3\"|\"the matrix\"|\"warner bros\"|\"2014-04-02\"|\"2.00\"|\"1:05\"\n" +
                "\"stb2\"|\"the matrix\"|\"warner bros\"|\"2014-05-01\"|\"4.00\"|\"1:30\"\n" +
                "\"stb3\"|\"deadpool\"|\"20th century fox\"|\"2016-11-21\"|\"4.00\"|\"1:48\"\n" +
                "\"stb2\"|\"blair witch\"|\"artisan entertainment\"|\"2015-04-01\"|\"4.00\"|\"2:16\"\n" +
                "\"stb1\"|\"the revenant\"|\"20th century fox\"|\"2016-04-01\"|\"4.00\"|\"12:36\"\n";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        String[] args = {"-l", dataFilename};

        QueryRunner.main(args);
        String stdoutStirng = baos.toString();

        assert(baos.toString().equals(expectedOutput));
        assert(dbFile.exists());
        String contents = fileToString(DataStoreFilenames.DB_FILE_NAME.getValue(), Charset.defaultCharset());
        assert(contents.equals(expectedDbFileString));
    }

    private static String fileToString(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
