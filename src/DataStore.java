import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.lang.*;

/**
 * Created by Paul.Thompson on 6/29/2017.
 */
public class DataStore {

    private String stb;
    private int stbMaxLength = 64;
    private String title;
    private int titleMaxLength = 64;
    private String provider;
    private int providerMaxLength = 64;
    private Date date;
    private BigDecimal rev;
    private Duration viewTime;



    public static void main(String... aArgs){
        BigDecimal doublePayment = new BigDecimal("1099.3222");
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        String s = n.format(doublePayment );
        System.out.println(s);
        NumberFormat format = NumberFormat.getCurrencyInstance();
        Number number = null;

        try {
            number = format.parse(s);
        }
        catch (ParseException e) {

        }

        System.out.println(number.toString());
    }
}
