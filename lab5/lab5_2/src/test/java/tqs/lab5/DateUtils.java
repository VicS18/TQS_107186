package tqs.lab5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date parseYearMonthDay(String date) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
