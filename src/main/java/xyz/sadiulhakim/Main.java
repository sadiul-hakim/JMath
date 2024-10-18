package xyz.sadiulhakim;

import xyz.sadiulhakim.util.MathUtility;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Long> list = List.of(1L, 2L, 3L);
        long operate = MathUtility.operateOnLongList(list, (a, b) -> a * b);
        System.out.println(operate);
    }
}