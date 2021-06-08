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
        return (4 * x * x + y * y + 2 * x + 4 * y + 17);
    }

    @Override
    public Matrix hessian(final Vector values) {
        final List<List<Double>> m = new ArrayList<>();
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        m.get(0).add(8.0);
        m.get(0).add(0.0);
        m.get(1).add(0.0);
        m.get(1).add(2.0);
        return new Matrix(m);
    }

    @Override
    public Vector grad(final Vector values) {
        final List<Double> first = new ArrayList<>();
        first.add(8 * values.get(0) + 2.0);
        first.add(2 * values.get(1) + 4.0);
        return new Vector(first);
    }
}
