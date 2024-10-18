package xyz.sadiulhakim.util;

import java.util.List;
import java.util.function.BiFunction;

public class MathUtility {
    private MathUtility() {
    }

    public static long operateOnLongList(List<Long> list, BiFunction<Long, Long, Long> function) {
        try {
            long res = list.getFirst();
            for (int i = 1; i < list.size(); i++) {
                res = function.apply(res, list.get(i));
            }

            return res;
        } catch (Exception ex) {
            return 0;
        }
    }

    public static int operate(List<Integer> list, BiFunction<Integer, Integer, Integer> function) {
        try {
            int res = list.getFirst();
            for (int i = 1; i < list.size(); i++) {
                res = function.apply(res, list.get(i));
            }

            return res;
        } catch (Exception ex) {
            return 0;
        }
    }
}
