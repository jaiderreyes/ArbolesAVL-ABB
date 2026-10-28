package com.arboles.tree;

import java.util.ArrayList;
import java.util.List;

/** Árbol AVL que conserva un factor de balance entre -1 y 1. */
public final class AvlTree {

    private Node root;

    public void insert(int key) {
        root = insert(root, key);
    }

    public void delete(int key) {
        root = delete(root, key);
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

    public int height() {
        return height(root);
    }

    public int rootKey() {
        if (root == null) {
            throw new IllegalStateException("El árbol está vacío");
        }
        return root.key;
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

    public boolean isBalanced() {
        return checkHeight(root) >= 0;
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        updateHeight(node);
        return rebalance(node);
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else if (node.left == null || node.right == null) {
            node = node.left != null ? node.left : node.right;
        } else {
            Node successor = minimum(node.right);
            node.key = successor.key;
            node.right = delete(node.right, successor.key);
        }

        if (node == null) {
            return null;
        }
        updateHeight(node);
        return rebalance(node);
    }

    private Node rebalance(Node node) {
        int balance = balanceFactor(node);

        if (balance > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left); // Caso izquierda-derecha.
            }
            return rotateRight(node); // Caso izquierda-izquierda.
        }

        if (balance < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right); // Caso derecha-izquierda.
            }
            return rotateLeft(node); // Caso derecha-derecha.
        }

        return node;
    }

    private Node rotateRight(Node unbalanced) {
        Node newRoot = unbalanced.left;
        Node transferredSubtree = newRoot.right;

        newRoot.right = unbalanced;
        unbalanced.left = transferredSubtree;

        updateHeight(unbalanced);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node rotateLeft(Node unbalanced) {
        Node newRoot = unbalanced.right;
        Node transferredSubtree = newRoot.left;

        newRoot.left = unbalanced;
        unbalanced.right = transferredSubtree;

        updateHeight(unbalanced);
        updateHeight(newRoot);
        return newRoot;
    }

    private int balanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private Node minimum(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
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

    private int checkHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = checkHeight(node.left);
        int rightHeight = checkHeight(node.right);
        if (leftHeight < 0 || rightHeight < 0
                || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static final class Node {
        private int key;
        private int height = 1;
        private Node left;
        private Node right;

        private Node(int key) {
            this.key = key;
        }
    }
}
