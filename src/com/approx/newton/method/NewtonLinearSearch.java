package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.linear.BinarySearch;
import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;

import static com.approx.newton.utils.Maths.*;

public class NewtonLinearSearch implements Method {

    @Override
    public MethodStats solve(final Vector values,
                             final Function function,
                             final double eps) {
        Vector x = new Vector(values.values());
        int cnt = 0;
        print(x);
        while (true) {
            cnt++;
            final Vector grad = function.grad(x);
            final Matrix hessian = function.hessian(x);
            final Vector d = hessian.gauss(multiply(grad, -1.0));
            double r = new BinarySearch(function, -20, 20, eps, x, d).start();
            final Vector s = multiply(d, r);
            x = sum(x, s);
            print(x);
            if (norm(s) <= eps) {
                return new MethodStats(x, cnt);
            }
        }
    }
}
