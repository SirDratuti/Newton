package com.approx.newton.utils;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class Maths {

    public static @NotNull Vector sum(final @NotNull Vector vector, final @NotNull Vector other) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            values.add(vector.get(i) + other.get(i));
        }
        return new Vector(values);
    }

    public static @NotNull Vector sub(final @NotNull Vector vector, final @NotNull Vector other) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            values.add(vector.get(i) - other.get(i));
        }
        return new Vector(values);
    }

    public static Double scalarMultiply(final @NotNull Vector vector, final @NotNull Vector other) {
        double result = 0.0;
        for (int i = 0; i < vector.size(); i++) {
            result += vector.get(i) * other.get(i);
        }
        return result;
    }

    public static @NotNull Vector multiply(final @NotNull Vector vector, final double constant) {
        final List<Double> list = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            list.add(vector.get(i) * constant);
        }
        return new Vector(list);
    }

    public static double norm(final @NotNull Vector vector) {
        double result = 0.0;
        for (int i = 0; i < vector.size(); i++) {
            result += vector.get(i) * vector.get(i);
        }
        return Math.sqrt(result);
    }

    public static @NotNull Matrix matrixI(final int size) {
        return diagonal(size, 1.0);
    }

    public static @NotNull Matrix diagonal(final int size, final double value) {
        final List<List<Double>> list = emptyList(size);
        for (int i = 0; i < size; i++) {
            list.get(i).set(i, value);
        }
        return new Matrix(list);
    }

    public static @NotNull Matrix matrixMultiply(final @NotNull Matrix matrix, final Double value) {
        final Matrix current = new Matrix(matrix.values());

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                current.set(i, j, matrix.get(i, j) * value);
            }
        }

        return current;
    }

    public static @NotNull Matrix matrixSum(final @NotNull Matrix matrix, final @NotNull Matrix other) {
        final Matrix current = new Matrix(matrix.values());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                current.set(i, j,
                        matrix.get(i, j) + other.get(i, j));
            }
        }
        return current;
    }

    public static @NotNull Matrix matrixSub(final @NotNull Matrix matrix, final @NotNull Matrix other) {
        final Matrix current = new Matrix(matrix.values());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                current.set(i, j,
                        matrix.get(i, j) - other.get(i, j));
            }
        }
        return current;
    }

    public static @NotNull Matrix mulVectors(final @NotNull Vector vector, final @NotNull Vector other) {
        final List<List<Double>> list = emptyList(vector.size());
        for (int i = 0; i < vector.size(); i++) {
            for (int j = 0; j < vector.size(); j++) {
                list.get(i).set(j, vector.get(i) * other.get(j));
            }
        }

        return new Matrix(list);

    }

    public static @NotNull Vector mulMatrixOnVector(final @NotNull Matrix matrix,
                                                    final @NotNull Vector vector) {
        final List<Double> list = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            list.add(0.0);
            for (int j = 0; j < matrix.getRow(i).size(); j++) {
                list.set(i, list.get(i) + vector.get(j) * matrix.get(i, j));
            }
        }
        return new Vector(list);
    }

    private static @NotNull List<List<Double>> emptyList(final int size) {
        final List<List<Double>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                list.get(i).add(0.0);
            }
        }
        return list;
    }

}
