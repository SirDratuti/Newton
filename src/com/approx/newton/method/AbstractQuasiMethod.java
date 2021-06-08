package com.approx.newton.method;

import com.approx.newton.functions.Function;
import com.approx.newton.linear.BinarySearch;
import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;
import com.approx.newton.utils.MethodStats;

import static com.approx.newton.utils.Maths.*;

public class AbstractQuasiMethod implements QuasiMethod {

    public static int DFPMethod = 1;
    public static int PowellMethod = 2;

    @Override
    public MethodStats solve(final Vector values,
                             final Function function,
                             final double eps,
                             final int methodType) {
        int cnt = 0;
        Vector x1 = new Vector(values.values());
        Matrix G = matrixI(x1.size());
        Vector w1 = multiply(function.grad(x1), -1);
        Vector p = new Vector(w1.values());
        double r = new BinarySearch(function, -100, 100, eps / 100.0, x1, p).start();
        Vector x2 = sum(x1, multiply(p, r));
        Vector delta = sub(x2, x1);
        x1 = new Vector(x2.values());
        while (true) {
            cnt++;
            Vector w2 = multiply(function.grad(x1), -1);
            Vector deltaW = sub(w2, w1);
            w1 = new Vector(w2.values());
            if (methodType == DFPMethod) {
                final Vector vk = mulMatrixOnVector(G, deltaW);
                G = matrixSub(G, matrixSum(
                        matrixMultiply(
                                mulVectors(delta, delta),
                                (1.0 / scalarMultiply(deltaW, delta))
                        ),
                        matrixMultiply(
                                mulVectors(vk, vk),
                                (1.0 / scalarMultiply(vk, deltaW))
                        )
                ));
            } else if (methodType == PowellMethod) {
                Vector deltaX = sum(
                        delta,
                        mulMatrixOnVector(G, deltaW)
                );
                G = matrixSub(G, matrixMultiply(
                        mulVectors(deltaX, deltaX),
                        (1.0 / scalarMultiply(deltaW, deltaX))
                ));
            } else {
                return new MethodStats(x1, -1);
            }

            p = mulMatrixOnVector(G, w2);
            r = new BinarySearch(function, -100, 100, eps / 100.0, x1, p).start();
            x2 = sum(x1, multiply(p, r));
            delta = sub(x2, x1);
            x1 = new Vector(x2.values());
            print(x2);
            if (norm(delta) < eps) {
                return new MethodStats(x2, cnt);
            }
        }
    }
}
