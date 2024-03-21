/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;

    private static final int SET_A_MAX_SIZE = 1;
    private static final int SET_B_MAX_SIZE = 6;
    private static final int SET_C_MAX_SIZE = 2;
    private static final int SET_D_MAX_SIZE = 5;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(SET_A_MAX_SIZE);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = new BoundedSetOfNaturals(SET_D_MAX_SIZE);
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    public void testAddElement() {

        // Test max size exception and size consistency
        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(SET_A_MAX_SIZE, setA.size(), "add: elements count not as expected.");

        assertThrows(IllegalArgumentException.class, () -> setA.add(11));
        assertEquals(1, setA.size(), "add: elements count not as expected.");
        assertFalse(setA.contains(11));

        // Test negative number exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(-10));
        assertEquals(0, setD.size(), "add: elements count not as expected.");
        assertFalse(setD.contains(-10));

        // Test element duplication
        assertThrows(IllegalArgumentException.class, () -> setA.add(99));
        assertEquals(SET_A_MAX_SIZE, setA.size(), "add: elements count not as expected.");
        
        assertThrows(IllegalArgumentException.class, () -> setC.add(60));
        assertEquals(SET_C_MAX_SIZE, setC.size(), "add: elements count not as expected.");
    }

    @Test
    public void testAddFromBadArray() {
        final int[] elems = new int[]{10, 20};

        // Test max size exception and size consistency
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
        assertEquals(SET_A_MAX_SIZE, setA.size(), "add: elements count not as expected.");
        assertTrue(setA.contains(10));
        assertFalse(setA.contains(20));

        // Test negative number exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(new int[]{-20,30}));
        assertEquals(0, setD.size(), "add: elements count not as expected.");
        assertFalse(setA.contains(-20));
        assertFalse(setA.contains(30));

        final int[] elems2 = new int[]{9183, 12, 29, 102, 12};

        // Test element duplication
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems2));
        assertEquals(4, setD.size(), "add: elements count not as expected.");
        for(int i : elems2)
            assertTrue(setD.contains(i), String.format("add: element %d not in setD.", i));
    }

    @Test
    public void testIntersect(){
        assertTrue(setB.intersects(setC), "intersects: setB should intersect setC.");
        assertFalse(setC.intersects(setD), "intersects: setC should NOT intersect setD, which is currently empty.");
        
        setA.add(999);
        assertFalse(setC.intersects(setA), "intersects: setC should NOT intersect setA, which currently has 999.");

        setD.add(50);
        assertTrue(setC.intersects(setD), "intersects: setC should intersect setD, which currently has 50.");

        // Check commutativity
        assertTrue(setD.intersects(setC), "intersects: setD should intersect setC, which currently has 50.");

        // Check self intersection
        assertTrue(setD.intersects(setD), "intersects: setD should intersect itself.");
    }


}
