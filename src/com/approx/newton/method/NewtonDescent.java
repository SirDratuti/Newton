package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.linear.BinarySearch;
import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.Maths;
import com.approx.newton.utils.MethodStats;

public class NewtonDescent implements Method {

    @Override
    public MethodStats solve(final Vector<Double> values,
                             final Function function,
                             final double eps) {
        int cnt = 0;
        Vector<Double> x = new Vector<>(values.values());
        Vector<Double> d = Maths.multiply(function.grad(x), -1);
        double r = new BinarySearch(function, -100, 100, eps).start();
        Vector<Double> s = Maths.multiply(d, r);
        x = Maths.sum(x, s);
        while (true) {
            cnt++;
            final Vector<Double> grad = function.grad(x);
            final Matrix hessian = function.hessian(x);
            s = hessian.gauss(Maths.multiply(grad, -1));
            if (Maths.scalarMultiply(s, grad) < 0) {
                d = s;
            } else {
                d = Maths.multiply(grad, -1);
            }
            r = new BinarySearch(function, -100, 100, eps).start();
            s = Maths.multiply(d, r);
            x = Maths.sum(x, s);
            if (Maths.norm(s) < eps) {
                return new MethodStats(x, cnt);
            }
        }
    }
}
