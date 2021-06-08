package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.ArrayList;
import java.util.List;

public class QuasiSecond implements Function{
    //(x^2 + y - 11)^2 + (x + y^2 - 7)^2
    @Override
    public double apply(Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        return (x*x + y - 11) * (x*x + y - 11) + (x + y*y - 7) * (x + y*y - 7);
    }

    @Override
    public Matrix hessian(Vector values) {
        final List<List<Double>> m = new ArrayList<>();
        final double x = values.get(0);
        final double y = values.get(1);
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        m.get(0).add(4.0 * (x*x + y - 11.0) + 8.0*x*x + 2.0);
        m.get(0).add(4.0*x + 4.0*y);
        m.get(1).add(4.0*x + 4.0*y);
        m.get(1).add(4.0 * (x + y*y - 7.0) + 8.0*y*y + 2.0);
        return new Matrix(m);
    }

    @Override
    public Vector grad(Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        final List<Double> list = new ArrayList<>();
        list.add(2.0 * (2.0*x * (x*x + y - 11.0) + x + y*y - 7.0));
        list.add(2.0 * (x*x + 2*y * (x + y*y - 7) + y - 11));
        return new Vector(list);
    }
}
