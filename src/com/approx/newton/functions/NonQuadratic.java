package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.ArrayList;
import java.util.List;

public class NonQuadratic implements Function {
    //x^4+2x^2y-33x^2+2xy^2-20x+y^4-19y^2-34y+389
    @Override
    public double apply(final Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        return (x * x * x * x + 2.0 * x * x * y - 33.0 * x * x + 2.0 * x * y * y - 20.0 * x +
                y * y * y * y - 19.0 * y * y - 34.0 * y + 389);
    }

    @Override
    public Matrix hessian(final Vector values) {
        final double x = values.get(0);
        final List<List<Double>> m = new ArrayList<>();
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        final double y = values.get(1);
        m.get(0).add(12.0 * x * x + 4.0 * y - 66.0);
        m.get(0).add(4 * x + 4.0 * y);
        m.get(1).add(4 * x + 4 * y);
        m.get(1).add(4 * x + 12 * y * y - 38);
        return new Matrix(m);
    }

    @Override
    public Vector grad(final Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        final List<Double> list = new ArrayList<>();
        list.add(4.0 * x * x * x + 4.0 * x * y - 66.0 * x + 2.0 * y * y - 20.0);
        list.add(2.0 * x * x + 4.0 * x * y + 4.0 * y * y * y - 38.0 * y - 34.0);
        return new Vector(list);
    }
}
