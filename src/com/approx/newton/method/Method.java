package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;


public interface Method {

    MethodStats solve (final Vector values, final Function function, final double eps);
}
