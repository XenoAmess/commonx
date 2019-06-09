package com.xenoamess.commons.primitive.collections.lists.array_lists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author XenoAmess
 */
public class LongArrayListTest {

    public void checkEqual(LongArrayList a1, ArrayList<Long> a2) {
        assertTrue(a1.size() == a2.size());
        for (int i = 0; i < a1.size(); i++) {
            assertTrue(a1.get(i).equals(a2.get(i)));
            assertTrue(a1.getPrimitive(i) == a2.get(i));
        }
        assertTrue(a1.equals(a2));
        assertTrue(a2.equals(a1));
    }

    public void check(int length) {
        LongArrayList a1 = new LongArrayList();
        ArrayList<Long> a2 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            long l = random.nextLong();
            a1.add(l);
            a2.add(l);
        }

        checkEqual(a1, a2);

        boolean flag = false;
        for (Long l : a2) {
            flag = !flag;
            if (flag) {
                a1.remove(l);
            } else {
                a1.removePrimitive(l);
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
        LongArrayList data = new LongArrayList();
        for (int i = 0; i < length; i++) {
            long l = random.nextLong();
            data.add(l);
        }

        long begin1 = System.nanoTime();
        LongArrayList a1 = new LongArrayList();
        for (int i = 0; i < a1.size(); i++) {
            a1.addPrimitive(data.getPrimitive(i));
        }
        for (int i = 0; i < length; i++) {
            a1.removePrimitive(data.getPrimitive(i));
        }
        a1.sort(null);
        long score1 = (System.nanoTime() - begin1);
        System.out.println("LongArrayList score : " + score1);

        long begin2 = System.nanoTime();
        ArrayList<Long> a2 = new ArrayList<Long>();
        for (int i = 0; i < a1.size(); i++) {
            a2.add(data.getPrimitive(i));
        }
        for (int i = 0; i < length; i++) {
            a2.remove(data.getPrimitive(i));
        }
        a2.sort(null);
        long score2 = (System.nanoTime() - begin2);
        System.out.println("ArrayList<Long> score : " + score2);
        assert (score2 > score1);
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
        performanceChecks(1000000);
    }
}
