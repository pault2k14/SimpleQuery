/**
 * Created by Paul on 7/3/2017.
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
