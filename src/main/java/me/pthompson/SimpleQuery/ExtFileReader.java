package me.pthompson.SimpleQuery;

import com.opencsv.CSVReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Reads the contents of a pipe delimited file into a List of DataStoreItems.
 */
public class ExtFileReader {

    public static List<DataStoreItem> read(String fileName) throws Exception {
        CSVReader csvReader = null;
        List<DataStoreItem> dataStoreItemList = new ArrayList<DataStoreItem>();

        csvReader = new CSVReader(new FileReader(fileName), '|', '"', 1);
        List<String[]> dataStoreItems = csvReader.readAll();

        for (String[] item : dataStoreItems) {
            DataStoreItem dataStoreItem = new DataStoreItem(item[0], item[1], item[2],
                    item[3], item[4], item[5]);
            dataStoreItemList.add(dataStoreItem);
        }

        return dataStoreItemList;
    }
}
