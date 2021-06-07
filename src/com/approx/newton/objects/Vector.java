package com.approx.newton.objects;

import java.util.List;

public record Vector<T>(List<T> values) {

    public T get(int index) {
        return values.get(index);
    }

    public void set(final int index, final T value){
        values.set(index, value);
    }

    public int size(){
        return values.size();
    }

}
