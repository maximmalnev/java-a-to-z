package ru.kovladimir.generator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SimpleGeneratorTest {

    @Test
    public void whenNullDataThenReturnTemplate() throws ParamNotFoundException {
        Generator generator = new SimpleGenerator();
        String template = "Hello, ${name}.";
        Pair[] data = null;

        String result = generator.generate(template, data);

        assertEquals(template, result);
    }

    @Test
    public void whenNullTemplateThenReturnNull() throws ParamNotFoundException {
        Generator generator = new SimpleGenerator();
        String template = null;
        Pair[] data = new Pair[]{new Pair("name", "Nick"), new Pair("city", "Moscow")};

        String result = generator.generate(template, data);

        assertNull(result);
    }


    @Test
    public void whenHasDataThenReplaceParams() throws ParamNotFoundException {
        Generator generator = new SimpleGenerator();
        String template = "Hello, ${name}. You live in ${city}. Bye, ${name}!";
        Pair[] data = new Pair[]{new Pair("name", "Nick"), new Pair("city", "Moscow")};

        String templateAfterReplacing = "Hello, Nick. You live in Moscow. Bye, Nick!";

        String result = generator.generate(template, data);

        assertEquals(templateAfterReplacing, result);
    }
}