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

    private String[] filters;
    private String[][] parsedFilters;
    private String LESS_THEN_OR_EQUAL = "LESS_THEN_OR_EQUAL";
    private String GREATER_THEN_OR_EQUAL = "GREATER_THEN_OR_EQUAL";
    private String LESS_THEN = "LESS_THEN";
    private String GREATER_THEN = "GREATER_THEN";
    private String NOT_EQUAL = "NOT_EQUAL";
    private String EQUAL = "EQUAL";

    public DataStoreFilter(String[] filters) throws Exception {

        this.filters = filters;
        this.parsedFilters = new String[this.filters.length][3];

        for(int i = 0; i < this.filters.length; ++i) {
            this.parsedFilters[i] = parseFilter(this.filters[i]);
        }

    }

    public boolean match(DataStoreItem dataStoreItem) throws Exception {

        for(int i = 0; i < parsedFilters.length; ++i) {

            if(parsedFilters[i][2].equals(LESS_THEN_OR_EQUAL)) {
                 if(!lessThenOrEqual(dataStoreItem, parsedFilters[i][0], parsedFilters[i][1])) return false;
            }
            else if(parsedFilters[i][2].equals(GREATER_THEN_OR_EQUAL)) {
                if(!greaterThenOrEqual(dataStoreItem, parsedFilters[i][0], parsedFilters[i][1])) return false;
            }
            else if(parsedFilters[i][2].equals(LESS_THEN)) {
                if(!lessThen(dataStoreItem, parsedFilters[i][0], parsedFilters[i][1])) return false;
            }
            else if(parsedFilters[i][2].equals(GREATER_THEN)) {
                if(!greaterThen(dataStoreItem, parsedFilters[i][0], parsedFilters[i][1])) return false;
            }
            else if(parsedFilters[i][2].equals(NOT_EQUAL)) {
                if(!notEqual(dataStoreItem, parsedFilters[i][0], parsedFilters[i][1])) return false;
            }
            else if(parsedFilters[i][2].equals(EQUAL)) {
                if(!equal(dataStoreItem, parsedFilters[i][0], parsedFilters[i][1])) return false;
            }
        }

        return true;
    }

    private boolean lessThenOrEqual(DataStoreItem dataStoreItem, String field, String value) throws Exception {
        if(field.equals(DataStoreColumns.STB.getValue())) {
            if(dataStoreItem.getStb().compareTo(value) <= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.TITLE.getValue())) {
            if(dataStoreItem.getTitle().compareTo(value) <= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.PROVIDER.getValue())) {
            if(dataStoreItem.getProvider().compareTo(value) <= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.DATE.getValue())) {
            if(dataStoreItem.getDate().compareTo(createDateFilter(value)) <= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.REV.getValue())) {
            if(dataStoreItem.getRev().compareTo(new BigDecimal(value)) <= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.VIEW_TIME.getValue())) {
            if(dataStoreItem.getViewTime().compareTo(createViewTimeFilter(value)) <= 0) return true;
            return false;
        }
        else {
            throw new Exception("Invalid filter field name: " + field);
        }
    }

    private boolean greaterThenOrEqual(DataStoreItem dataStoreItem, String field, String value) throws Exception {
        if(field.equals(DataStoreColumns.STB.getValue())) {
            if(dataStoreItem.getStb().compareTo(value) >= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.TITLE.getValue())) {
            if(dataStoreItem.getTitle().compareTo(value) >= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.PROVIDER.getValue())) {
            if(dataStoreItem.getProvider().compareTo(value) >= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.DATE.getValue())) {
            if(dataStoreItem.getDate().compareTo(createDateFilter(value)) >= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.REV.getValue())) {
            if(dataStoreItem.getRev().compareTo(new BigDecimal(value)) >= 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.VIEW_TIME.getValue())) {
            if(dataStoreItem.getViewTime().compareTo(createViewTimeFilter(value)) >= 0) return true;
            return false;
        }
        else {
            throw new Exception("Invalid filter field name: " + field);
        }
    }

    private boolean lessThen(DataStoreItem dataStoreItem, String field, String value) throws Exception {
        if(field.equals(DataStoreColumns.STB.getValue())) {
            if(dataStoreItem.getStb().compareTo(value) < 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.TITLE.getValue())) {
            if(dataStoreItem.getTitle().compareTo(value) < 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.PROVIDER.getValue())) {
            if(dataStoreItem.getProvider().compareTo(value) < 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.DATE.getValue())) {
            if(dataStoreItem.getDate().compareTo(createDateFilter(value)) < 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.REV.getValue())) {
            if(dataStoreItem.getRev().compareTo(new BigDecimal(value)) < 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.VIEW_TIME.getValue())) {
            if(dataStoreItem.getViewTime().compareTo(createViewTimeFilter(value)) < 0) return true;
            return false;
        }
        else {
            throw new Exception("Invalid filter field name: " + field);
        }
    }

    private boolean greaterThen(DataStoreItem dataStoreItem, String field, String value) throws Exception {
        if(field.equals(DataStoreColumns.STB.getValue())) {
            if(dataStoreItem.getStb().compareTo(value) > 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.TITLE.getValue())) {
            if(dataStoreItem.getTitle().compareTo(value) > 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.PROVIDER.getValue())) {
            if(dataStoreItem.getProvider().compareTo(value) > 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.DATE.getValue())) {
            if(dataStoreItem.getDate().compareTo(createDateFilter(value)) > 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.REV.getValue())) {
            if(dataStoreItem.getRev().compareTo(new BigDecimal(value)) > 0) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.VIEW_TIME.getValue())) {
            if(dataStoreItem.getViewTime().compareTo(createViewTimeFilter(value)) > 0) return true;
            return false;
        }
        else {
            throw new Exception("Invalid filter field name: " + field);
        }
    }

    private boolean notEqual(DataStoreItem dataStoreItem, String field, String value) throws Exception {
        if(field.equals(DataStoreColumns.STB.getValue())) {
            if(!dataStoreItem.getStb().equals(value)) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.TITLE.getValue())) {
            if(!dataStoreItem.getTitle().equals(value)) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.PROVIDER.getValue())) {
            if(!dataStoreItem.getProvider().equals(value)) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.DATE.getValue())) {
            if(!dataStoreItem.getDate().equals(createDateFilter(value))) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.REV.getValue())) {
            if(!dataStoreItem.getRev().equals(new BigDecimal(value))) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.VIEW_TIME.getValue())) {
            if(!dataStoreItem.getViewTime().equals(createViewTimeFilter(value))) return true;
            return false;
        }
        else {
            throw new Exception("Invalid filter field name: " + field);
        }
    }

    private boolean equal(DataStoreItem dataStoreItem, String field, String value) throws Exception {

        if(field.equals(DataStoreColumns.STB.getValue())) {
            if(dataStoreItem.getStb().equals(value)) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.TITLE.getValue())) {
            if(dataStoreItem.getTitle().equals(value)) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.PROVIDER.getValue())) {
            if(dataStoreItem.getProvider().equals(value)) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.DATE.getValue())) {
            if(dataStoreItem.getDate().equals(createDateFilter(value))) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.REV.getValue())) {
            if(dataStoreItem.getRev().equals(new BigDecimal(value))) return true;
            return false;
        }
        else if(field.equals(DataStoreColumns.VIEW_TIME.getValue())) {
            if(dataStoreItem.getViewTime().equals(createViewTimeFilter(value))) return true;
            return false;
        }
        else {
            throw new Exception("Invalid filter field name: " + field);
        }
    }

    private Duration createViewTimeFilter(String viewTimeString) throws Exception {
        String[] viewTimeFields = viewTimeString.split(":");
        Duration viewTimeDuration = null;

        try {
            viewTimeDuration = Duration.parse(String.format("PT%sH%sM", viewTimeFields[0], viewTimeFields[1]));
        }
        catch (DateTimeParseException e) {
            throw new Exception("Unable to parse VIEW_TIME string: " + viewTimeString);
        }

        return viewTimeDuration;
    }

    private Date createDateFilter(String dateString) throws Exception {
        SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = sdfm.parse(dateString);
        } catch (ParseException e) {
            throw new Exception("Unable to parse DATE string: " + dateString);
        }

        return date;
    }

    private String[] parseFilter(String filter) throws Exception {

        String[] splitFields;

        if(filter.contains("<=")){
            splitFields = filter.split("<=");
            return new String[]{splitFields[0], splitFields[1], LESS_THEN_OR_EQUAL};
        }
        else if(filter.contains(">=")){
            splitFields = filter.split(">=");
            return new String[]{splitFields[0], splitFields[1], GREATER_THEN_OR_EQUAL};
        }
        else if(filter.contains("<")){
            splitFields = filter.split("<");
            return new String[]{splitFields[0], splitFields[1], LESS_THEN};
        }
        else if(filter.contains(">")){
            splitFields = filter.split(">");
            return new String[]{splitFields[0], splitFields[1], GREATER_THEN};
        }
        else if(filter.contains("!=")){
            splitFields = filter.split("!=");
            return new String[]{splitFields[0], splitFields[1], NOT_EQUAL};
        }
        else if(filter.contains("=")){
            splitFields = filter.split("=");
            return new String[]{splitFields[0], splitFields[1], EQUAL};
        }
        else {
            throw new Exception("Invalid filter operator: " + filter);
        }
    }
}
