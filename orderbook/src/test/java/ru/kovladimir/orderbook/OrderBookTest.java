package ru.kovladimir.orderbook;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderBookTest {

    @Test
    public void whenAddOneBuyOrderThenBuyMapContainsIt() {
        OrderBook book = new OrderBook("");
        Order order = new Order("name", Operation.BUY, 10.0, 20, 1);

        book.add(order);
        boolean contains = book.getMapBuyByPrice().containsKey(order.getPrice());

        assertThat(contains, is(true));
    }

    @Test
    public void whenAddOneBuyOrderThenSellMapDoesNotContainIt() {
        OrderBook book = new OrderBook("");
        Order order = new Order("name", Operation.BUY, 10.0, 20, 1);

        book.add(order);
        boolean contains = book.getMapSellByPrice().containsKey(order.getPrice());

        assertThat(contains, is(false));
    }

    @Test
    public void whenAddOneSellOrderThenSellMapContainsIt() {
        OrderBook book = new OrderBook("");
        Order order = new Order("name", Operation.SELL, 10.0, 20, 1);

        book.add(order);
        boolean contains = book.getMapSellByPrice().containsKey(order.getPrice());

        assertThat(contains, is(true));
    }

    @Test
    public void whenAddOneSellOrderThenBuyMapDoesNotContainIt() {
        OrderBook book = new OrderBook("");
        Order order = new Order("name", Operation.SELL, 10.0, 20, 1);

        book.add(order);
        boolean contains = book.getMapBuyByPrice().containsKey(order.getPrice());

        assertThat(contains, is(false));
    }

    @Test
    public void whenAddBuyAndSellWithSameQuantityAndPriceThenBuyMapIsEmpty() {
        OrderBook book = new OrderBook("");
        Order buyOrder1 = new Order("name", Operation.BUY, 10.0, 20, 1);
        Order sellOrder = new Order("name", Operation.SELL, 10.0, 20, 2);

        book.add(buyOrder1);
        book.add(sellOrder);
        boolean contains = book.getMapBuyByPrice().containsKey(buyOrder1.getPrice());

        assertThat(contains, is(false));
    }

    @Test
    public void whenAddBuyAndSellWithSameQuantityAndPriceThenSellMapIsEmpty() {
        OrderBook book = new OrderBook("");
        Order buyOrder1 = new Order("name", Operation.BUY, 10.0, 20, 1);
        Order sellOrder = new Order("name", Operation.SELL, 10.0, 20, 2);

        book.add(buyOrder1);
        book.add(sellOrder);
        boolean contains = book.getMapSellByPrice().containsKey(buyOrder1.getPrice());

        assertThat(contains, is(false));
    }

    @Test
    public void whenBuyMoreThanSelThenBuyMapHasDifferenceOfQuantity() {
        OrderBook book = new OrderBook("");
        Order buyOrder = new Order("name", Operation.BUY, 10.0, 20, 1);
        Order sellOrder = new Order("name", Operation.SELL, 10.0, 10, 2);

        book.add(buyOrder);
        book.add(sellOrder);
        boolean contains = book.getMapBuyByPrice().get(10.0).contains(new Order("name", Operation.BUY, 10.0, 10, 1));

        assertThat(contains, is(true));
    }

    @Test
    public void whenSellMoreThanBuyThenSellMapHasDifferenceOfQuantity() {
        OrderBook book = new OrderBook("");
        Order sellOrder = new Order("name", Operation.SELL, 10.0, 52, 1);
        Order buyOrder = new Order("name", Operation.BUY, 10.0, 20, 2);

        book.add(sellOrder);
        book.add(buyOrder);
        boolean contains = book.getMapSellByPrice().get(10.0).contains(new Order("name", Operation.SELL, 10.0, 32, 1));

        assertThat(contains, is(true));
    }

    @Test
    public void whenDeleteBuyOrderByIDThenBuyMapDoesNotContainIt() {
        OrderBook book = new OrderBook("");
        Order order = new Order("name", Operation.BUY, 10.0, 20, 1);
        book.add(order);

        book.delete(new Order("", null, 0.0, 0, 1));
        boolean contains = book.getMapBuyByPrice().containsKey(order.getPrice());

        assertThat(contains, is(false));
    }

    @Test
    public void whenDeleteSellOrderByIDThenSellMapDoesNotContainIt() {
        OrderBook book = new OrderBook("");
        Order order = new Order("name", Operation.SELL, 10.0, 20, 1);
        book.add(order);

        book.delete(new Order("", null, 0.0, 0, 1));
        boolean contains = book.getMapSellByPrice().containsKey(order.getPrice());

        assertThat(contains, is(false));
    }

}