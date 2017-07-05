package me.pthompson.SimpleQuery;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

/**
 * Custom comparison of DataStoreItem
 */
public class DataStoreItemSort implements Comparator<DataStoreItem> {

    private String[] orderColumns;

    public DataStoreItemSort(String[] orderColumns) {
        this.orderColumns = orderColumns;
    }

    public int compare(DataStoreItem a, DataStoreItem b) {

        CompareToBuilder compareToBuilder = new CompareToBuilder();

        for(int i = 0; i < orderColumns.length; ++i) {

            if(this.orderColumns[i].equals(DataStoreColumns.STB.getValue())) {
                compareToBuilder.append(a.getStb(), b.getStb());
            }
            else if(this.orderColumns[i].equals(DataStoreColumns.TITLE.getValue())) {
                compareToBuilder.append(a.getTitle(), b.getTitle());
            }
            else if(this.orderColumns[i].equals(DataStoreColumns.PROVIDER.getValue())) {
                compareToBuilder.append(a.getProvider(), b.getProvider());
            }
            else if(this.orderColumns[i].equals(DataStoreColumns.DATE.getValue())) {
                compareToBuilder.append(a.getDate(), b.getDate());
            }
            else if(this.orderColumns[i].equals(DataStoreColumns.REV.getValue())) {
                compareToBuilder.append(a.getRev(), b.getRev());
            }
            else if(this.orderColumns[i].equals(DataStoreColumns.VIEW_TIME.getValue())) {
                compareToBuilder.append(a.getViewTime(), b.getViewTime());
            }
        }

        return compareToBuilder.toComparison();
    }

}
