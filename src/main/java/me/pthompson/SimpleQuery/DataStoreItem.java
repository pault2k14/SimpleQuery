package me.pthompson.SimpleQuery;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.lang.*;

/**
 * Class representing the simple database columns.
 */
public class DataStoreItem {

    private String stb;
    private int STB_MAX_LENGTH = 64;
    private String title;
    private int TITLE_MAX_LENGTH = 64;
    private String provider;
    private int PROVIDER_MAX_LENGTH = 64;
    private Date date;
    private BigDecimal rev;
    private Duration viewTime;
    private String pk;
    private SimpleDateFormat sdfm;

    public DataStoreItem() {

    }

    public DataStoreItem(String stb, String title, String provider,
                         String date, String rev, String viewTime) throws Exception {

        setStb(stb);
        setTitle(title);
        setProvider(provider);
        setDate(date);
        setViewTime(viewTime);
        setRev(rev);
        setPk(stb, title, date);
    }

    public String getStb() {
        return this.stb;
    }

    public void setStb(String stb) throws Exception {
        this.stb = stb;
        if (stb.length() > STB_MAX_LENGTH) {
            throw new Exception("STB exceeds " + STB_MAX_LENGTH + " characters: " + stb);
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) throws Exception {
        this.title = title;
        if (title.length() > TITLE_MAX_LENGTH) {
            throw new Exception("TITLE exceeds " + TITLE_MAX_LENGTH + " characters: " + title);
        }
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) throws Exception {
        this.provider = provider;
        if (provider.length() > PROVIDER_MAX_LENGTH) {
            throw new Exception("PROVIDER string exceeds " + PROVIDER_MAX_LENGTH + " characters: " + provider);
        }
    }

    public Date getDate() {
        return this.date;
    }

    public String getDateString() {
        return sdfm.format(this.date);
    }

    public void setDate(String date) throws Exception {
        sdfm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = sdfm.parse(date);
        } catch (ParseException e) {
            throw new Exception("Unable to parse DATE string: " + date);
        }
    }

    public BigDecimal getRev() {
        return this.rev;
    }

    public void setRev(String rev) {
        this.rev = new BigDecimal(rev);
    }

    public Duration getViewTime() {
        return this.viewTime;
    }

    public String getViewTimeString() {

        int hrStr = Integer.parseInt(Long.toString(viewTime.getSeconds() / 3600));
        int rem = Integer.parseInt(Long.toString(viewTime.getSeconds() % 3600));
        int mn = rem/60;
        String mnStr = (mn<10 ? "0" : "") + mn;

        return hrStr + ":" + mnStr;
    }

    public void setViewTime(String viewTime) throws Exception {
        String[] viewTimeFields = viewTime.split(":");

        if(viewTimeFields.length == 2) {
            try {
                this.viewTime = Duration.parse(String.format("PT%sH%sM", viewTimeFields[0], viewTimeFields[1]));
            }
            catch (DateTimeParseException e) {
                throw new Exception("Unable to parse VIEW_TIME string: " + viewTime);
            }
        }
        else {
            try {
                this.viewTime = Duration.parse(viewTime);
            }
            catch (DateTimeParseException e) {
                throw new Exception("Unable to parse VIEW_TIME string: " + viewTime);
            }
        }
    }

    public String getPk() {
        return this.pk;
    }

    public void setPk(String stb, String title, String date) {
        this.pk = stb + title + date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof DataStoreItem)) {
            return false;
        }

        DataStoreItem item = (DataStoreItem) o;

        return Objects.equals(stb, item.stb) &&
                Objects.equals(title, item.title) &&
                Objects.equals(provider, item.provider) &&
                Objects.equals(date, item.date) &&
                Objects.equals(rev, item.rev) &&
                Objects.equals(viewTime, item.viewTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stb, title, provider, date, rev, viewTime);
    }


    @Override
    public String toString() {
        return  "STB " + stb + "\n" +
                "TITLE " + title + "\n" +
                "PROVIDER " + provider + "\n" +
                "DATE " + getDateString() + "\n" +
                "REV " + rev + "\n" +
                "VIEW_TIME " + getViewTimeString() + "\n";
    }
}
