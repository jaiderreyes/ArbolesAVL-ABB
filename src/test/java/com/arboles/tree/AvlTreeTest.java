package com.arboles.tree;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AvlTreeTest {

    @Test
    void appliesLlRrLrAndRlRotations() {
        assertRotation(new int[]{30, 20, 10}); // LL
        assertRotation(new int[]{10, 20, 30}); // RR
        assertRotation(new int[]{30, 10, 20}); // LR
        assertRotation(new int[]{10, 30, 20}); // RL
    }

    @Test
    void remainsBalancedAfterDeletion() {
        AvlTree tree = new AvlTree();
        for (int value : new int[]{20, 10, 30, 5, 15, 25, 40, 35, 50}) {
            tree.insert(value);
        }

        tree.delete(10);
        tree.delete(20);

        assertFalse(tree.contains(20));
        assertTrue(tree.isBalanced());
        assertEquals(List.of(5, 15, 25, 30, 35, 40, 50), tree.inOrder());
    }

    private void assertRotation(int[] values) {
        AvlTree tree = new AvlTree();
        for (int value : values) {
            tree.insert(value);
        }

        assertEquals(20, tree.rootKey());
        assertEquals(List.of(20, 10, 30), tree.preOrder());
        assertTrue(tree.isBalanced());
    }
}
