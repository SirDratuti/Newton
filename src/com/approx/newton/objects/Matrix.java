package com.approx.newton.objects;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public record Matrix(List<List<Double>> values) {

    public List<Double> getRow(final int index) {
        return values.get(index);
    }

    public Double get(final int row, final int column) {
        return values.get(row).get(column);
    }

    public int size() {
        return values.size();
    }

    public void set(final int row, final int column, final Double value) {
        values.get(row).set(column, value);
    }

    private void fillMap(final @NotNull Map<Integer, Integer> mp) {
        for (int i = 0; i < values.size(); i++) {
            mp.put(i, i);
        }
    }

    public @NotNull Vector gauss(final Vector b) {
        final Map<Integer, Integer> mp = new HashMap<>();
        final List<Double> ans = new ArrayList<>();
        fillMap(mp);
        for (int j = 0; j < values.size(); j++) {
            final int jj = mp.get(j);
            double max = Math.abs(get(jj, j));
            int index = j;
            for (int i = j + 1; i < values.size(); i++) {
                double value = Math.abs(get(mp.get(i), j));
                if (value >= max) {
                    max = value;
                    index = i;
                }
            }
            remap(mp, j, index);
            for (int i = j + 1; i < size(); i++) {
                sub(mp.get(j), mp.get(i), j, b);
            }
        }

        for (int i = size() - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = size() - 1; j > i; j--) {
                sum += get(mp.get(i), j) * ans.get(size() - 1 - j);
            }
            ans.add((b.get(mp.get(i)) - sum) / get(mp.get(i), i));
        }

        Collections.reverse(ans);
        return new Vector(ans);
    }

    private void sub(final int first, final int second, final int diag, final Vector b) {
        if (Math.abs(get(second, diag)) <= 1e-20) {
            return;
        }
        final double mul = get(second, diag) / get(first, diag);
        b.set(second, b.get(second) - b.get(first) * mul);
        for (int i = 0; i < size(); i++) {
            if (i == diag) {
                set(second, i, 0.0);
            } else {
                double newValue = get(second, i) - get(first, i) * mul;
                set(second, i, newValue);
            }
        }
    }

    private void remap(final @NotNull Map<Integer, Integer> mp, final int from, final int to) {
        int fromValue = mp.get(from);
        int toValue = mp.get(to);
        mp.put(from, toValue);
        mp.put(to, fromValue);
    }

}
