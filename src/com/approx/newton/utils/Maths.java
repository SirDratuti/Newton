package com.approx.newton.utils;

import com.approx.newton.objects.Matrix;
import com.approx.newton.objects.Vector;

import java.util.*;
import java.util.stream.Collectors;

public final class Maths {


    public static Vector<Double> sum(final Vector<Double> vector, final Vector<Double> other) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            values.add(vector.get(i) + other.get(i));
        }
        return new Vector<>(values);
    }

    public static Vector<Double> sub(final Vector<Double> vector, final Vector<Double> other) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            values.add(vector.get(i) - other.get(i));
        }
        return new Vector<>(values);
    }

    public static Vector<Double> elementsMultiply(final Vector<Double> vector, final Vector<Double> other) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            values.add(vector.get(i) * other.get(i));
        }
        return new Vector<>(values);
    }

    public static Double scalarMultiply(final Vector<Double> vector, final Vector<Double> other) {
        double result = 0.0;
        for (int i = 0; i < vector.size(); i++) {
            result += vector.get(i) * other.get(i);
        }
        return result;
    }

    public static Vector<Double> multiply(final Vector<Double> vector, final double constant) {
        return new Vector<>(
                vector
                        .values()
                        .stream()
                        .map(it ->
                                it * constant)
                        .collect(Collectors
                                .toList())
        );
    }

    public static double norm(final Vector<Double> vector) {
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

    public static Vector<Double> mulByVector(final Matrix matrix, final Vector<Double> vector) {
        final List<Double> values = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            double sum = 0.0;
            for (int j = 0; j < vector.size(); j++) {
                sum += matrix.get(i, j) * vector.get(j);
            }
            values.add(sum);
        }
        return new Vector<>(values);
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

    public static Matrix mulVectors(final Vector<Double> vector, final Vector<Double> other) {
        final List<List<Double>> list = Collections.nCopies(
                vector.size(),
                Collections.nCopies(vector.size(), 0.0)
        );
        for (int i = 0; i < vector.size(); i++) {
            for (int j = 0; j < vector.size(); j++) {
                list.get(i).set(j, vector.get(i) * vector.get(j));
            }
        }

        return new Matrix(list);

    }

    public static Vector<Double> mulMatrixOnVector(final Matrix matrix,
                                                   final Vector<Double> vector) {
        final List<Double> list = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            list.add(0.0);
            for (int j = 0; j < matrix.getRow(i).size(); j++) {
                list.set(i,list.get(i) + vector.get(j) * matrix.get(i,j));
            }
        }
        return new Vector<>(list);
    }


    /*private void fillMap(final Map<Integer, Integer> mp, final int size) {
        for (int i = 0; i < size; i++) {
            mp.put(i, i);
        }
    }

    private void remap(final Map<Integer, Integer> mp, final int from, final int to) {
        int fromValue = mp.get(from);
        int toValue = mp.get(to);
        mp.put(from, toValue);
        mp.put(to, fromValue);
    }


    private void sub(final int first, final int second, final int diag, final Matrix matrix) {
        if (Math.abs(matrix.get(second, diag)) <= 1e-20) {
            return;
        }
        final double mul = matrix.get(second, diag) / matrix.get(first, diag);
        matrix.setB(second, matrix.getB(second) - matrix.getB(first) * mul);
        for (int i = 0; i < matrix.size(); i++) {
            if (i == diag) {
                matrix.set(second, i, 0.0);
            } else {
                double newValue = matrix.get(second, i) - matrix.get(first, i) * mul;
                matrix.set(second, i, newValue);
            }
        }
    }

    public Vector<Double> gauss(final Matrix matrix) {
        final Map<Integer, Integer> mp = new HashMap<>();
        final List<Double> ans = new ArrayList<>();
        fillMap(mp, matrix.size());
        for (int j = 0; j < matrix.size(); j++) {
            final int jj = mp.get(j);
            double max = Math.abs(matrix.get(jj, j));
            int index = j;
            for (int i = j + 1; i < matrix.size(); i++) {
                double value = Math.abs(matrix.get(mp.get(i), j));
                if (value >= max) {
                    max = value;
                    index = i;
                }
            }
            remap(mp, j, index);
            for (int i = j + 1; i < matrix.size(); i++) {
                sub(mp.get(j), mp.get(i), j, matrix);
            }
        }

        for (int i = matrix.size() - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = matrix.size() - 1; j > i; j--) {
                sum += matrix.get(mp.get(i), j) * ans.get(matrix.size() - 1 - j);
            }
            ans.add((matrix.getB(mp.get(i)) - sum) / matrix.get(mp.get(i), i));
        }

        Collections.reverse(ans);
        return new Vector<>(ans);
    }*/

}
