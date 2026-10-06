package com.moonsworth.lunar.ichor.util;

@FunctionalInterface
public interface TriFunction<A, B, C, R> {
   R apply(A value1, B value2, C value3);
}
