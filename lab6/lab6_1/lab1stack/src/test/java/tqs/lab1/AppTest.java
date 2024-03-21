/*
 * Copyright 2015-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package tqs.lab1;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StackTests {

    TqsStack<Integer> stack;

    @BeforeEach
    void setup(){
        stack = new TqsStack<Integer>();
    }

    @Test
    @DisplayName("A stack is empty on construction")
    void isEmpty() {
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("A stack has size 0 on construction")
    void sizeZero() {
        Assertions.assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    void pushVals() {
        int[] vals = {1,2,3,4,5};
        
        for(int v : vals)
            stack.push(Integer.valueOf(v));

        Assertions.assertTrue(!stack.isEmpty());

        Assertions.assertEquals(vals.length, stack.size());
    }

    @Test
    @DisplayName("If one pushes x then pops, the value popped is x.")
    void pushOnePopOne() {
        stack.push(1);
        Assertions.assertEquals(1, stack.pop());
    }

    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    void peek() {
        stack.push(6);
        int prevSize = stack.size();
        Assertions.assertEquals(6, stack.peek());
        stack.peek();
        Assertions.assertEquals(prevSize, stack.size());
    }


    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    void popVals() {
        int[] vals = {1,2,3,4,5};
        
        for(int v : vals)
            stack.push(Integer.valueOf(v));
        
        for(int i = 0; i < vals.length; i++){
            stack.pop();
        }

        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    void popEmpty() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {stack.pop();});
    }

    @Test
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    void peekEmpty(){
        Assertions.assertThrows(NoSuchElementException.class, () -> {stack.peek();});
    }

    
}
