package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;


public interface Function {

    double apply(final Vector<Double> values);

    Matrix hessian(final Vector<Double> values);

    Vector<Double> grad(final Vector<Double> values);

}
