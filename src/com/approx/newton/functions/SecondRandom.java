package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.ArrayList;
import java.util.List;

public class SecondRandom implements Function{
    //(1.5x^4 + 28sqrt(y)*x^3 +(15x^(3/2))^4 + 2x + y)^2 + y + 33x
    @Override
    public double apply(Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        return Math.pow(1.5*x*x*x*x + 28*Math.sqrt(y)*x*x*x + Math.pow(15*Math.pow(x, (3.0/2)), 4.0) + 2*x + y, 2.0) + y + 33 * x;
    }

    @Override
    public Matrix hessian(Vector values) {
        final List<List<Double>> m = new ArrayList<>();
        final double x = values.get(0);
        m.add(new ArrayList<>());
        m.add(new ArrayList<>());
        final double y = values.get(1);
        m.get(0).add(2*Math.pow(303750*x*x*x*x*x + 6*x*x*x + 84*x*x*Math.sqrt(y) + 2, 2) + 2*(1518750 * x*x*x*x + 18*x*x + 168 * x * Math.sqrt(y))*(50625 * x*x*x*x*x*x + 1.5 * x*x*x*x + 28*x*x*x*Math.sqrt(y) + 2*x + y));
        m.get(0).add(2*(14*x*x*x/Math.sqrt(y) + 1)*(303750*x*x*x*x*x +6*x*x*x + 84*x*x*Math.sqrt(y) + 2) + (84*x*x*(1.5*x*x*x*x + 28*Math.sqrt(y)*x*x*x + Math.pow(15*Math.pow(x, (3.0/2)), 4.0) + 2*x + y))/(Math.sqrt(y)));
        m.get(1).add(2*(14*x*x*x/Math.sqrt(y) + 1)*(303750*x*x*x*x*x +6*x*x*x + 84*x*x*Math.sqrt(y) + 2) + (84*x*x*(1.5*x*x*x*x + 28*Math.sqrt(y)*x*x*x + Math.pow(15*Math.pow(x, (3.0/2)), 4.0) + 2*x + y))/(Math.sqrt(y)));
        m.get(1).add(2 * (14*x*x*x/Math.sqrt(y) + 1)*(14*x*x*x/Math.sqrt(y) + 1) - (14*x*x*x*(1.5*x*x*x*x + 28*Math.sqrt(y)*x*x*x + Math.pow(15*Math.pow(x, (3.0/2)), 4.0) + 2*x + y))/(Math.pow(y, 3/2.0)));
        return new Matrix(m);
    }

    @Override
    public Vector grad(Vector values) {
        final double x = values.get(0);
        final double y = values.get(1);
        final List<Double> list = new ArrayList<>();
        list.add(2*(1.5*x*x*x*x + 28*Math.sqrt(y)*x*x*x + Math.pow(15*Math.pow(x, (3.0/2)), 4.0) + 2*x + y)
                *(6.0*x*x*x + 84.0*Math.sqrt(y)*x*x + 15*15*15*15*6*Math.pow(x, 5) + 2) + 33);
        list.add(2*(1.5*x*x*x*x + 28*Math.sqrt(y)*x*x*x + Math.pow(15*Math.pow(x, (3.0/2)), 4.0) + 2*x + y)*
                (14*x*x*x/Math.sqrt(y) + 1) + 1);
        return new Vector(list);
    }
}
