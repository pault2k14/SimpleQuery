import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.ICSVParser;
import com.opencsv.bean.*;
// import org.apache.commons.csv.CSVFormat;
// import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Paul.Thompson on 6/29/2017.
 */
public class ExtFileReader {

    public List<DataStoreItem> read(String fileName) {
        CSVReader csvReader = null;
        List<DataStoreItem> dataStoreItemList = new ArrayList<>();

        try {
            csvReader = new CSVReader(new FileReader(fileName), '|', '"', 1);
            List<String[]> dataStoreItems = csvReader.readAll();

            for (String[] item : dataStoreItems) {
                DataStoreItem dataStoreItem = new DataStoreItem(item[0], item[1], item[2],
                        item[3], item[4], item[5]);
                dataStoreItemList.add(dataStoreItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataStoreItemList;
    }
}
