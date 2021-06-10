package com.approx.newton.method;

import com.approx.newton.objects.Vector;
import org.jetbrains.annotations.NotNull;

public interface Printable {

    default void print(final @NotNull Vector x) {
        for (int i = 0; i < x.size(); i++) {
            System.out.println(x.get(0) + ", " + x.get(1));
        }
        System.out.println();
    }
}
