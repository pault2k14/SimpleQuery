import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Paul.Thompson on 6/29/2017.
 */
public class DataStoreReader {

    ArrayList<DataStoreItem> dataStoreItems;
    CSVReader reader;
    File dbFile;
    char SEPERATOR = '|';
    char QUOTE = '"';
    String[] filterColumns;
    String[] orderColumns;
    DataStoreFilter dataStoreFilter;
    DataStoreItemSort dataStoreItemSort;

    public DataStoreReader(String[] filterColumns, String[] orderColumns) throws Exception {
        dbFile = new File(DataStoreFilenames.DB_FILE_NAME.getValue());
        reader = new CSVReader(new FileReader(dbFile), SEPERATOR, QUOTE);
        dataStoreItems = new ArrayList<>();

        if(!dbFile.exists()) {
            dbFile.createNewFile();
        }

        this.filterColumns = filterColumns;
        this.orderColumns = orderColumns;
        this.dataStoreFilter = new DataStoreFilter(this.filterColumns);
        this.dataStoreItemSort = new DataStoreItemSort(this.orderColumns);
    }

    public ArrayList<DataStoreItem> executeQuery() throws Exception{

        Iterator<String[]> it = reader.iterator();
        DataStoreItem dataStoreItem = null;

        while(it.hasNext()) {
            String[] item = it.next();
            dataStoreItem = new DataStoreItem(item[0], item[1], item[2], item[3],
                    item[4], item[5]);

            if(dataStoreFilter.match(dataStoreItem)) {
                dataStoreItems.add(dataStoreItem);
            }
        }

        Collections.sort(dataStoreItems, dataStoreItemSort);
        return dataStoreItems;
    }
}
