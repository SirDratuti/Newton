package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.ArrayList;
import java.util.List;

public class TestFunction implements Function {
    //x^2+y^2-1.2xy
    @Override
    public double apply(final Vector values) {
        double x = values.get(0);
        double y = values.get(1);
        return (x * x + y * y - 1.2 * x * y);
    }

    @Override
    public Matrix hessian(final Vector values) {
        final List<List<Double>> m = new ArrayList<>();
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        m.get(0).add(2.0);
        m.get(0).add(-1.2);
        m.get(1).add(-1.2);
        m.get(1).add(2.0);
        return new Matrix(m);
    }

    @Override
    public Vector grad(final Vector values) {
        double x = values.get(0);
        double y = values.get(1);
        final List<Double> first = new ArrayList<>();
        first.add(2 * x - 1.2 * y);
        first.add(2 * y - 1.2 * x);
        return new Vector(first);
    }
}
