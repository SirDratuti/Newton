package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.ArrayList;
import java.util.List;

public class FirstRandom implements Function{
    //2x^2 + 2y^2 - 8y
    @Override
    public double apply(Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        return 2*x*x + 2*y*y - 8*y;
    }

    @Override
    public Matrix hessian(Vector values) {
        final List<List<Double>> m = new ArrayList<>();
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        m.get(0).add(2.0);
        m.get(0).add(0.0);
        m.get(1).add(0.0);
        m.get(1).add(2.0);
        return new Matrix(m);
    }

    @Override
    public Vector grad(Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        final List<Double> list = new ArrayList<>();
        list.add(2*x);
        list.add(2*y - 8);
        return new Vector(list);
    }
}
