package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.linear.BinarySearch;
import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;

import static com.approx.newton.utils.Maths.*;

public class NewtonDescent implements Method {

    @Override
    public MethodStats solve(final Vector values,
                             final Function function,
                             final double eps) {
        int cnt = 0;
        Vector x = new Vector(values.values());
        Vector d = multiply(function.grad(x), -1);
        double r = new BinarySearch(function, -100, 100, eps, x, d).start();
        Vector s = multiply(d, r);
        x = sum(x, s);
        while (true) {
            cnt++;
            final Vector grad = function.grad(x);
            final Matrix hessian = function.hessian(x);
            s = hessian.gauss(multiply(grad, -1));
            if (scalarMultiply(s, grad) < 0) {
                d = s;
            } else {
                d = multiply(grad, -1);
            }
            r = new BinarySearch(function, -100, 100, eps, x, d).start();
            s = multiply(d, r);
            x = sum(x, s);
            if (norm(s) < eps) {
                return new MethodStats(x, cnt);
            }
        }
    }
}
