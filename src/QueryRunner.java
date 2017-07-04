import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul.Thompson on 6/29/2017.
 */
public class QueryRunner {

    public static void main(String... aArgs) throws Exception {

        // --load fileName
        /*
        List<DataStoreItem> dataStoreItemList = new ExtFileReader().read("data.txt");
        DataStoreWriter dataStoreWriter = new DataStoreWriter();

        for(DataStoreItem item : dataStoreItemList) {
            dataStoreWriter.write(item);
        }
        */
        // "DATE=2014-04-01"


        DataStoreReader dataStoreReader = new DataStoreReader(
                new String[]{"DATE!=2014-04-02"}, new String[]{"TITLE", "DATE"});
        ArrayList<DataStoreItem> dataStoreItems = dataStoreReader.executeQuery();
        DataStoreItemStringBuilder dataStoreItemStringBuilder =
                new DataStoreItemStringBuilder((new String[]{"STB", "TITLE", "PROVIDER", "DATE",
                "REV", "VIEW_TIME"}));

        for(DataStoreItem dataStoreItem : dataStoreItems) {
            String line = dataStoreItemStringBuilder.buildString(dataStoreItem);
            System.out.println(line);
        }



    }
}
