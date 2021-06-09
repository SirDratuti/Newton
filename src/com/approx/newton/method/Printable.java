package com.approx.newton.method;

import com.approx.newton.objects.Vector;

public interface Printable {

    default void print(final Vector x) {
        //for (int i = 0; i < x.size(); i++) {
            System.out.print(x.get(1) + ", ");
        //}
        //System.out.println();
    }
}
