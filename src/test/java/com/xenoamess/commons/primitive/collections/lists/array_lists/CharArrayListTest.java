package com.xenoamess.commons.primitive.collections.lists.array_lists;

import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author XenoAmess
 */
public class CharArrayListTest {

    public void checkEqual(CharArrayList a1, ArrayList<Character> a2) {
        assertEquals(a1.size(), a2.size());
        for (int i = 0; i < a1.size(); i++) {
            assertEquals(a1.get(i), a2.get(i));
            assertEquals(a1.getPrimitive(i), (char) a2.get(i));
        }
        assertEquals(a1, a2);
        assertEquals(a2, a1);
    }

    public void check(int length) {
        CharArrayList a1 = new CharArrayList();
        ArrayList<Character> a2 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char l = ((char) random.nextInt());
            a1.add(l);
            a2.add(l);
        }

        checkEqual(a1, a2);

        boolean flag = false;
        for (Character l : a2) {
            flag = !flag;
            if (flag) {
                a1.remove(l);
            } else {
                a1.removeByContentPrimitive(l);
            }
        }

        assertTrue(a1.isEmpty());

        a1.addAll(a2);

        checkEqual(a1, a2);

        a1.sort(null);
        a2.sort(null);

        checkEqual(a1, a2);
    }

    public void performanceChecks(int length) {
        Random random = new Random();
        CharArrayList data = new CharArrayList();
        for (int i = 0; i < length; i++) {
            char l = ((char) random.nextInt());
            data.addPrimitive(l);
        }

        long begin1 = System.currentTimeMillis();
        CharArrayList a1 = new CharArrayList();

        for (int i = 0; i < length; i++) {
            a1.addPrimitive(data.getPrimitive(i));
        }
        a1.sort(null);
        Object a1c = a1.clone();
        for (int i = 0; i < length; i++) {
            a1.removeByContentPrimitive(data.getPrimitive(i));
        }
        long score1 = (System.currentTimeMillis() - begin1);
        System.out.println("CharArrayList time : " + score1);

        long begin2 = System.currentTimeMillis();
        ArrayList<Character> a2 = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            a2.add((Character) data.getPrimitive(i));
        }
        a2.sort(null);
        Object a2c = a2.clone();
        for (int i = 0; i < length; i++) {
            a2.remove((Character) data.getPrimitive(i));
        }
        long score2 = (System.currentTimeMillis() - begin2);
        System.out.println("ArrayList<Character> time : " + score2);
        assertTrue(score2 > score1);
        assertEquals(a1c, a2c);
    }

    @Test
    public void checks() {
        for (int i = 0; i < 1000; i++) {
            check(100);
        }
        System.out.println("micro end");
        for (int i = 0; i < 20; i++) {
            check(10000);
        }
        System.out.println("small end");
        for (int i = 0; i < 5; i++) {
            check(100000);
        }
        System.out.println("medium end");
//        for (int i = 0; i < 1; i++) {
//            check(1000000);
//        }
        System.out.println("large deleted");
        System.out.println("performance checks started:");
    }
}
