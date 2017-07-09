package ru.kovladimir.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void whenAddRootThenReturnTrue() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();

        boolean result = tree.addChild(0);

        assertThat(result, is(true));
    }

    @Test
    public void whenAddRootThenContainsIt() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();

        tree.addChild(0);
        boolean result = tree.contains(0);

        assertThat(result, is(true));
    }

    @Test
    public void whenAddNullThenReturnFalse() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();

        boolean result = tree.addChild(null);

        assertThat(result, is(false));
    }

    @Test
    public void whenAddNullThenDoesNotContainIt() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();

        tree.addChild(null);
        boolean result = tree.contains(null);

        assertThat(result, is(false));
    }

    @Test
    public void whenAddSecondElementThenReturnTrue() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        tree.addChild(0);

        boolean result = tree.addChild(1);

        assertThat(result, is(true));
    }

    @Test
    public void whenAddSecondElementThenContainsIt() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        tree.addChild(0);

        tree.addChild(1);
        boolean result = tree.contains(1);

        assertThat(result, is(true));
    }

    @Test
    public void whenEmptyTreeThenReturnEmptyList() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        List<Integer> list = Collections.emptyList();

        List<Integer> result = tree.getChildren();

        assertThat(result, is(list));
    }

    @Test
    public void whenAddOnlyRootThenListHasOnlyRoot() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{0}));
        tree.addChild(0);

        List<Integer> result = tree.getChildren();

        assertThat(result, is(list));
    }

    @Test
    public void whenAddEightElementsThenListSizeIsEight() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 8; i++) {
            tree.addChild(i);
        }

        List<Integer> result = tree.getChildren();

        assertThat(result.size(), is(8));
    }

    @Test
    public void whenAddEightElementsThenListHasTheseElements() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        List<Integer> list = new ArrayList<>(Arrays.asList(
                new Integer[]{0, 1, 2, 3, 4, 5, 6, 7}));
        for (int i = 0; i < 8; i++) {
            tree.addChild(i);
        }

        List<Integer> result = tree.getChildren();

        assertThat(result, is(list));
    }

    @Test
    public void whenCheckBalanceThenReturnTrue() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 8; i++) {
            tree.addChild(i);
        }

        boolean result = tree.isBalanced();

        assertThat(result, is(true));
    }

    @Test
    public void whenDoNotAddElementsThenHeightIsMinusOne() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();

        int result = tree.getHeight();

        assertThat(result, is(-1));
    }

    @Test
    public void whenAddOnlyRootThenHeightIsZero() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        tree.addChild(0);

        int result = tree.getHeight();

        assertThat(result, is(0));
    }

    @Test
    public void whenAddThreeElementsThenHeightIsOne() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 3; i++) {
            tree.addChild(i);
        }

        int result = tree.getHeight();

        assertThat(result, is(1));
    }

    @Test
    public void whenAddSevenElementsThenHeightIsTwo() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 7; i++) {
            tree.addChild(i);
        }

        int result = tree.getHeight();

        assertThat(result, is(2));
    }

    @Test
    public void whenAddEightElementsThenHeightIsThree() {
        BinaryTreeInterface<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 8; i++) {
            tree.addChild(i);
        }

        int result = tree.getHeight();

        assertThat(result, is(3));
    }
}