package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;

public class DFP extends AbstractQuasiMethod implements Method {

    @Override
    public MethodStats solve(final Vector values,final Function function,final double eps) {
        return solve(values, function, eps, DFPMethod);
    }
}
