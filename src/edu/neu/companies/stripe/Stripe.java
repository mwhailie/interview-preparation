package edu.neu.companies.stripe;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Stripe {
    static class RecordComparator implements Comparator<Map<String, Integer>> {
        String key;
        String direction;
        public RecordComparator(String key, String direction) {
            this.key = key;
            this.direction = direction;
        }
        public int compare(Map<String, Integer> left, Map<String, Integer> right) {
            int valueLeft = left.getOrDefault(key, 0);
            int valueRight = right.getOrDefault(key, 0);
            if("asc".equals(direction)){
                return valueLeft - valueRight;
            }else{
                return valueRight - valueLeft;
            }
        }
    }
    static class SortOrderComparator implements Comparator<Map<String, Integer>> {
        LinkedHashMap<String, String> sortOrder;
        public SortOrderComparator(LinkedHashMap<String, String> sortOrder) {
            this.sortOrder = sortOrder;
        }

        @Override
        public int compare(Map<String, Integer> left, Map<String, Integer> right) {
            for(String key : sortOrder.keySet()){
                RecordComparator rc = new RecordComparator(key, sortOrder.get(key));
                if(rc.compare(left,right) != 0){
                    return rc.compare(left,right);
                }
            }
            return 0;
        }
    }

    public static Map<String, Integer> minByKey(String key, List<Map<String, Integer>> records){
        int minValue = Integer.MAX_VALUE;
        Map<String, Integer> minRecord = null;
        for(Map<String, Integer> record :records){
            int value = record.getOrDefault(key, 0);
            if(value <= minValue){
                minValue = value;
                minRecord = record;
            }
        }
        return minRecord;
    }
    public static Map<String, Integer> firstByKey(String key, String direction, List<Map<String, Integer>> records){

        // int minValue = Integer.MAX_VALUE;
        // int maxValue = Integer.MIN_VALUE;
        // Map<String, Integer> minRecord = null;
        // Map<String, Integer> maxRecord = null;
        // for(Map<String, Integer> record :records){
        //   int value = record.getOrDefault(key, 0);
        //   if(value <= minValue){
        //     minValue = value;
        //     minRecord = record;
        //   }
        //   if(value >= maxValue){
        //     maxValue = value;
        //     maxRecord = record;
        //   }
        // }
        // if("asc".equals(direction)){
        //   return minRecord;
        // }else{
        //   return maxRecord;
        // }

        PriorityQueue<Map<String, Integer>> pq = new PriorityQueue<>( new RecordComparator(key, direction));

        // for(Map<String, Integer> record :records){
        pq.addAll(records);
        // }

        return pq.poll();
    }

    // test code follows...
    public static Map<String, Integer> firstBySortOrder(LinkedHashMap<String, String> sortOrder, List<Map<String, Integer>> records){
        PriorityQueue<Map<String, Integer>> pq = new PriorityQueue<>( new SortOrderComparator(sortOrder));
        pq.addAll(records);
        return pq.poll();
    }

    public static void main(String[] args) {
        testMinByKey();
        testFirstByKey();
        testRecordComparator();
        testFirstBySortOrder();
    }


    public static <T> void assertEqual(T expected, T actual) {
        if (expected == null && actual == null || actual != null && actual.equals(expected)) {
            System.out.println("PASSED");
        } else {
            throw new AssertionError("Expected:\n  " + expected + "\nActual:\n  " + actual + "\n");
        }
    }

    public static void testMinByKey() {
        List<Map<String, Integer>> example1 = Arrays.asList(
                Maps.of("a", 1, "b", 2),
                Maps.of("a", 2)
        );
        List<Map<String, Integer>> example2 = Arrays.asList(example1.get(1), example1.get(0));
        List<Map<String, Integer>> example3 = Arrays.asList(Maps.of());
        List<Map<String, Integer>> example4 = Arrays.asList(
                Maps.of("a", -1),
                Maps.of("b", -1)
        );

        System.out.println("minByKey");
        assertEqual(example1.get(0), minByKey("a", example1));
        assertEqual(example2.get(1), minByKey("a", example2));
        assertEqual(example1.get(1), minByKey("b", example1));
        assertEqual(example3.get(0), minByKey("a", example3));
        assertEqual(example4.get(1), minByKey("b", example4));
    }

    public static void testFirstByKey() {
        List<Map<String, Integer>> example1 = Arrays.asList(Maps.of("a", 1));
        List<Map<String, Integer>> example2 = Arrays.asList(
                Maps.of("b", 1),
                Maps.of("b", -2),
                Maps.of("a", 10)
        );
        List<Map<String, Integer>> example3 = Arrays.asList(
                Maps.of(),
                Maps.of("a", 10, "b", -10),
                Maps.of(),
                Maps.of("a", 3, "c", 3)
        );

        System.out.println("firstByKey");
        assertEqual(example1.get(0), firstByKey("a", "asc", example1));
        assertEqual(example2.get(0), firstByKey("a", "asc", example2));  // example2.get(1) ok too
        assertEqual(example2.get(2), firstByKey("a", "desc", example2));
        assertEqual(example2.get(1), firstByKey("b", "asc", example2));
        assertEqual(example2.get(0), firstByKey("b", "desc", example2));
        assertEqual(example3.get(1), firstByKey("a", "desc", example3));
    }

    public static void testRecordComparator() {
        RecordComparator cmp = new RecordComparator("a", "asc");
        Map<String, Integer> a1 = Maps.of("a", 1);
        Map<String, Integer> a2 = Maps.of("a", 2);
        System.out.println("RecordComparator");
        assertEqual(-1, cmp.compare(a1, a2));
        assertEqual(1, cmp.compare(a2, a1));
        assertEqual(0, cmp.compare(a1, a1));
    }

    public static void testFirstBySortOrder() {
        List<Map<String, Integer>> example1 = Arrays.asList(Maps.of("a", 5), Maps.of("a", 6));
        List<Map<String, Integer>> example2 = Arrays.asList(
                Maps.of("a", -5, "b", 10),
                Maps.of("a", -4, "b", 10)
        );

        System.out.println("firstBySortOrder");
        assertEqual(example1.get(1), firstBySortOrder(Maps.ordered("a", "desc"), example1));
        assertEqual(
                example2.get(0),
                firstBySortOrder(Maps.ordered("b", "asc", "a", "asc"), example2)
        );
        assertEqual(
                example2.get(1),
                firstBySortOrder(Maps.ordered("a", "desc", "b", "asc"), example2)
        );
    }

    // helper code for maps...

    static class Maps {
        public static <K, V> Map<K, V> of() {
            return new HashMap<K, V>();
        }

        public static <K, V> Map<K, V> of(K k1, V v1) {
            Map<K, V> map = new HashMap<K, V>();
            map.put(k1, v1);
            return map;
        }

        public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
            Map<K, V> map = new HashMap<K, V>();
            map.put(k1, v1);
            map.put(k2, v2);
            return map;
        }

        public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
            Map<K, V> map = new HashMap<K, V>();
            map.put(k1, v1);
            map.put(k2, v2);
            map.put(k3, v3);
            return map;
        }

        public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
            Map<K, V> map = new HashMap<K, V>();
            map.put(k1, v1);
            map.put(k2, v2);
            map.put(k3, v3);
            map.put(k4, v4);
            return map;
        }

        public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
            Map<K, V> map = new HashMap<K, V>();
            map.put(k1, v1);
            map.put(k2, v2);
            map.put(k3, v3);
            map.put(k4, v4);
            map.put(k5, v5);
            return map;
        }

        public static <K, V> LinkedHashMap<K, V> ordered(K k1, V v1) {
            LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
            map.put(k1, v1);
            return map;
        }

        public static <K, V> LinkedHashMap<K, V> ordered(K k1, V v1, K k2, V v2) {
            LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
            map.put(k1, v1);
            map.put(k2, v2);
            return map;
        }

        public static <K, V> LinkedHashMap<K, V> ordered(K k1, V v1, K k2, V v2, K k3, V v3) {
            LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
            map.put(k1, v1);
            map.put(k2, v2);
            map.put(k3, v3);
            return map;
        }
    }
}
