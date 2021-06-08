package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.Maths;
import com.approx.newton.utils.MethodStats;

public class Newton implements Method {

    @Override
    public MethodStats solve(final Vector values,
                             final Function function,
                             final double eps) {
        Vector x = new Vector(values.values());
        int cnt = 0;
        while (true) {
            cnt++;
            final Vector grad = function.grad(values);
            final Matrix hessian = function.hessian(values);
            final Vector s = hessian.gauss(
                    Maths.multiply(grad, -1.0));
            x = Maths.sum(x, s);
            if (Maths.norm(x) <= eps) {
                return new MethodStats(x, cnt);
            }
        }
    }
}
