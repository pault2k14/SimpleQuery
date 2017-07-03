import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Created by Paul on 7/3/2017.
 */
public class DataStoreFilter {

    private boolean isStbfiltered = false;
    private String stbFilter;
    private boolean isTitleFiltered = false;
    private String titleFilter;
    private boolean isProviderFiltered = false;
    private String providerFilter;
    private boolean isDateFiltered = false;
    private Date dateFilter;
    private boolean isRevFiltered = false;
    private BigDecimal revFilter;
    private boolean isViewTimeFiltered = false;
    private Duration viewTimeFilter;
    private String[] filters;

    public DataStoreFilter(String[] filters) throws Exception {

        this.filters = filters;

        for(int i = 0; i < filters.length; ++i) {
            String[] splitFiltersFields = filters[i].split("=");
            setFilter(splitFiltersFields[0], splitFiltersFields[1]);
        }

    }

    private void setFilter(String field, String value) throws Exception {

        if(field.toUpperCase().equals("STB")) {
            isStbfiltered = true;
            stbFilter = value;
        }

        else if(field.toUpperCase().equals("TITLE")) {
            isTitleFiltered = true;
            titleFilter = value;
        }

        else if(field.toUpperCase().equals("PROVIDER")) {
            isProviderFiltered = true;
            providerFilter = value;
        }

        else if(field.toUpperCase().equals("DATE")) {
            isDateFiltered = true;
            SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateFilter = sdfm.parse(value);
            } catch (ParseException e) {
                throw new Exception("Unable to parse DATE string: " + value);
            }
        }

        else if(field.toUpperCase().equals("REV")) {
            isRevFiltered = true;
            revFilter = new BigDecimal(value);
        }

        else if(field.toUpperCase().equals("VIEW_TIME")) {
            isViewTimeFiltered = true;
            String[] viewTimeFields = value.split(":");
            try {
                viewTimeFilter = Duration.parse(String.format("PT%sH%sM", viewTimeFields[0], viewTimeFields[1]));
            }
            catch (DateTimeParseException e) {
                throw new Exception("Unable to parse VIEW_TIME string: " + value);
            }
        }

        else {
            throw new Exception("Illegal filter field specified! " + field);
        }
    }

    public boolean match(DataStoreItem dataStoreItem) {

        if(isStbfiltered) {
            if(!dataStoreItem.getStb().equals(stbFilter)) return false;
        }

        if(isTitleFiltered) {
            if(!dataStoreItem.getTitle().equals(titleFilter)) return false;
        }

        if(isProviderFiltered) {
            if(!dataStoreItem.getProvider().equals(providerFilter)) return false;
        }

        if(isDateFiltered) {
            if(!dataStoreItem.getDate().equals(dateFilter)) return false;
        }

        if(isRevFiltered) {
            if(!dataStoreItem.getRev().equals(revFilter)) return false;
        }

        if(isViewTimeFiltered) {
            if(!dataStoreItem.getViewTime().equals(viewTimeFilter)) return false;
        }

        return true;
    }

}
