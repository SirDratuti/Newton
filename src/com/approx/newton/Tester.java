package com.approx.newton;

import com.approx.newton.functions.*;
import com.approx.newton.method.*;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        List<Double> start = new ArrayList<>();
        start.add(0.0);
        start.add(0.0);
        List<Function> list = new ArrayList<>();
        //list.add(new QuasiFirst());
        //list.add(new QuasiSecond());
        //list.add(new QuasiThird());
        list.add(new QuasiFourth());
        System.out.println("NewtonDescent y");
        for (Function func : list) {
            System.out.println(func.getClass());
            Method newtonMethod = new NewtonDescent();
            final MethodStats result =
                    newtonMethod.solve(new Vector(start),
                            func, 1e-7);
            System.out.println();
            System.out.println("iterations: " + result.iterations());
            System.out.println();
        }
    }
}