package ru.kovladimir.iterators;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class BasicConverterTest {

    private Iterator<Iterator<Integer>> getIteratorOfIterators() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[]{1, 3}));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[]{0, 9}));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[]{1, 9}));
        List<Iterator<Integer>> iterators = new ArrayList<>();
        iterators.add(list1.iterator());
        iterators.add(list2.iterator());
        iterators.add(list3.iterator());
        return iterators.iterator();
    }


    @Test
    public void whenCallConvertThenReturnIteratorOfIterators() {
        Converter converter = new BasicConverter();
        Iterator<Iterator<Integer>> iteratorIterator = getIteratorOfIterators();
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{1, 3, 0, 9, 1, 9}));
        List<Integer> result = new ArrayList<>();

        Iterator<Integer> iterator = converter.convert(iteratorIterator);
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result, is(list));
    }

}