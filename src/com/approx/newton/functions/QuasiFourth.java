package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuasiFourth implements Function {

    @Override
    public double apply(final @NotNull Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        final double v = ((x - 1.0) / 2.0) * ((x - 1.0) / 2.0);
        final double v1 = ((x - 2.0) / 2.0) * ((x - 2.0) / 2.0);
        final double w = ((y - 1.0) / 3.0) * ((y - 1.0) / 3.0);
        return (100.0
                - (2.0 / (1.0 + v + w))
                - (1.0 / (1.0 + v1 + w))
        );
    }

    @Override
    public @NotNull Matrix hessian(final @NotNull Vector values) {
        final List<List<Double>> m = new ArrayList<>();
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        final double x = values.get(0);
        final double y = values.get(1);
        final double v = (0.25 * (x - 2.0) * (x - 2.0) + (y - 1.0) * (y - 1.0) / 9.0 + 1.0);
        final double w = (0.25 * (x - 1.0) * (x - 1.0) + (y - 1.0) * (y - 1.0) / 9.0 + 1.0);
        final double tl = ((-0.5 * (x - 2.0) * (x - 2.0)) / (v * v * v) +
                (0.5) / (v * v) + (1.0) / (w * w) - ((x - 1.0) * (x - 1.0)) / (w * w * w));
        final double tr = ((-2.0 * (x - 2.0) * (y - 1.0) / 9.0) / (v * v * v)
                - (4.0 * (x - 1.0) * (y - 1.0) / 9.0) / (w * w * w));
        final double br = ((-0.0987654 * (y - 1.0) * (y - 1.0)) / (v * v * v) -
                (0.197531 * (y - 1.0) * (y - 1.0)) / (w * w * w) +
                (2.0 / 9.0) / (v * v) +
                (4.0 / 9.0) / (w * w));
        m.get(0).add(tl);
        m.get(0).add(tr);
        m.get(1).add(tr);
        m.get(1).add(br);
        return new Matrix(m);
    }

    @Override
    public @NotNull Vector grad(final @NotNull Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        final double v = (0.25 * (x - 2.0) * (x - 2.0) + (y - 1.0) * (y - 1.0) / 9.0 + 1.0);
        final double w = (0.25 * (x - 1.0) * (x - 1.0) + (y - 1.0) * (y - 1.0) / 9.0 + 1.0);
        final List<Double> list = new ArrayList<>();
        list.add((0.5 * x - 1.0) / (v * v) + (x - 1.0) / (w * w));
        list.add((y - 1.0) * ((4.0 / 9.0) / (w * w) + (2.0 / 9.0) / (v * v)));
        return new Vector(list);
    }
}
