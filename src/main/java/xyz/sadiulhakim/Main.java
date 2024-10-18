package xyz.sadiulhakim;

import xyz.sadiulhakim.expression.MathExpression;

public class Main {
    public static void main(String[] args) {
        long num = new MathExpression.Builder(10)
                .add(10)
                .store()
                .add(10)
                .recall(true)
                .build()
                .toLong();
        System.out.println(num);
    }
}