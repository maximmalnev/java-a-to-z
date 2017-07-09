package ru.kovladimir.orderbook.params;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParamsImplTest {

    @Test
    public void whenNotValidThenReturnFalse() {
        Params params = new ParamsImpl(new String[] {"-th", "orders.xml"});

        boolean result = params.isValid();

        assertThat(result, is(false));
    }

    @Test
    public void whenNotTwoElementsThenReturnFalse() {
        Params params = new ParamsImpl(new String[] {"orders.xml"});

        boolean result = params.isValid();

        assertThat(result, is(false));
    }

    @Test
    public void whenValidThenReturnTrue() {
        Params params = new ParamsImpl(new String[] {"-path", "orders.xml"});

        boolean result = params.isValid();

        assertThat(result, is(true));
    }

    @Test
    public void whenValidArgsAndGetFileThenReturnFile() throws NotValidArgsException {
        Params params = new ParamsImpl(new String[] {"-path", "orders.xml"});
        File file = new File("orders.xml");

        File result = params.getFile();

        assertThat(result, is(file));
    }

    @Test (expected = NotValidArgsException.class)
    public void whenNotValidArgsAndGetFileThenThrowException() throws NotValidArgsException {
        Params params = new ParamsImpl(new String[] {"-ath", "orders.xml"});

        File result = params.getFile();

    }

}