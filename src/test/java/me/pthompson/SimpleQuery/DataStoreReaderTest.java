package me.pthompson.SimpleQuery;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides basic testing fo the DataStoreReader class.
 */
public class DataStoreReaderTest {

    @Before
    public void setup() throws Exception {
        List<DataStoreItem> dataStoreItems = ExtFileReader.read("data.txt");
        DataStoreWriter dataStoreWriter = new DataStoreWriter();

        for(DataStoreItem dataStoreItem : dataStoreItems) {
            dataStoreWriter.write(dataStoreItem);
        }
    }

    @Test
    public void testExecuteQuery() throws Exception {

        String[] orderColumns = {"TITLE", "DATE", "STB"};
        String[] filterColumns = {"TITLE=the matrix"};
        DataStoreReader dataStoreReader = new DataStoreReader(filterColumns, orderColumns);
        ArrayList<DataStoreItem> dataStoreItems = dataStoreReader.executeQuery();

        ArrayList<DataStoreItem> expectedDataStoreItems = new ArrayList<>();
        expectedDataStoreItems.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));
        expectedDataStoreItems.add(new DataStoreItem("stb2", "the matrix", "warner bros",
                "2014-05-01", "4.00", "1:30"));
        expectedDataStoreItems.add(new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30"));

        for(int i = 0; i < dataStoreItems.size(); ++i) {
            assert(dataStoreItems.get(i).equals(expectedDataStoreItems.get(i)));
        }


    }
}
