package xyz.sadiulhakim.expression;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MathExpression {
    private final double number;

    private MathExpression(Builder builder) {
        this.number = builder.NUMBER;
    }

    public double get() {
        return number;
    }

    public long toLong() {
        try {
            return (long) number;
        } catch (Exception ex) {
            return 0;
        }
    }

    public static class Builder {
        private double NUMBER;
        private double memory = 0;

        private final ReadWriteLock LOCK = new ReentrantReadWriteLock();

        public Builder() {
            NUMBER = 0;
        }

        public Builder(double num) {
            NUMBER = num;
        }

        public Builder add(double num) {
            LOCK.writeLock().lock();
            NUMBER += num;
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder minus(double num) {
            LOCK.writeLock().lock();
            NUMBER -= num;
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder rand(long seed) {
            if (NUMBER != 0) {
                throw new RuntimeException("Number is bigger than zero!");
            }

            LOCK.writeLock().lock();
            Random random = new Random();
            NUMBER = random.nextDouble(seed);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder pi() {
            if (NUMBER != 0) {
                throw new RuntimeException("Number is bigger than zero!");
            }

            LOCK.writeLock().lock();
            NUMBER = Math.PI;
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder e() {
            if (NUMBER != 0) {
                throw new RuntimeException("Number is bigger than zero!");
            }

            LOCK.writeLock().lock();
            NUMBER = Math.E;
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder randomInRange(double min, double max) {

            if (NUMBER != 0) {
                throw new RuntimeException("Number is bigger than zero!");
            }

            LOCK.writeLock().lock();
            Random random = new Random();
            NUMBER = min + (max - min) * random.nextDouble();
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder toDegrees() {
            LOCK.writeLock().lock();
            NUMBER = Math.toDegrees(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder max(double num) {
            LOCK.writeLock().lock();
            NUMBER = Math.max(NUMBER, num);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder min(double num) {
            LOCK.writeLock().lock();
            NUMBER = Math.min(NUMBER, num);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder multiply(double num) {
            LOCK.writeLock().lock();
            NUMBER *= num;
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder divide(double num) {

            if (num == 0) {
                return this;
            }

            LOCK.writeLock().lock();
            try {
                NUMBER /= num;
            } finally {
                LOCK.writeLock().unlock();
            }
            return this;
        }

        public Builder mod(double num) {
            LOCK.writeLock().lock();
            NUMBER %= num;
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder pow(double num) {
            LOCK.writeLock().lock();
            NUMBER = Math.pow(NUMBER, num);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder sqrt() {
            LOCK.writeLock().lock();
            NUMBER = Math.sqrt(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder round() {
            LOCK.writeLock().lock();
            NUMBER = Math.round(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder floor() {
            LOCK.writeLock().lock();
            NUMBER = Math.floor(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder ceil() {
            LOCK.writeLock().lock();
            NUMBER = Math.ceil(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder abs() {
            LOCK.writeLock().lock();
            NUMBER = Math.abs(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder cbrt() {
            LOCK.writeLock().lock();
            NUMBER = Math.cbrt(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder log() {
            LOCK.writeLock().lock();
            NUMBER = Math.log(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder log10() {
            LOCK.writeLock().lock();
            NUMBER = Math.log10(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder sin() {
            LOCK.writeLock().lock();
            NUMBER = Math.sin(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder cos() {
            LOCK.writeLock().lock();
            NUMBER = Math.cos(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder tan() {
            LOCK.writeLock().lock();
            NUMBER = Math.tan(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder sinh() {
            LOCK.writeLock().lock();
            NUMBER = Math.sinh(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder cosh() {
            LOCK.writeLock().lock();
            NUMBER = Math.cosh(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder tanh() {
            LOCK.writeLock().lock();
            NUMBER = Math.tanh(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder exp() {
            LOCK.writeLock().lock();
            NUMBER = Math.exp(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder toRadians() {
            LOCK.writeLock().lock();
            NUMBER = Math.toRadians(NUMBER);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder store() {
            LOCK.writeLock().lock();
            memory = NUMBER;
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder recall(boolean cleanMemory) {
            LOCK.writeLock().lock();
            NUMBER = memory;

            if (cleanMemory) {
                memory = 0;
            }
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder format(String format) {
            LOCK.writeLock().lock();
            DecimalFormat formater = new DecimalFormat(format);
            String num = formater.format(NUMBER);
            NUMBER = Double.parseDouble(num);
            LOCK.writeLock().unlock();
            return this;
        }

        public Builder format(int decimalPlace) {
            LOCK.writeLock().lock();
            String pattern = "." + "#".repeat(decimalPlace);
            DecimalFormat formater = new DecimalFormat(pattern);
            String num = formater.format(NUMBER);
            NUMBER = Double.parseDouble(num);
            LOCK.writeLock().unlock();
            return this;
        }

        public MathExpression build() {
            return new MathExpression(this);
        }
    }
}
