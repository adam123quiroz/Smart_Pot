package com.example.smartpot.thread;

import java.math.BigInteger;

public class SeriesTaylor {
    private long n;
    private double x;

    public SeriesTaylor(long n, double x) {
        this.n = n;
        this.x = x;
    }

    public SeriesTaylor() {
    }

    public long getN() {
        return n;
    }

    public void setN(long n) {
        this.n = n;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getSeries() {
        long denominator = 2 * n + 1;
        double sen = (Math.pow((-1),n) * Math.pow(x, (2 * n + 1))) /
                (factorial(BigInteger.valueOf(denominator)).longValue());
        return sen;
    }

    private BigInteger factorial(BigInteger number) {
        BigInteger result = BigInteger.valueOf(1);

        for (long factor = 2; factor <= number.longValue(); factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }

        return result;
    }
}
