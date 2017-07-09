package ru.kovladimir.finder.validator;

import org.junit.Test;
import ru.kovladimir.finder.ParamsImpl;
import ru.kovladimir.finder.Params;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ParamsImplTest {

    @Test
    public void getStandardError() {
        Params params = new ParamsImpl(new String[0]);

        String error = params.getError();

        assertEquals(error, "There's no error.");
    }

    @Test
    public void whenNotValidLengthThenReturnFalse() {
        Params params = new ParamsImpl(new String[]{"1", "2"});

        boolean valid = params.isValid();

        assertFalse(valid);
    }

    @Test
    public void getLengthError() {
        Params params = new ParamsImpl(new String[]{"1", "2"});
        params.isValid();

        String error = params.getError();

        assertEquals(error, "The quantity of arguments are not 7.");
    }

    @Test
    public void whenNotValidDKeyThenReturnFalse() {
        Params params = new ParamsImpl(new String[]{"fail", "d:", "-n", "asd.txt", "-m", "-o", "d:\\log.txt"});

        boolean valid = params.isValid();

        assertFalse(valid);
    }

    @Test
    public void getDError() {
        Params params = new ParamsImpl(new String[]{"fail", "d:", "-n", "asd.txt", "-m", "-o", "d:\\log.txt"});
        params.isValid();

        String error = params.getError();

        assertEquals(error, "There is no key '-d'.");
    }

    @Test
    public void whenNotValidNKeyThenReturnFalse() {
        Params params = new ParamsImpl(new String[]{"fail", "d:", "-n", "asd.txt", "-m", "-o", "d:\\log.txt"});

        boolean valid = params.isValid();

        assertFalse(valid);
    }

    @Test
    public void getNError() {
        Params params = new ParamsImpl(new String[]{"-d", "d:", "fail", "asd.txt", "-m", "-o", "d:\\log.txt"});
        params.isValid();

        String error = params.getError();

        assertEquals(error, "There is no key '-n'.");
    }

    @Test
    public void whenNotValidMFRKeyThenReturnFalse() {
        Params params = new ParamsImpl(new String[]{"fail", "d:", "-n", "asd.txt", "-m", "-o", "d:\\log.txt"});

        boolean valid = params.isValid();

        assertFalse(valid);
    }

    @Test
    public void getMFRError() {
        Params params = new ParamsImpl(new String[]{"-d", "d:", "-n", "asd.txt", "fail", "-o", "d:\\log.txt"});
        params.isValid();

        String error = params.getError();

        assertEquals(error, "There is no key '-m', '-f' or '-r'.");
    }

    @Test
    public void whenNotValidOeyThenReturnFalse() {
        Params params = new ParamsImpl(new String[]{"-d", "d:", "-n", "asd.txt", "-m", "fail", "d:\\log.txt"});

        boolean valid = params.isValid();

        assertFalse(valid);
    }

    @Test
    public void getOError() {
        Params params = new ParamsImpl(new String[]{"-d", "d:", "-n", "asd.txt", "-m", "fail", "d:\\log.txt"});
        params.isValid();

        String error = params.getError();

        assertEquals(error, "There is no key '-o'.");
    }
}