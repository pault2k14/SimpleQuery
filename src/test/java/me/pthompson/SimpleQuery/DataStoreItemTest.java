package me.pthompson.SimpleQuery;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.fail;

/**
 * Provides basic testing of the DataStoreItem class.
 */
public class DataStoreItemTest {

    @Test
    public void testGetViewTimeString() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        assert(dataStoreItem.getViewTimeString().equals("1:30"));
    }

    @Test
    public void testGetDateString() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        assert(dataStoreItem.getDateString().equals("2015-05-01"));
    }

    @Test
    public void testSetStbBelow64Char() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setStb("stb2");
        assert(dataStoreItem.getStb().equals("stb2"));
    }

    @Test(expected = Exception.class)
    public void testSetStbAbove64Char() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setStb("0123456789012345678901234567890123456789012345678901234567890123456789");

        // Shouldn't make it this far
        fail("Exception not thorwn");
    }

    @Test
    public void testSetTitleBelow64Char() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setTitle("the hobbit");

        assert(dataStoreItem.getTitle().equals("the hobbit"));
    }

    @Test(expected = Exception.class)
    public void testSetTitleAbove64Char() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setTitle("0123456789012345678901234567890123456789012345678901234567890123456789");

        // Shouldn't make it this far
        fail("Exception not thorwn");
    }

    @Test
    public void testSetProviderBelow64Char() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setProvider("buena vista");

        assert(dataStoreItem.getProvider().equals("buena vista"));
    }

    @Test(expected = Exception.class)
    public void testSetProviderAbove64Char() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setProvider("0123456789012345678901234567890123456789012345678901234567890123456789");

        // Shouldn't make it this far
        fail("Exception not thorwn");
    }

    @Test
    public void testSetDate() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setDate("2015-05-02");

        assert(dataStoreItem.getDateString().equals("2015-05-02"));
    }

    @Test
    public void testSetRev() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setRev("3.00");

        assert(dataStoreItem.getRev().equals(new BigDecimal("3.00")));
    }

    @Test
    public void testSetViewTimeWithColonSeperation() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setViewTime("3:00");

        assert(dataStoreItem.getViewTimeString().equals("3:00"));
    }

    @Test
    public void testSetViewTimeWithDurationString() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setViewTime("PT3H00M");

        assert(dataStoreItem.getViewTimeString().equals("3:00"));
    }

    @Test
    public void testSetPk() throws Exception {

        DataStoreItem dataStoreItem = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");

        dataStoreItem.setPk("stb2", "the hobbit", "2015-05-02");

        assert(dataStoreItem.getPk().equals("stb2" + "the hobbit" + "2015-05-02"));
    }

    @Test
    public void testEquals() throws Exception {

        DataStoreItem dataStoreItemOne = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");
        DataStoreItem dataStoreItemTwo = new DataStoreItem("stb1", "the matrix", "warner bros",
                "2015-05-01", "4.00", "1:30");
        DataStoreItem dataStoreItemThree = new DataStoreItem("stb2", "the matrix 2", "buena vista",
                "2015-05-02", "5.00", "1:31");

        assert(dataStoreItemOne.equals(dataStoreItemTwo));
        assert(!dataStoreItemOne.equals(dataStoreItemThree));

    }
}
