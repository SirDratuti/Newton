package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.ArrayList;
import java.util.List;

public class QuasiThird implements Function {
    //(x_1 + 10x_2)^2 + 5(x_3 - x_4)^2 + (x_2 - 2x_3)^4 + 10(x_1 - x_4)^4
    @Override
    public double apply(Vector values) {
        final double x_1 = values.get(0);
        final double x_2 = values.get(1);
        final double x_3 = values.get(2);
        final double x_4 = values.get(3);
        return (x_1 + 10*x_2)*(x_1 + 10*x_2)
                + 5*(x_3 - x_4)*(x_3 - x_4)
                + (x_2 - 2*x_3)*(x_2 - 2*x_3)*(x_2 - 2*x_3)*(x_2 - 2*x_3)
                + 10*(x_1 - x_4)*(x_1 - x_4)*(x_1 - x_4)*(x_1 - x_4);
    }

    @Override
    public Matrix hessian(Vector values) {
        final List<List<Double>> m = new ArrayList<>();
        final double x_1 = values.get(0);
        final double x_2 = values.get(1);
        final double x_3 = values.get(2);
        final double x_4 = values.get(3);
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        m.get(0).add(120.0*(x_1 - x_4)*(x_1 - x_4) + 2.0);
        m.get(0).add(20.0);
        m.get(0).add(0.0);
        m.get(0).add(-120.0*(x_1 - x_4)*(x_1 - x_4));
        m.get(1).add(20.0);
        m.get(1).add(12.0*(x_2 -2 * x_3)*(x_2 -2 * x_3) + 200.0);
        m.get(1).add(-24.0*(x_2 - 2*x_3)*(x_2 - 2*x_3));
        m.get(1).add(0.0);
        m.get(2).add(0.0);
        m.get(2).add(-24.0*(x_2 - 2*x_3)*(x_2 - 2*x_3));
        m.get(2).add(48.0*(x_2 - 2*x_3)*(x_2 - 2*x_3) + 10);
        m.get(2).add(-10.0);
        m.get(3).add(-120.0*(x_1 - x_4)*(x_1 - x_4));
        m.get(3).add(0.0);
        m.get(3).add(-10.0);
        m.get(3).add(120.0*(x_1 - x_4)*(x_1 - x_4) + 10.0);
        return new Matrix(m);
    }

    @Override
    public Vector grad(Vector values) {
        final double x_1 = values.get(0);
        final double x_2 = values.get(1);
        final double x_3 = values.get(2);
        final double x_4 = values.get(3);
        final List<Double> list = new ArrayList<>();
        double v = 40 * (x_1 - x_4) * (x_1 - x_4) * (x_1 - x_4);
        list.add(2 * (x_1 + 10*x_2) + v);
        list.add(20 * (x_1 + 10*x_2) + 4*(x_2 - 2*x_3)*(x_2 - 2*x_3)*(x_2 - 2*x_3));
        list.add(10*(x_3 - x_4) - 8*(x_2 - 2*x_3)*(x_2 - 2*x_3)*(x_2 - 2*x_3));
        list.add(-10 * (x_3 - x_4) - v);
        return new Vector(list);
    }
}
