package ru.kovladimir.parser.date;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HHDateConverterTest {

    @Test
    public void whenJanuaryThenReturnDate() {
        DateConverter converter = new HHDateConverter();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.JANUARY, 10);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        Calendar result = converter.getCalendarDate("10 января");

        assertThat(result, is(calendar));
    }

    @Test
    public void whenJuneThenReturnDate() {
        DateConverter converter = new HHDateConverter();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.JUNE, 26);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        Calendar result = converter.getCalendarDate("26 июня");

        assertThat(result, is(calendar));
    }

}