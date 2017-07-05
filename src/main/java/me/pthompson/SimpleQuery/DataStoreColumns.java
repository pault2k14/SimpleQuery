package me.pthompson.SimpleQuery;

/**
 * ENUMS for the DataStore column names.
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
