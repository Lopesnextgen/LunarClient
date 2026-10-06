package com.moonsworth.lunar.ichor.util;

@FunctionalInterface
public interface FatalIchorError2<A, B, C, R> {
   R apply(A var1, B var2, C var3);
}
