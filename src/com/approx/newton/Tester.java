package com.approx.newton;

import com.approx.newton.functions.Function;
import com.approx.newton.functions.NonQuadratic;
import com.approx.newton.functions.QuasiFirst;
import com.approx.newton.functions.TestFunction;
import com.approx.newton.method.*;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        final Function func = new QuasiFirst();
        final Method newtonMethod = new DFP();
        final List<Double> start = new ArrayList<>();
        start.add(3.0);
        start.add(5.0);
        final MethodStats result =
                newtonMethod.solve(new Vector(start),
                        func, 1e-10);
        for (int i = 0; i < result.x().size(); i++) {
            System.out.print(result.x().get(i) + " ");
        }
        System.out.println();
        System.out.println(result.iterations());
        System.out.println(func.apply(result.x()));
    }
}
