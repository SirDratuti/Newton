package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;

public class Powell extends AbstractQuasiMethod implements Method {

    @Override
    public MethodStats solve(Vector values, Function function, double eps) {
        return solve(values, function, eps, PowellMethod);
    }
}
