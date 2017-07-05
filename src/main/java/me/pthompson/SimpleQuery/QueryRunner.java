package me.pthompson.SimpleQuery;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that loads data from pipe delimited file into database,
 * and filters user input to build queries against that database.
 */
public class QueryRunner {

    public static void main(String... args) throws Exception{
        String selectString = null;
        String filterString = null;
        String orderString = null;
        String[] selectFields = {};
        String[] filterFields = {};
        String[] orderFields = {};

        Options options = new Options();
        options.addOption("s", true, "Columns to show in query");
        options.addOption("f", true, "Query filters");
        options.addOption("o", true, "Query ordering");
        options.addOption("l", true, "Data file to load");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);

        if(cmd.hasOption("l")) {
            String fileName = cmd.getOptionValue("l");
            List<DataStoreItem> dataStoreItemList = null;

            try {
                dataStoreItemList = ExtFileReader.read(fileName);
            }
            catch(Exception e) {
                System.out.println("Unable to load data file " + fileName);
                e.printStackTrace();
                return;
            }

            DataStoreWriter dataStoreWriter = null;
            try {
                dataStoreWriter = new DataStoreWriter();

                for(DataStoreItem item : dataStoreItemList) {
                    dataStoreWriter.write(item);
                }
            }
            catch(Exception e) {
                System.out.println("Unable to write to database");
                e.printStackTrace();
                return;
            }

            System.out.println("Successfully loaded data file " + fileName);
        }

        if(cmd.hasOption("s")) {
            selectString = cmd.getOptionValue("s");
            selectFields = selectString.split(",");
        }


        if(cmd.hasOption("f")) {
            filterString = cmd.getOptionValue("f");
            filterFields = filterString.split(",");
        }

        if(cmd.hasOption("o")) {
            orderString = cmd.getOptionValue("o");
            orderFields = orderString.split(",");
        }

        if(selectFields.length >= 1) {

            DataStoreReader dataStoreReader = null;
            ArrayList<DataStoreItem> dataStoreItems = null;

            try {
                dataStoreReader = new DataStoreReader(filterFields, orderFields);
                dataStoreItems = dataStoreReader.executeQuery();
            }
            catch(Exception e) {
                System.out.println("Unable to execute query: \n" +
                        "select " + selectString + "\n" +
                        "filter " + filterString + "\n" +
                        "order " + orderString + "\n");
                e.printStackTrace();
                return;
            }

            DataStoreItemStringBuilder dataStoreItemStringBuilder = new DataStoreItemStringBuilder(selectFields);

            for(DataStoreItem dataStoreItem : dataStoreItems) {
                String line = dataStoreItemStringBuilder.buildString(dataStoreItem);
                System.out.println(line);
            }
        }
    }
}
