package com.arboles.tree;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {

    @Test
    void insertsSearchesTraversesAndDeletes() {
        BinarySearchTree tree = new BinarySearchTree();
        for (int value : new int[]{8, 3, 10, 1, 6, 14, 4, 7, 13}) {
            tree.insert(value);
        }

        assertTrue(tree.contains(7));
        assertFalse(tree.contains(99));
        assertEquals(List.of(1, 3, 4, 6, 7, 8, 10, 13, 14), tree.inOrder());

        tree.delete(3);

        assertFalse(tree.contains(3));
        assertTrue(tree.isValid());
        assertEquals(List.of(1, 4, 6, 7, 8, 10, 13, 14), tree.inOrder());
    }
}
