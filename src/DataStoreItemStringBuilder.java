/**
 * Created by Paul on 7/4/2017.
 */
public class DataStoreItemStringBuilder {

    private String[] selectColumns;

    DataStoreItemStringBuilder(String[] selectColumns) {
        this.selectColumns = selectColumns;
    }

    public String buildString(DataStoreItem item) {

        String itemString = "";

        for(int i = 0; i < selectColumns.length; ++i) {
            if(selectColumns[i].equals(DataStoreColumns.STB.getValue())) {
                itemString += item.getStb();
            }
            else if(selectColumns[i].equals(DataStoreColumns.TITLE.getValue())) {
                itemString += item.getTitle();
            }
            else if(selectColumns[i].equals(DataStoreColumns.PROVIDER.getValue())) {
                itemString += item.getProvider();
            }
            else if(selectColumns[i].equals(DataStoreColumns.DATE.getValue())) {
                itemString += item.getDateString();
            }
            else if(selectColumns[i].equals(DataStoreColumns.REV.getValue())) {
                itemString += item.getRev();
            }
            else if(selectColumns[i].equals(DataStoreColumns.VIEW_TIME.getValue())) {
                itemString += item.getViewTimeString();
            }

            if(i != selectColumns.length - 1) {
                itemString += ",";
            }
        }

        return itemString;
    }

}
