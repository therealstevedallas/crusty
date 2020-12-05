package com.walterj.util;

/**
 * @author Walter Jordan
 */
public class Range<T extends Number> {
    T lo;
    T hi;

    public Range(T lo, T hi) {
        this.lo = lo;
        this.hi = hi;
    }

    public T getLo() {
        return lo;
    }

    public void setLo(T lo) {
        this.lo = lo;
    }

    public T getHi() {
        return hi;
    }

    public void setHi(T hi) {
        this.hi = hi;
    }

    @Override public String toString() {
        return "Range{" +
            "lo=" + lo +
            ", hi=" + hi +
            '}';
    }
}
