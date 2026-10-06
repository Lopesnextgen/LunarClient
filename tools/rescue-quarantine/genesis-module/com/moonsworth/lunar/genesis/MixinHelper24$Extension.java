package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

public interface MixinHelper24$Extension<R, C, V> {
   @Nullable R getRowKey();

   @Nullable C getColumnKey();

   @Nullable V getValue();

   @Override
   boolean equals(@Nullable Object var1);

   @Override
   int hashCode();
}
