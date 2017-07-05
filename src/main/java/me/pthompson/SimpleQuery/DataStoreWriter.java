package me.pthompson.SimpleQuery;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

/**
 * Writes DataStoreItems to the DataStore database.
 */
public class DataStoreWriter {

    private CSVReader reader;
    private CSVWriter writer;
    private File dbFile;
    private File tempFile;
    private char SEPERATOR = '|';
    private char QUOTE = '"';

    public DataStoreWriter() throws Exception {

        dbFile = new File(DataStoreFilenames.DB_FILE_NAME.getValue());
        tempFile = new File(DataStoreFilenames.TEMP_FILE_NAME.getValue());

        if(!dbFile.exists()) {
            dbFile.createNewFile();
        }

        if(tempFile.exists()) {
            tempFile.delete();
            tempFile.createNewFile();
        }
    }

    public void write(DataStoreItem item) throws Exception {
        dbFile = new File(DataStoreFilenames.DB_FILE_NAME.getValue());
        tempFile = new File(DataStoreFilenames.TEMP_FILE_NAME.getValue());
        reader = new CSVReader(new FileReader(dbFile), SEPERATOR, QUOTE);
        writer = new CSVWriter(new FileWriter(tempFile), SEPERATOR, QUOTE);
        boolean written = false;

        for (Iterator<String[]> itemIterator = reader.iterator(); itemIterator.hasNext(); ) {

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

        File dbFile = new File(DataStoreFilenames.DB_FILE_NAME.getValue());
        File tempFile = new File(DataStoreFilenames.TEMP_FILE_NAME.getValue());

        if(dbFile.exists() && tempFile.exists()) {
            dbFile.delete();
            tempFile.renameTo(dbFile);
        }
    }

}
