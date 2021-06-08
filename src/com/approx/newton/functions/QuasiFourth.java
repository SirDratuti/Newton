package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.ArrayList;
import java.util.List;

public class QuasiFourth implements Function {

    @Override
    public double apply(final Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        final double v = ((x - 1.0) / 2.0) * ((x - 1.0) / 2.0);
        final double w = ((y - 1.0) / 3.0) * ((y - 1.0) / 3.0);
        return (100.0
                - (2.0 / (1.0 + v + w))
                - (1.0 / (1.0 + v + w))
        );
    }

    @Override
    public Matrix hessian(final Vector values) {
        final List<List<Double>> m = new ArrayList<>();
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        final double x = values.get(0);
        final double y = values.get(1);
        final double znamFirst = ((x - 2.0) * (x - 2.0) / 4.0 + (y - 1.0) * (y - 1.0) / 9.0 + 1.0);
        final double znamSecond = ((x - 1.0) * (x - 1.0) / 4.0 + (y - 1.0) * (y - 1.0) / 9.0 + 1.0);
        final double tl = (-0.5 * (x - 2.0) * (x - 2.0)) / (znamFirst * znamFirst * znamFirst) +
                (0.5 / (znamFirst * znamFirst)) +
                (1.0 / (znamSecond * znamSecond)) -
                ((x - 1.0) * (x - 1.0) / (znamSecond * znamSecond * znamSecond));
        final double tr = ((-2.0 * (x - 2.0) * (y - 1.0) / 9.0) / (znamFirst * znamFirst * znamFirst) -
                (4.0 * (x - 1.0) * (y - 1.0) / 9.0) / (znamSecond * znamSecond * znamSecond));
        final double br = ((-0.0987654 * (y - 1.0) * (y - 1.0)) / (znamFirst * znamFirst * znamFirst) -
                (0.197531 * (y - 1.0) * (y - 1.0)) / (znamSecond * znamSecond * znamSecond) +
                (2.0 / 0.9) / (znamFirst * znamFirst) +
                (4.0 / 0.9) / (znamSecond * znamSecond));
        m.get(0).add(tl);
        m.get(0).add(tr);
        m.get(1).add(tr);
        m.get(1).add(br);
        return new Matrix(m);
    }

    @Override
    public Vector grad(final Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        final double v = ((0.5 * x - 0.5) * (0.5 * x - 0.5)
                + (y / 3.0 - 1.0 / 3.0) * (y / 3.0 - 1.0 / 3.0) + 1.0);
        final List<Double> list = new ArrayList<>();
        list.add((1.5 * x - 1.5) / (v * v));
        list.add((y * 2.0 / 3.0 - 2.0 / 3.0) / (v * v));
        return new Vector(list);
    }
}
