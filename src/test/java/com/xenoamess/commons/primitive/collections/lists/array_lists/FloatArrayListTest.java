package com.xenoamess.commons.primitive.collections.lists.array_lists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author XenoAmess
 */
public class FloatArrayListTest {

    public void checkEqual(FloatArrayList a1, ArrayList<Float> a2) {
        assertEquals(a1.size(), a2.size());
        for (int i = 0; i < a1.size(); i++) {
            assertEquals(a1.get(i), a2.get(i));
            assertEquals(a1.getPrimitive(i), (float) a2.get(i));
        }
        assertEquals(a1, a2);
        assertEquals(a2, a1);
    }

    public void check(int length) {
        FloatArrayList a1 = new FloatArrayList();
        ArrayList<Float> a2 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            float l = random.nextFloat();
            a1.add(l);
            a2.add(l);
        }

        checkEqual(a1, a2);

        boolean flag = false;
        for (Float l : a2) {
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
        FloatArrayList data = new FloatArrayList();
        for (int i = 0; i < length; i++) {
            float l = random.nextFloat();
            data.add(l);
        }

        long begin1 = System.nanoTime();
        FloatArrayList a1 = new FloatArrayList();
        for (int i = 0; i < length; i++) {
            a1.addPrimitive(data.getPrimitive(i));
        }
        for (int i = 0; i < length; i++) {
            a1.removeByContentPrimitive(data.getPrimitive(i));
        }
        a1.sort(null);
        long score1 = (System.nanoTime() - begin1);
        System.out.println("FloatArrayList time : " + score1);

        long begin2 = System.nanoTime();
        ArrayList<Float> a2 = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            a2.add(data.getPrimitive(i));
        }
        for (int i = 0; i < length; i++) {
            a2.remove((Object) data.getPrimitive(i));
        }
        a2.sort(null);
        long score2 = (System.nanoTime() - begin2);
        System.out.println("ArrayList<Float> time : " + score2);
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
