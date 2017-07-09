package ru.kovladimir.orderbook;

import java.util.*;

/**
 * Order Book.
 */
public class OrderBook {

    /**
     * Name of product.
     */
    private String name;

    /**
     * Buy orders sorted by ID. Pair id-order.
     */
    private Map<Long, Order> buyByID = new HashMap<>();

    /**
     * Sell orders sorted by ID. Pair id-order.
     */
    private Map<Long, Order> sellByID = new HashMap<>();

    /**
     * Buy orders sorted by price. Pair price-list of orders.
     */
    private TreeMap<Double, List<Order>> buyByPrice = new TreeMap<>(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return Double.compare(o2, o1);
        }
    });

    /**
     * Sell orders sorted by price. Pair price-list of orders.
     */
    private TreeMap<Double, List<Order>> sellByPrice = new TreeMap<>(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return Double.compare(o1, o2);
        }
    });

    /**
     * Constructor.
     * @param name String.
     */
    public OrderBook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TreeMap<Double, List<Order>> getMapBuyByPrice() {
        return buyByPrice;
    }

    public TreeMap<Double, List<Order>> getMapSellByPrice() {
        return sellByPrice;
    }

    /**
     * Add order.
     * @param order Order.
     */
    public void add(Order order) {
        if (order.getOperation() == Operation.BUY) {
            addOrderToBuy(order);
        } else {
            addOrderToSell(order);
        }
    }

    /**
     * Delete order.
     * @param order Order.
     */
    public void delete(Order order) {
        boolean wasDeleted = deleteFromMaps(order, buyByID, buyByPrice);
        if (!wasDeleted) {
            deleteFromMaps(order, sellByID, sellByPrice);
        }
    }

    /**
     * Add order to "buy maps".
     * @param order Order.
     */
    private void addOrderToBuy(Order order) {
        if (sellByID.isEmpty()) {
            addToIDMap(buyByID, order);
            addToPriceMap(buyByPrice, order);
        } else {
            Map.Entry<Double, List<Order>> entry = sellByPrice.firstEntry();
            if (order.getPrice() >= entry.getKey()) {

                List<Order> listOfOrders = entry.getValue();
                Order firstOrderInOpposite = listOfOrders.get(0);

                long quantityToBuy = order.getQuantity();
                long quantityToSellInFirstOrder = firstOrderInOpposite.getQuantity();

                if (quantityToBuy > quantityToSellInFirstOrder) {
                    removeFromListOrMap(sellByPrice, firstOrderInOpposite.getPrice(), listOfOrders);
                    sellByID.remove(firstOrderInOpposite.getId());
                    order.setQuantity(quantityToBuy - quantityToSellInFirstOrder);
                    addOrderToBuy(order);
                } else if (quantityToBuy < quantityToSellInFirstOrder) {
                    firstOrderInOpposite.setQuantity(quantityToSellInFirstOrder - quantityToBuy);
                } else { //quantityToBuy == quantityToSell
                    removeFromListOrMap(sellByPrice, firstOrderInOpposite.getPrice(), listOfOrders);
                    sellByID.remove(firstOrderInOpposite.getId());
                }
            } else {
                addToIDMap(buyByID, order);
                addToPriceMap(buyByPrice, order);
            }
        }
    }

    /**
     * Add order to "sell maps".
     * @param order Order.
     */
    private void addOrderToSell(Order order) {
        if (buyByID.isEmpty()) {
            addToIDMap(sellByID, order);
            addToPriceMap(sellByPrice, order);
        } else {
            Map.Entry<Double, List<Order>> entry = buyByPrice.firstEntry();
            if (entry.getKey() >= order.getPrice()) {

                List<Order> listOfOrders = entry.getValue();
                Order firstOrderInOpposite = listOfOrders.get(0);

                long quantityToBuyInFirstOrder = firstOrderInOpposite.getQuantity();
                long quantityToSell = order.getQuantity();

                if (quantityToBuyInFirstOrder > quantityToSell) {
                    firstOrderInOpposite.setQuantity(quantityToBuyInFirstOrder - quantityToSell);
                } else if (quantityToBuyInFirstOrder < quantityToSell) {
                    removeFromListOrMap(buyByPrice, firstOrderInOpposite.getPrice(), listOfOrders);
                    buyByID.remove(firstOrderInOpposite.getId());
                    order.setQuantity(quantityToSell - quantityToBuyInFirstOrder);
                    addOrderToSell(order);
                } else { //quantityToBuy == quantityToSell
                    removeFromListOrMap(buyByPrice, firstOrderInOpposite.getPrice(), listOfOrders);
                    buyByID.remove(firstOrderInOpposite.getId());
                }
            } else {
                addToIDMap(sellByID, order);
                addToPriceMap(sellByPrice, order);
            }
        }
    }

    /**
     * If Order is the last in list then remove pair from map, else remove order from list.
     * @param map Map.
     * @param price double.
     * @param list List.
     */
    private void removeFromListOrMap(TreeMap<Double, List<Order>> map, double price, List<Order> list) {
        if (list.size() == 1) {
            map.remove(price);
        } else {
            list.remove(0);
        }
    }

    /**
     * Add Order to map sorted by ID.
     * @param map Map.
     * @param order Order.
     */
    private void addToIDMap(Map<Long, Order> map, Order order) {
        map.put(order.getId(), order);
    }

    /**
     * Add Order to map sorted by price.
     * @param map Map.
     * @param order Order.
     */
    private void addToPriceMap(Map<Double, List<Order>> map, Order order) {
        List<Order> list = map.get(order.getPrice());
        if (list == null) {
            list = new LinkedList<>();
            list.add(order);
            map.put(order.getPrice(), list);
        } else {
            list.add(order);

        }
    }

    /**
     * Delete order from "buy maps" or @sell maps".
     * @param order Order.
     * @param mapID Map ID.
     * @param mapPrice Map Price.
     * @return boolean.
     */
    private boolean deleteFromMaps(Order order, Map<Long, Order> mapID, TreeMap<Double, List<Order>> mapPrice) {
        boolean wasDeleted = false;
        Order orderFromMap = mapID.get(order.getId());
        if (orderFromMap != null) {
            mapID.remove(order.getId());

            List<Order> listWIthOrder = mapPrice.get(orderFromMap.getPrice());
            if (listWIthOrder.size() <= 1) {
                mapPrice.remove(orderFromMap.getPrice());
            } else {
                listWIthOrder.remove(orderFromMap);
            }

            wasDeleted = true;
        }
        return wasDeleted;
    }
}
