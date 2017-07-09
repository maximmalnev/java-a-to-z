package ru.kovladimir.parser.date;

import java.util.Calendar;

/**
 * String-Calendar Converter.
 */
public interface DateConverter {

    /**
     * Covert date from String to Calendar.
     */
    Calendar getCalendarDate(String s);

}
