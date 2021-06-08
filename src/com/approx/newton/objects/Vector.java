package com.approx.newton.objects;

import java.util.List;

public record Vector(List<Double> values) {

    public Double get(int index) {
        return values.get(index);
    }

    public void set(final int index, final Double value){
        values.set(index, value);
    }

    public int size(){
        return values.size();
    }

}
