package ru.kovladimir.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleTreeTest {

    @Test (expected = NullPointerException.class)
    public void whenSetNullRootThenThrowNPE() {
        new SimpleTree<>(null);
    }

    @Test
    public void whenSetRootThenContainsIt() {
        Tree<String> tree = new SimpleTree<>("0");

        boolean result = tree.contains("0");

        assertThat(result, is(true));
    }

    @Test
    public void whenAddNullThenDoNotAddItAndReturnFalse() {
        Tree<String> tree = new SimpleTree<>("0");

        boolean result = tree.addChild("0", null);

        assertThat(result, is(false));
    }

    @Test
    public void whenSearchNullThenReturnsFalse() {
        Tree<String> tree = new SimpleTree<>("0");

        boolean result = tree.contains(null);

        assertThat(result, is(false));
    }

    @Test
    public void whenAddToRootThenReturnTrue() {
        Tree<String> tree = new SimpleTree<>("0");

        boolean result = tree.addChild("0", "1");

        assertThat(result, is(true));
    }

    @Test
    public void whenAddToRootThenContainsIt() {
        Tree<String> tree = new SimpleTree<>("0");

        tree.addChild("0", "1");
        boolean result = tree.contains("1");

        assertThat(result, is(true));
    }

    @Test
    public void whenAddSecondElementToRootThenReturnTrue() {
        Tree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");

        boolean result = tree.addChild("0", "2");

        assertThat(result, is(true));
    }

    @Test
    public void whenAddSecondElementToRootThenContainsIt() {
        Tree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");
        tree.addChild("0", "2");

        boolean result = tree.contains("2");

        assertThat(result, is(true));
    }

    @Test
    public void whenAddElementToAnotherElementThenReturnTrue() {
        Tree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");
        tree.addChild("0", "2");

        boolean result = tree.addChild("1", "3");

        assertThat(result, is(true));
    }

    @Test
    public void whenAddElementToAnotherElementThenContainsIt() {
        Tree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");
        tree.addChild("0", "2");

        tree.addChild("1", "3");
        boolean result = tree.contains("3");

        assertThat(result, is(true));
    }

    @Test
    public void whenGetChildrenThenReturnListWithRightSize() {
        Tree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");
        tree.addChild("0", "2");
        tree.addChild("1", "3");

        List<String> result = tree.getChildren();

        assertThat(result.size(), is(4));
    }

    @Test
    public void whenGetChildrenThenReturnListWithRightValues() {
        List<String> list = new ArrayList<>(Arrays.asList(new String[]{"0", "1", "2", "3"}));
        Tree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");
        tree.addChild("0", "2");
        tree.addChild("1", "3");

        List<String> result = tree.getChildren();
        Collections.sort(result);

        assertThat(list, is(result));
    }

    @Test
    public void whenHasOnlyRootAndGetChildrenThenResultSizeIsOne() {
        Tree<String> tree = new SimpleTree<>("0");

        List<String> result = tree.getChildren();

        assertThat(result.size(), is(1));
    }

    @Test
    public void whenHasOnlyRootAndGetChildrenThenResultHasOnlyRoot() {
        List<String> list = new ArrayList<>(Arrays.asList(new String[]{"0"}));
        Tree<String> tree = new SimpleTree<>("0");

        List<String> result = tree.getChildren();

        assertThat(result, is(list));
    }

    @Test
    public void whenTreeHasOnlyRootThenIsBalanced() {
        Tree<String> tree = new SimpleTree<>("0");

        boolean result = tree.isBalanced();

        assertThat(result, is(true));
    }

    @Test
    public void whenTreeHasTwoLeavesElementsThenIsBalanced() {
        Tree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");
        tree.addChild("0", "2");

        boolean result = tree.isBalanced();

        assertThat(result, is(true));
    }

    @Test
    public void whenTreeHasOneLeafThenTreeIsNotBalanced() {
        Tree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");

        boolean result = tree.isBalanced();

        assertThat(result, is(false));
    }

    @Test
    public void whenAddBalancedTreeWithNineLeavesThenIsBalanced() {
        SimpleTree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");
        tree.addChild("0", "3");
        tree.addChild("1", "4");
        tree.addChild("1", "5");
        tree.addChild("3", "6");
        tree.addChild("3", "7");
        tree.addChild("6", "8");
        tree.addChild("6", "9");

        boolean result = tree.isBalanced();

        assertThat(result, is(true));
    }

    @Test
    public void whenAddNotBalancedTreeWithNineLeavesThenIsNotBalanced() {
        SimpleTree<String> tree = new SimpleTree<>("0");
        tree.addChild("0", "1");
        tree.addChild("0", "2");
        tree.addChild("0", "3");
        tree.addChild("1", "4");
        tree.addChild("1", "5");
        tree.addChild("4", "7");
        tree.addChild("3", "6");
        tree.addChild("6", "8");
        tree.addChild("6", "9");

        boolean result = tree.isBalanced();

        assertThat(result, is(false));
    }

}