package ru.kovladimir.tree;

import java.util.*;

/**
 * Simple Binary Tree.
 *
 * @param <E>
 */
public class BinaryTree<E> implements BinaryTreeInterface<E> {

    /**
     * Root.
     */
    private Node root;

    /**
     * {@inheritDoc}
     *
     * @param data
     * @return
     */
    @Override
    public boolean addChild(E data) {
        boolean wasAdded = false;
        if (data != null) {
            if (root == null) {
                root = new Node(data);
                wasAdded = true;
            } else {
                wasAdded = addChild(root, data);
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
        boolean contains = false;
        if (element != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.data.equals(element)) {
                    contains = true;
                    break;
                } else {
                    if (node.leftChild != null) {
                        queue.add(node.leftChild);
                    }
                    if (node.rightChild != null) {
                        queue.add(node.rightChild);
                    }
                }
            }
        }
        return contains;
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
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                list.add(node.data);
                if (node.leftChild != null) {
                    queue.add(node.leftChild);
                }
                if (node.rightChild != null) {
                    queue.add(node.rightChild);
                }
            }
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
     * Add child. Begin with root.
     *
     * @param root Node root.
     * @param data Data to add.
     * @return boolean.
     */
    private boolean addChild(Node root, E data) {
        boolean wasAdded = false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.data.equals(data)) {
                break;
            } else {
                if (node.leftChild == null) {
                    node.leftChild = new Node(data);
                    wasAdded = true;
                    break;
                } else if (node.rightChild == null) {
                    node.rightChild = new Node(data);
                    wasAdded = true;
                    break;
                } else {
                    queue.add(node.leftChild);
                    queue.add(node.rightChild);
                }
            }
        }
        return wasAdded;
    }

    /**
     * Get height of tree. Start with root.
     *
     * @param root Node root.
     * @return int. Height.
     */
    private int findHeightOfTree(Node root) {
        return root == null ? -1 : Math.max(findHeightOfTree(root.leftChild), findHeightOfTree(root.rightChild)) + 1;
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
