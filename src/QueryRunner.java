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

        DataStoreReader dataStoreReader = new DataStoreReader(new String[]{"STB", "TITLE"},
                new String[]{"DATE=2014-04-01"}, new String[]{"DATE"});
        ArrayList<DataStoreItem> dataStoreItems = dataStoreReader.query();

        for(DataStoreItem dataStoreItem : dataStoreItems) {
            System.out.println(dataStoreItem);
        }


    }
}
