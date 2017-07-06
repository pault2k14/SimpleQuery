package me.pthompson.SimpleQuery;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Performs basic testing of the DataItemSort class.
 */
public class DataItemSortTest {

    @Test
    public void testCompareSortNoFields() throws Exception {

        DataStoreItemSort dataStoreItemSort = new DataStoreItemSort(new String[]{});

        ArrayList<DataStoreItem> dataStoreItemsOrdered = new ArrayList<>();
        dataStoreItemsOrdered.add(new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb1", "unbreakable", "buena vista",
                "2014-04-03", "6.00", "2:05"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb2", "the hobbit", "warner bros",
                "2014-04-02", "8.00", "2:45"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb2", "the matrix", "warner bros",
                "2014-05-01", "4.00", "1:30"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb3", "deadpool", "20th century fox",
                "2016-11-21", "4.00", "1:48"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb2", "blair witch", "artisan entertainment",
                "2015-04-01", "4.00", "2:16"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb1", "the revenant", "20th century fox",
                "2016-04-01", "4.00", "12:36"));

        ArrayList<DataStoreItem> dataStoreItemsExpectedOrder = new ArrayList<>();
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb1", "unbreakable", "buena vista",
                "2014-04-03", "6.00", "2:05"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb2", "the hobbit", "warner bros",
                "2014-04-02", "8.00", "2:45"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb2", "the matrix", "warner bros",
                "2014-05-01", "4.00", "1:30"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb3", "deadpool", "20th century fox",
                "2016-11-21", "4.00", "1:48"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb2", "blair witch", "artisan entertainment",
                "2015-04-01", "4.00", "2:16"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb1", "the revenant", "20th century fox",
                "2016-04-01", "4.00", "12:36"));

        Collections.sort(dataStoreItemsOrdered, dataStoreItemSort);

        for(int i = 0; i < dataStoreItemsOrdered.size(); ++i) {
            assert(dataStoreItemsOrdered.get(i).equals(dataStoreItemsExpectedOrder.get(i)));
        }

    }

    @Test
    public void testCompareSortAllFields() throws Exception {

        DataStoreItemSort dataStoreItemSort = new DataStoreItemSort(new String[]{
                "TITLE", "REV", "VIEW_TIME", "PROVIDER", "DATE", "STB"
        });

        ArrayList<DataStoreItem> dataStoreItemsOrdered = new ArrayList<>();
        dataStoreItemsOrdered.add(new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb1", "unbreakable", "buena vista",
                "2014-04-03", "6.00", "2:05"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb2", "the hobbit", "warner bros",
                "2014-04-02", "8.00", "2:45"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb2", "the matrix", "warner bros",
                "2014-05-01", "4.00", "1:30"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb3", "deadpool", "20th century fox",
                "2016-11-21", "4.00", "1:48"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb2", "blair witch", "artisan entertainment",
                "2015-04-01", "4.00", "2:16"));
        dataStoreItemsOrdered.add(new DataStoreItem("stb1", "the revenant", "20th century fox",
                "2016-04-01", "4.00", "12:36"));

        ArrayList<DataStoreItem> dataStoreItemsExpectedOrder = new ArrayList<>();
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb2", "blair witch", "artisan entertainment",
                "2015-04-01", "4.00", "2:16"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb3", "deadpool", "20th century fox",
                "2016-11-21", "4.00", "1:48"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb2", "the hobbit", "warner bros",
                "2014-04-02", "8.00", "2:45"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb2", "the matrix", "warner bros",
                "2014-05-01", "4.00", "1:30"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb1", "the revenant", "20th century fox",
                "2016-04-01", "4.00", "12:36"));
        dataStoreItemsExpectedOrder.add(new DataStoreItem("stb1", "unbreakable", "buena vista",
                "2014-04-03", "6.00", "2:05"));

        Collections.sort(dataStoreItemsOrdered, dataStoreItemSort);

        for(int i = 0; i < dataStoreItemsOrdered.size(); ++i) {
            assert(dataStoreItemsOrdered.get(i).equals(dataStoreItemsExpectedOrder.get(i)));
        }

    }
}
