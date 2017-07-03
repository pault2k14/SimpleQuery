import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

/**
 * Created by Paul.Thompson on 6/29/2017.
 */
public class DataStoreWriter {

    CSVReader reader;
    CSVWriter writer;
    File dbFile;
    File tempFile;
    String TEMP_FILE_NAME = "tempFile";
    String DB_FILE_NAME = "dbFile";
    char SEPERATOR = '|';
    char QUOTE = '"';

    public DataStoreWriter() throws Exception {

        dbFile = new File(DB_FILE_NAME);
        tempFile = new File(TEMP_FILE_NAME);

        if(!dbFile.exists()) {
            dbFile.createNewFile();
        }

        if(tempFile.exists()) {
            tempFile.delete();
            tempFile.createNewFile();
        }
    }

    public void write(DataStoreItem item) throws Exception {
        dbFile = new File(DB_FILE_NAME);
        tempFile = new File(TEMP_FILE_NAME);
        reader = new CSVReader(new FileReader(dbFile), SEPERATOR, QUOTE);
        writer = new CSVWriter(new FileWriter(tempFile), SEPERATOR, QUOTE);
        boolean written = false;

        for (Iterator<String[]> itemIterator = reader.iterator(); itemIterator.hasNext(); ) {

            item.getViewTimeString();

            String[] itemStringArray = itemIterator.next();
            DataStoreItem dbDataStoreItem = new DataStoreItem(itemStringArray[0], itemStringArray[1],
                    itemStringArray[2], itemStringArray[3], itemStringArray[4], itemStringArray[5]);

            if (item.getPk().equals(dbDataStoreItem.getPk())) {
                writer.writeNext(new String[]{item.getStb(), item.getTitle(), item.getProvider(),
                        item.getDateString(), item.getRev().toString(), item.getViewTimeString()});
                written = true;
            } else {
                writer.writeNext(itemStringArray);
             }
        }

        if(!written) {
            writer.writeNext(new String[]{item.getStb(), item.getTitle(), item.getProvider(),
                    item.getDateString(), item.getRev().toString(), item.getViewTimeString()});
        }

        writer.flush();
        writer.close();
        reader.close();

        File dbFile = new File(DB_FILE_NAME);
        File tempFile = new File(TEMP_FILE_NAME);

        if(dbFile.exists() && tempFile.exists()) {
            dbFile.delete();
            tempFile.renameTo(dbFile);
        }
    }

}
