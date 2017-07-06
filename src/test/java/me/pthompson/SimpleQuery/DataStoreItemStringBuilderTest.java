package me.pthompson.SimpleQuery;

import org.junit.Test;

/**
 * Provides basic testing of the DataStoreItemStringBuilder class.
 */
public class DataStoreItemStringBuilderTest {

    @Test
    public void testBuildStringAllColumns() throws Exception {

        DataStoreItemStringBuilder dataStoreItemStringBuilder = new DataStoreItemStringBuilder(new String[]
                {"STB", "TITLE", "PROVIDER", "DATE", "REV", "VIEW_TIME"});

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        String expectedString = "stb1,the matrix,warner bros,2015-05-01,4.00,1:30";
        assert(dataStoreItemStringBuilder.buildString(dataStoreItem).equals(expectedString));
    }

    @Test
    public void testBuildStringNoColumns() throws Exception {

        DataStoreItemStringBuilder dataStoreItemStringBuilder = new DataStoreItemStringBuilder(new String[]{});

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        String expectedString = "";
        assert(dataStoreItemStringBuilder.buildString(dataStoreItem).equals(expectedString));
    }

    @Test
    public void testBuildStringDuplicateColumns() throws Exception {

        DataStoreItemStringBuilder dataStoreItemStringBuilder = new DataStoreItemStringBuilder(new String[]
                {"STB", "STB", "STB"});

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        String expectedString = "stb1,stb1,stb1";
        assert(dataStoreItemStringBuilder.buildString(dataStoreItem).equals(expectedString));
    }
}
