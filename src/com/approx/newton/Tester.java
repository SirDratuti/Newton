package com.approx.newton;

import com.approx.newton.functions.*;
import com.approx.newton.method.*;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        final Function func = new QuasiFirst();
        final Method newtonMethod = new Newton();
        final List<Double> start = new ArrayList<>();
        start.add(4.0);
        start.add(1.0);
        final MethodStats result =
                newtonMethod.solve(new Vector(start),
                        func, 1e-10);
        System.out.println(result.iterations());
    }
}
