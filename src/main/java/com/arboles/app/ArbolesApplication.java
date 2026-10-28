package com.arboles.app;

import com.arboles.tree.AvlTree;
import com.arboles.tree.BinarySearchTree;

/** Ejemplo comparativo de un ABB y un árbol AVL. */
public final class ArbolesApplication {

    private ArbolesApplication() {
    }

    public static void main(String[] args) {
        int[] values = {30, 20, 10, 25, 40, 50, 45};

        BinarySearchTree abb = new BinarySearchTree();
        AvlTree avl = new AvlTree();

        for (int value : values) {
            abb.insert(value);
            avl.insert(value);
        }

        System.out.println("ABB en orden: " + abb.inOrder());
        System.out.println("ABB preorden: " + abb.preOrder());
        System.out.println("Altura del ABB: " + abb.height());
        System.out.println();
        System.out.println("AVL en orden: " + avl.inOrder());
        System.out.println("AVL preorden: " + avl.preOrder());
        System.out.println("Raíz del AVL: " + avl.rootKey());
        System.out.println("Altura del AVL: " + avl.height());
        System.out.println("¿AVL balanceado?: " + avl.isBalanced());

        avl.delete(20);
        System.out.println("AVL después de eliminar 20: " + avl.preOrder());
    }
}
