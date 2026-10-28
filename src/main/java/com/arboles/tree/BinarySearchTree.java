package com.arboles.tree;

import java.util.ArrayList;
import java.util.List;

/** Árbol binario de búsqueda (ABB) de claves enteras. */
public final class BinarySearchTree {

    private Node root;

    public void insert(int key) {
        root = insert(root, key);
    }

    public boolean contains(int key) {
        Node current = root;
        while (current != null) {
            if (key == current.key) {
                return true;
            }
            current = key < current.key ? current.left : current.right;
        }
        return false;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public int height() {
        return height(root);
    }

    public List<Integer> inOrder() {
        List<Integer> values = new ArrayList<>();
        inOrder(root, values);
        return List.copyOf(values);
    }

    public List<Integer> preOrder() {
        List<Integer> values = new ArrayList<>();
        preOrder(root, values);
        return List.copyOf(values);
    }

    public List<Integer> postOrder() {
        List<Integer> values = new ArrayList<>();
        postOrder(root, values);
        return List.copyOf(values);
    }

    public boolean isValid() {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        } else {
            Node successor = minimum(node.right);
            node.key = successor.key;
            node.right = delete(node.right, successor.key);
        }
        return node;
    }

    private Node minimum(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int height(Node node) {
        return node == null ? 0 : 1 + Math.max(height(node.left), height(node.right));
    }

    private void inOrder(Node node, List<Integer> values) {
        if (node != null) {
            inOrder(node.left, values);
            values.add(node.key);
            inOrder(node.right, values);
        }
    }

    private void preOrder(Node node, List<Integer> values) {
        if (node != null) {
            values.add(node.key);
            preOrder(node.left, values);
            preOrder(node.right, values);
        }
    }

    private void postOrder(Node node, List<Integer> values) {
        if (node != null) {
            postOrder(node.left, values);
            postOrder(node.right, values);
            values.add(node.key);
        }
    }

    private boolean isValid(Node node, long minimum, long maximum) {
        if (node == null) {
            return true;
        }
        return node.key > minimum && node.key < maximum
                && isValid(node.left, minimum, node.key)
                && isValid(node.right, node.key, maximum);
    }

    private static final class Node {
        private int key;
        private Node left;
        private Node right;

        private Node(int key) {
            this.key = key;
        }
    }
}
