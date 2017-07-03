import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Paul.Thompson on 6/29/2017.
 */
public class DataStoreReader {

    ArrayList<DataStoreItem> dataStoreItems;
    CSVReader reader;
    File dbFile;
    String DB_FILE_NAME = "dbFile";
    char SEPERATOR = '|';
    char QUOTE = '"';
    String[] select;
    String[] filters;
    String[] order;
    DataStoreFilter dataStoreFilter;

    public DataStoreReader(String[] select, String[] filters, String[] order) throws Exception {
        dbFile = new File(DB_FILE_NAME);
        reader = new CSVReader(new FileReader(dbFile), SEPERATOR, QUOTE);
        dataStoreItems = new ArrayList<>();

        if(!dbFile.exists()) {
            dbFile.createNewFile();
        }

        this.select = select;
        this.filters = filters;
        this.order = order;
        this.dataStoreFilter = new DataStoreFilter(filters);
    }

    public ArrayList<DataStoreItem> query() throws Exception{

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

        return dataStoreItems;
    }

    public ArrayList<DataStoreItem> orderByStb() {

        return null;
    }

    public ArrayList<DataStoreItem> orderByTitle() {

        return null;
    }

    public ArrayList<DataStoreItem> orderByProvider() {

        return null;
    }

    public ArrayList<DataStoreItem> orderByDate() {

        return null;
    }

    public ArrayList<DataStoreItem> orderByRev() {

        return null;
    }

    public ArrayList<DataStoreItem> orderByViewTime() {

        return null;
    }

    public ArrayList<DataStoreItem> select(String[] select) {

        return null;
    }
}
