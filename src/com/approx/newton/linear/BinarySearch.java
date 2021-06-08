package com.approx.newton.linear;


import com.approx.newton.functions.Function;

public class BinarySearch {

    private double left;
    private double right;
    private final double epsilon;
    private final Function function;

    public BinarySearch(final Function function, double left, double right, double epsilon) {
        this.left = left;
        this.right = right;
        this.epsilon = epsilon;
        this.function = function;
    }

    //TODO Егор, прикрути к бинпоиску правильную реализацию
    public Double start() {
        double middle = (left + right) / 2;
        while ((right - left) >= epsilon) {
            if (function.apply(right) > function.apply(left)) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return (left + right) / 2.0;
    }

}