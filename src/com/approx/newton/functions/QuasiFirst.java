package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.ArrayList;
import java.util.List;

public class QuasiFirst implements Function {
    //100(y-x^2)^2 + (1-x)^2
    @Override
    public double apply(final Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        return (100.0 * (y - x * x) * (y - x * x) + (1 - x) * (1 - x));
    }

    @Override
    public Matrix hessian(final Vector values) {
        final List<List<Double>> m = new ArrayList<>();
        final double x = values.get(0);
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        final double y = values.get(1);
        m.get(0).add(-400.0 * y + 1200.0 * x * x + 2);
        m.get(0).add(-400.0 * x);
        m.get(1).add(-400.0 * x);
        m.get(1).add(200.0);
        return new Matrix(m);
    }

    @Override
    public Vector grad(final Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        final List<Double> list = new ArrayList<>();
        list.add(400.0 * x * x * x - 400.0 * x * y + 2.0 * x - 2.0);
        list.add(200.0 * y - 200.0 * x * x);
        return new Vector(list);
    }
}
