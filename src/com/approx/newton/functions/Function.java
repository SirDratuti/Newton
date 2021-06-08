package com.approx.newton.functions;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;


public interface Function {

    double apply(final Vector values);

    Matrix hessian(final Vector values);

    Vector grad(final Vector values);
}
