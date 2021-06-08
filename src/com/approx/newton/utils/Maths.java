package com.approx.newton.utils;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.*;
import java.util.stream.Collectors;

public final class Maths {

    public static Vector sum(final Vector vector, final Vector other) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            values.add(vector.get(i) + other.get(i));
        }
        return new Vector(values);
    }

    public static Vector sub(final Vector vector, final Vector other) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            values.add(vector.get(i) - other.get(i));
        }
        return new Vector(values);
    }

    public static Vector elementsMultiply(final Vector vector, final Vector other) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            values.add(vector.get(i) * other.get(i));
        }
        return new Vector(values);
    }

    public static Double scalarMultiply(final Vector vector, final Vector other) {
        double result = 0.0;
        for (int i = 0; i < vector.size(); i++) {
            result += vector.get(i) * other.get(i);
        }
        return result;
    }

    public static Vector multiply(final Vector vector, final double constant) {
        return new Vector(
                vector
                        .values()
                        .stream()
                        .map(it ->
                                it * constant)
                        .collect(Collectors
                                .toList())
        );
    }

    public static double norm(final Vector vector) {
        double result = 0.0;
        for (int i = 0; i < vector.size(); i++) {
            result += vector.get(i) * vector.get(i);
        }
        return Math.sqrt(result);
    }

    public static Matrix matrixI(final int size) {
        return diagonal(size, 1.0);
    }

    public static Matrix diagonal(final int size, final double value) {
        final List<List<Double>> list = Collections.nCopies(size, Collections.nCopies(size, 0.0));
        for (int i = 0; i < size; i++) {
            list.get(i).set(i, value);
        }
        return new Matrix(list);
    }

    public static Vector mulByVector(final Matrix matrix, final Vector vector) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            double sum = 0.0;
            for (int j = 0; j < vector.size(); j++) {
                sum += matrix.get(i, j) * vector.get(j);
            }
            values.add(sum);
        }
        return new Vector(values);
    }

    public static Matrix matrixMultiply(final Matrix matrix, final Double value) {
        final Matrix current = new Matrix(matrix.values());

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                current.set(i, j, matrix.get(i, j) * value);
            }
        }

        return current;
    }

    public static Matrix matrixSum(final Matrix matrix, final Matrix other) {
        final Matrix current = new Matrix(matrix.values());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                current.set(i, j,
                        matrix.get(i, j) + other.get(i, j));
            }
        }
        return current;
    }

    public static Matrix matrixSub(final Matrix matrix, final Matrix other) {
        final Matrix current = new Matrix(matrix.values());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                current.set(i, j,
                        matrix.get(i, j) - other.get(i, j));
            }
        }
        return current;
    }

    public static Matrix mulVectors(final Vector vector, final Vector other) {
        final List<List<Double>> list = Collections.nCopies(
                vector.size(),
                Collections.nCopies(vector.size(), 0.0)
        );
        for (int i = 0; i < vector.size(); i++) {
            for (int j = 0; j < vector.size(); j++) {
                list.get(i).set(j, vector.get(i) * other.get(j));
            }
        }

        return new Matrix(list);

    }

    public static Vector mulMatrixOnVector(final Matrix matrix,
                                                   final Vector vector) {
        final List<Double> list = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            list.add(0.0);
            for (int j = 0; j < matrix.getRow(i).size(); j++) {
                list.set(i,list.get(i) + vector.get(j) * matrix.get(i,j));
            }
        }
        return new Vector(list);
    }

}
