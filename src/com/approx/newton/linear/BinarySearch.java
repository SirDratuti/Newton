package com.approx.newton.linear;


import com.approx.newton.functions.Function;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.Maths;

public class BinarySearch {

    private double left;
    private double right;
    private final double beta;
    private final double epsilon;
    private final Function function;
    private final Vector x;
    private final Vector d;

    public BinarySearch(final Function function, double left, double right, double epsilon, Vector x, Vector d) {
        this.left = left;
        this.right = right;
        beta = epsilon;
        this.epsilon = epsilon;
        this.function = function;
        this.x = x;
        this.d = d;
    }

    //TODO Егор, прикрути к бинпоиску правильную реализацию
    public Double start() {
        while ((right - left) >= epsilon) {
            double x1 = (left + right - beta) / 2.0;
            double x2 = (left + right + beta) / 2.0;
            double fx1 = function.apply(Maths.sum(x, Maths.multiply(d,x1)));
            double fx2 = function.apply(Maths.sum(x, Maths.multiply(d,x2)));
            if (fx2 > fx1) {
                right = x2;
            } else {
                left = x1;
            }
        }
        return (left + right) / 2.0;
    }

}