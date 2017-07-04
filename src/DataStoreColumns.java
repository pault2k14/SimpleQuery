/**
 * Created by Paul on 7/3/2017.
 */
public enum DataStoreColumns {
    STB("STB"),
    TITLE("TITLE"),
    PROVIDER("PROVIDER"),
    REV("REV"),
    DATE("DATE"),
    VIEW_TIME("VIEW_TIME");

    private String value;

    DataStoreColumns(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
