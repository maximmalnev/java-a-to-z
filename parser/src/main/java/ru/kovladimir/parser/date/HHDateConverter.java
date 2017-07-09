package ru.kovladimir.parser.date;

import java.time.Year;
import java.util.Calendar;

/**
 * String-Calendar Converter from hh.ru.
 */
public class HHDateConverter implements DateConverter {

    /**
     * {@inheritDoc}
     * @param dateString
     * @return
     */
    @SuppressWarnings("MagicConstant")
    @Override
    public Calendar getCalendarDate(String dateString) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                getYear(),
                getMonth(dateString),
                getDay(dateString)
        );
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        return calendar;
    }

    /**
     * Get current year.
     * @return int.
     */
    private int getYear() {
        return Year.now().getValue();
    }

    /**
     * Get month from string.
     * @param dateString String.
     * @return int.
     */
    private int getMonth(String dateString) {
        String monthString = dateString.split("[\\u00A0\\s]+")[1];


        int month = 0;
        switch (monthString.toLowerCase()) {
            case "января":
                // month is already 0
                break;
            case "февраля":
                month = Calendar.FEBRUARY;
                break;
            case "марта":
                month = Calendar.MARCH;
                break;
            case "апреля":
                month = Calendar.APRIL;
                break;
            case "мая":
                month = Calendar.MAY;
                break;
            case "июня":
                month = Calendar.JUNE;
                break;
            case "июля":
                month = Calendar.JULY;
                break;
            case "августа":
                month = Calendar.AUGUST;
                break;
            case "сентября":
                month = Calendar.SEPTEMBER;
                break;
            case "октября":
                month = Calendar.OCTOBER;
                break;
            case "ноября":
                month = Calendar.NOVEMBER;
                break;
            case "декабря":
                month = Calendar.DECEMBER;
                break;
        }
        return month;
    }

    /**
     * Get day from string.
     * @param dateString String.
     * @return int.
     */
    private int getDay(String dateString) {
        String dayString = dateString.split("[\\u00A0\\s]+")[0];
        return Integer.parseInt(dayString);
    }


}
