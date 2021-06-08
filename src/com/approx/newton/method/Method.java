package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;


public interface Method extends Printable {

    MethodStats solve(final Vector values, final Function function, final double eps);

    default void print(final Vector x) {
        for (int i = 0; i < x.size(); i++) {
            System.out.print(x.get(i) + " ");
        }
        System.out.println();
    }
}
