package me.pthompson.SimpleQuery;

/**
 * ENUMS for filenames used in this project.
 */
public enum DataStoreFilenames {

    DB_FILE_NAME("dbFile"),
    TEMP_FILE_NAME("tempFile");

    private String value;

    DataStoreFilenames(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
