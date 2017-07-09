package ru.kovladimir.tree;

import java.util.List;

/**
 * Tree.
 * @param <E>
 */
public interface Tree<E> {

    /**
     * Add child to parent. If parent or child are null, then don't add.
     * @param parent E.
     * @param child E.
     * @return was added or not.
     */
    boolean addChild(E parent, E child);

    /**
     * Check if tree has this element.
     * @param element E.
     * @return contains.
     */
    boolean contains(E element);

    /**
     * Get List of all elements in the tree.
     * @return List.
     */
    List<E> getChildren();

    /**
     * Check if the tree is balances.
     * @return boolean.
     */
    boolean isBalanced();
}
