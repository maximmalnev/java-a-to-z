package ru.kovladimir.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Tree.
 * @param <E>
 */
public class SimpleTree<E> implements Tree<E> {

    /**
     * Root.
     */
    private Node root;

    /**
     * Tree with root element.
     * @param root E.
     */
    public SimpleTree(E root) {
        if (root == null) {
            throw new NullPointerException();
        }
        this.root = new Node(root);
    }

    /**
     * {@inheritDoc}
     * @param parent E.
     * @param child E.
     * @return
     */
    @Override
    public boolean addChild(E parent, E child) {
        boolean wasAdded = false;
        if (child != null && parent != null) {
            Node parentNode = getNodeByElement(parent, root);
            if (parentNode != null) {
                Node childNode = new Node(child);
                parentNode.children.add(childNode);
                wasAdded = true;
            }
        }
        return wasAdded;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<E> getChildren() {
        List<E> list = new ArrayList<>();
        addAllElementsInList(list, root);
        return list;
    }

    /**
     * {@inheritDoc}
     * @param element E.
     * @return
     */
    @Override
    public boolean contains(E element) {
        boolean contains = false;
        if (element != null) {
            contains = getNodeByElement(element, root) != null;
        }
        return contains;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isBalanced() {
        return isBalanced(root);

    }

    /**
     * Check if the tree (started by parent) is balanced.
     * @param parent Node.
     * @return boolean.
     */
    private boolean isBalanced(Node parent) {
        boolean isBalanced = true;
        int size = parent.children.size();
        if (size != 2 && size != 0) {
            isBalanced = false;
        } else if (size == 2) {
            isBalanced = isBalanced(parent.children.get(0));
            if (isBalanced) {
                isBalanced = isBalanced(parent.children.get(1));
            }
        }
        return isBalanced;
    }

    /**
     * Get node by element (start by parent node).
     * @param element E.
     * @param parent Node.
     * @return Node.
     */
    private Node getNodeByElement(E element, Node parent) {
        Node node = null;
        if (parent.element.equals(element)) {
            node = parent;
        } else {
            List<Node> children = parent.children;
            for (Node child : children) {
                node = getNodeByElement(element, child);
                if (node != null) {
                    break;
                }
            }
        }
        return node;
    }

    /**
     * Get all elements from parent and add to list.
     * @param children List.
     * @param parent Node.
     */
    private void addAllElementsInList(List<E> children, Node parent) {
        children.add(parent.element);
        for (Node node : parent.children) {
            addAllElementsInList(children, node);
        }
    }

    /**
     * Node that contains element and has links to children.
     */
    private class Node {

        /**
         *
         */
        E element;

        /**
         * List of children.
         */
        List<Node> children = new ArrayList<>();

        /**
         * Constructor with element.
         * @param element E.
         */
        public Node(E element) {
            this.element = element;
        }
    }
}
