package ru.kovladimir.tree;

import java.util.List;

/**
 * Binary tree.
 * @param <E>
 */
public interface BinaryTreeInterface<E> {

    /**
     * Add child to parent. If parent or child are null, then don't add.
     * @param child E.
     * @return was added or not.
     */
    boolean addChild(E child);

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

    /**
     * Get height of the tree.
     * @return int.
     */
    int getHeight();
}
