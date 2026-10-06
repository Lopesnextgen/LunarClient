package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
class MixinHelper322<K, V> extends MixinHelper32<K, V> implements Serializable {
   final @Nullable K field1;
   final @Nullable V field2;
   private static final long field3 = 0L;

   MixinHelper322(@Nullable K var1, @Nullable V var2) {
      this.field1 = (K)var1;
      this.field2 = (V)var2;
   }

   @Override
   public final @Nullable K getKey() {
      return this.field1;
   }

   @Override
   public final @Nullable V getValue() {
      return this.field2;
   }

   @Override
   public final V setValue(V var1) {
      throw new UnsupportedOperationException();
   }
}
