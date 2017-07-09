package ru.kovladimir.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Binary Search Tree.
 *
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> implements BinaryTreeInterface<E> {

    /**
     * Tree.
     */
    private Node root = null;

    /**
     * {@inheritDoc}
     *
     * @param child E.
     * @return
     */
    @Override
    public boolean addChild(E child) {
        boolean wasAdded = false;
        if (child != null) {
            if (root == null) {
                root = new Node(child);
                wasAdded = true;
            } else {
                wasAdded = addChild(root, child);
            }
        }
        return wasAdded;
    }

    /**
     * {@inheritDoc}
     *
     * @param element E.
     * @return
     */
    @Override
    public boolean contains(E element) {
        boolean has = false;
        if (element != null) {
            has = root != null && contains(root, element);
        }
        return has;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<E> getChildren() {
        List<E> list;
        if (root == null) {
            list = Collections.emptyList();
        } else {
            list = new ArrayList<>();
            addAllChildrenInList(root, list);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public boolean isBalanced() {
        int leftTreeHeight = findHeightOfTree(root.leftChild);
        int rightTreeHeight = findHeightOfTree(root.rightChild);
        return !(leftTreeHeight > rightTreeHeight + 1) && !(rightTreeHeight > leftTreeHeight + 1);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int getHeight() {
        return findHeightOfTree(root);
    }

    /**
     * Add child to the tree. Start with root node.
     *
     * @param parent Node.
     * @param child  E.
     * @return boolean.
     */
    private boolean addChild(Node parent, E child) {
        boolean wasAdded = false;
        int resultOfComparison = child.compareTo(parent.data);
        if (resultOfComparison < 0) {
            if (parent.leftChild == null) {
                parent.leftChild = new Node(child);
                wasAdded = true;
            } else {
                wasAdded = addChild(parent.leftChild, child);
            }
        } else if (resultOfComparison > 0) {
            if (parent.rightChild == null) {
                parent.rightChild = new Node(child);
                wasAdded = true;
            } else {
                wasAdded = addChild(parent.rightChild, child);
            }
        }
        balanceTree();
        return wasAdded;
    }

    /**
     * Check if contains element. Start with node parent.
     *
     * @param parent  Node.
     * @param element E.
     * @return boolean.
     */
    private boolean contains(Node parent, E element) {
        boolean contains = false;
        if (Objects.equals(parent.data, element)) {
            contains = true;
        } else {
            int resultOfComparison = element.compareTo(parent.data);
            if (resultOfComparison < 0 && parent.leftChild != null) {
                contains = contains(parent.leftChild, element);
            } else if (resultOfComparison > 0 && parent.rightChild != null) {
                contains = contains(parent.rightChild, element);
            }
        }
        return contains;
    }

    /**
     * Add all children in list. Start biwth node.
     *
     * @param node Node.
     * @param list List.
     */
    private void addAllChildrenInList(Node node, List<E> list) {
        if (node != null) {
            addAllChildrenInList(node.leftChild, list);
            list.add(node.data);
            addAllChildrenInList(node.rightChild, list);
        }
    }

    /**
     * Find height if tree. Start with root node.
     *
     * @param root Node.
     * @return int.
     */
    private int findHeightOfTree(Node root) {
        return root == null ? -1 : Math.max(findHeightOfTree(root.leftChild), findHeightOfTree(root.rightChild)) + 1;
    }

    /**
     * Balance tree.
     */
    private void balanceTree() {
        int leftTreeHeight = findHeightOfTree(root.leftChild);
        int rightTreeHeight = findHeightOfTree(root.rightChild);
        if (leftTreeHeight > rightTreeHeight + 1) {
            turnTreeToRight();
        } else if (rightTreeHeight > leftTreeHeight + 1) {
            turnTreeToLeft();
        }
    }

    /**
     * Turn tree to right.
     */
    private void turnTreeToRight() {
        Node newRoot = root.leftChild;
        Node rightestInLeftSubtree = root.leftChild;
        while (rightestInLeftSubtree.rightChild != null) {
            rightestInLeftSubtree = rightestInLeftSubtree.rightChild;
        }
        root.leftChild = null;
        rightestInLeftSubtree.rightChild = root;
        root = newRoot;
    }

    /**
     * Turn tree to left.
     */
    private void turnTreeToLeft() {
        Node newRoot = root.rightChild;
        Node leftestInRightTree = root.rightChild;
        while (leftestInRightTree.leftChild != null) {
            leftestInRightTree = leftestInRightTree.leftChild;
        }
        root.rightChild = null;
        leftestInRightTree.leftChild = root;
        root = newRoot;
    }

    /**
     * Node.
     */
    private class Node {

        /**
         * Data.
         */
        E data;

        /**
         * Link to the left child.
         */
        Node leftChild;

        /**
         * Link to the right child.
         */
        Node rightChild;

        /**
         * Constructor.
         *
         * @param data E.
         */
        public Node(E data) {
            this.data = data;
        }
    }
}
