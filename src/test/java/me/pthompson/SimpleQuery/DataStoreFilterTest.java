package me.pthompson.SimpleQuery;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class DataStoreFilterTest {

    private DataStoreFilter dataStoreFilter;
    private ArrayList<DataStoreItem> dataStoreItems;

    @Before
    public void setup() throws Exception {

        dataStoreItems = new ArrayList<>();
        dataStoreItems.add(new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30"));
        dataStoreItems.add(new DataStoreItem("stb1", "unbreakable", "buena vista",
                "2014-04-03", "6.00", "2:05"));
        dataStoreItems.add(new DataStoreItem("stb2", "the hobbit", "warner bros",
                "2014-04-02", "8.00", "2:45"));
        dataStoreItems.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));
        dataStoreItems.add(new DataStoreItem("stb2", "the matrix", "warner bros",
                "2014-05-01", "4.00", "1:30"));
        dataStoreItems.add(new DataStoreItem("stb3", "deadpool", "20th century fox",
                "2016-11-21", "4.00", "1:48"));
        dataStoreItems.add(new DataStoreItem("stb2", "blair witch", "artisan entertainment",
                "2015-04-01", "4.00", "2:16"));
        dataStoreItems.add(new DataStoreItem("stb1", "the revenant", "20th century fox",
                "2016-04-01", "4.00", "12:36"));
    }

    @Test
    public void testLessThanOrEqual() throws Exception {

        ArrayList<DataStoreItem> matchedItems = new ArrayList<>();
        ArrayList<DataStoreItem> expectedItems = new ArrayList<>();
        expectedItems.add(new DataStoreItem("stb1", "unbreakable", "buena vista",
                "2014-04-03", "6.00", "2:05"));
        expectedItems.add(new DataStoreItem("stb2", "the hobbit", "warner bros",
                "2014-04-02", "8.00", "2:45"));
        expectedItems.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));

        dataStoreFilter = new DataStoreFilter(new String[]{"DATE:lte:2014-04-03"});

        for(DataStoreItem item : dataStoreItems) {
            if(dataStoreFilter.match(item)) {
                matchedItems.add(item);
            }
        }

        assert(matchedItems.size() == expectedItems.size());

        for(int i = 0; i < matchedItems.size(); ++i) {
            assert(matchedItems.get(i).equals(expectedItems.get(i)));
        }
    }

    @Test
    public void testGreaterThanOrEqual() throws Exception {

        ArrayList<DataStoreItem> matchedItems = new ArrayList<>();
        ArrayList<DataStoreItem> expectedItems = new ArrayList<>();
        expectedItems.add(new DataStoreItem("stb3", "deadpool", "20th century fox",
                "2016-11-21", "4.00", "1:48"));

        dataStoreFilter = new DataStoreFilter(new String[]{"DATE:gte:2016-04-03"});

        for(DataStoreItem item : dataStoreItems) {
            if(dataStoreFilter.match(item)) {
                matchedItems.add(item);
            }
        }

        assert(matchedItems.size() == expectedItems.size());

        for(int i = 0; i < matchedItems.size(); ++i) {
            assert(matchedItems.get(i).equals(expectedItems.get(i)));
        }
    }

    @Test
    public void testLessThen() throws Exception {

        ArrayList<DataStoreItem> matchedItems = new ArrayList<>();
        ArrayList<DataStoreItem> expectedItems = new ArrayList<>();
        expectedItems.add(new DataStoreItem("stb2", "the hobbit", "warner bros",
                "2014-04-02", "8.00", "2:45"));
        expectedItems.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));

        dataStoreFilter = new DataStoreFilter(new String[]{"DATE:lt:2014-04-03"});

        for(DataStoreItem item : dataStoreItems) {
            if(dataStoreFilter.match(item)) {
                matchedItems.add(item);
            }
        }

        assert(matchedItems.size() == expectedItems.size());

        for(int i = 0; i < matchedItems.size(); ++i) {
            assert(matchedItems.get(i).equals(expectedItems.get(i)));
        }
    }

    @Test
    public void testGreaterThen() throws Exception {

        ArrayList<DataStoreItem> matchedItems = new ArrayList<>();
        ArrayList<DataStoreItem> expectedItems = new ArrayList<>();
        expectedItems.add(new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30"));
        expectedItems.add(new DataStoreItem("stb2", "the matrix", "warner bros",
                "2014-05-01", "4.00", "1:30"));
        expectedItems.add(new DataStoreItem("stb3", "deadpool", "20th century fox",
                "2016-11-21", "4.00", "1:48"));
        expectedItems.add(new DataStoreItem("stb2", "blair witch", "artisan entertainment",
                "2015-04-01", "4.00", "2:16"));
        expectedItems.add(new DataStoreItem("stb1", "the revenant", "20th century fox",
                "2016-04-01", "4.00", "12:36"));

        dataStoreFilter = new DataStoreFilter(new String[]{"DATE:gt:2014-04-03"});

        for(DataStoreItem item : dataStoreItems) {
            if(dataStoreFilter.match(item)) {
                matchedItems.add(item);
            }
        }

        assert(matchedItems.size() == expectedItems.size());

        for(int i = 0; i < matchedItems.size(); ++i) {
            assert(matchedItems.get(i).equals(expectedItems.get(i)));
        }
    }

    @Test
    public void testNotEqual() throws Exception {

        ArrayList<DataStoreItem> matchedItems = new ArrayList<>();
        ArrayList<DataStoreItem> expectedItems = new ArrayList<>();
        expectedItems.add(new DataStoreItem("stb1", "unbreakable", "buena vista",
                "2014-04-03", "6.00", "2:05"));
        expectedItems.add(new DataStoreItem("stb2", "the hobbit", "warner bros",
                "2014-04-02", "8.00", "2:45"));
        expectedItems.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));

        dataStoreFilter = new DataStoreFilter(new String[]{"REV!=4.00"});

        for(DataStoreItem item : dataStoreItems) {
            if(dataStoreFilter.match(item)) {
                matchedItems.add(item);
            }
        }

        assert(matchedItems.size() == expectedItems.size());

        for(int i = 0; i < matchedItems.size(); ++i) {
            assert(matchedItems.get(i).equals(expectedItems.get(i)));
        }
    }

    @Test
    public void testEqual() throws Exception {

        ArrayList<DataStoreItem> matchedItems = new ArrayList<>();
        ArrayList<DataStoreItem> expectedItems = new ArrayList<>();
        expectedItems.add(new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30"));
        expectedItems.add(new DataStoreItem("stb3", "the matrix", "warner bros",
                "2014-04-02", "2.00", "1:05"));
        expectedItems.add(new DataStoreItem("stb2", "the matrix", "warner bros",
                "2014-05-01", "4.00", "1:30"));

        dataStoreFilter = new DataStoreFilter(new String[]{"TITLE=the matrix"});

        for(DataStoreItem item : dataStoreItems) {
            if(dataStoreFilter.match(item)) {
                matchedItems.add(item);
            }
        }

        assert(matchedItems.size() == expectedItems.size());

        for(int i = 0; i < matchedItems.size(); ++i) {
            assert(matchedItems.get(i).equals(expectedItems.get(i)));
        }
    }

    @Test
    public void testParseFilter() throws Exception {
        dataStoreFilter = new DataStoreFilter(new String[]{"TITLE=the matrix",
        "REV!=2.00", "REV:lt:2.00", "REV:gt:2.00", "REV:lte:2.00",
                "REV:gte:2.00"});

        String[] expectedParsedFilterOne = new String[]{"TITLE", "the matrix", "EQUAL"};
        String[] expectedParsedFilterTwo = new String[]{"REV", "2.00", "NOT_EQUAL"};
        String[] expectedParsedFilterThree = new String[]{"REV", "2.00", "LESS_THEN"};
        String[] expectedParsedFilterFour = new String[]{"REV", "2.00", "GREATER_THEN"};
        String[] expectedParsedFilterFive = new String[]{"REV", "2.00", "LESS_THEN_OR_EQUAL"};
        String[] expectedParsedFilterSix = new String[]{"REV", "2.00", "GREATER_THEN_OR_EQUAL"};
        String[][] expectedParsedFilters = new String[][]{expectedParsedFilterOne, expectedParsedFilterTwo,
                expectedParsedFilterThree, expectedParsedFilterFour, expectedParsedFilterFive,
                expectedParsedFilterSix};

        for(int i = 0; i < dataStoreFilter.getParsedFilters().length; ++i) {
            assert(dataStoreFilter.getParsedFilters()[i][0].equals(expectedParsedFilters[i][0]));
            assert(dataStoreFilter.getParsedFilters()[i][1].equals(expectedParsedFilters[i][1]));
            assert(dataStoreFilter.getParsedFilters()[i][2].equals(expectedParsedFilters[i][2]));
        }




    }

}
