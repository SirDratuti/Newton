package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.linear.BinarySearch;
import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.Maths;
import com.approx.newton.utils.MethodStats;

public class NewtonLinearSearch implements Method {

    @Override
    public MethodStats solve(final Vector<Double> values,
                             final Function function,
                             final double eps) {
        Vector<Double> x = new Vector<>(values.values());
        int cnt = 0;
        while (true) {
            cnt++;
            final Vector<Double> grad = function.grad(x);
            final Matrix hessian = function.hessian(x);
            final Vector<Double> d = hessian.gauss(Maths.multiply(grad, -1.0));
            double r = new BinarySearch(function, -100, 100, eps).start();
            final Vector<Double> s = Maths.multiply(d, r);
            x = Maths.sum(x, s);
            if (Maths.norm(s) <= eps) {
                return new MethodStats(x, cnt);
            }
        }
    }
}
