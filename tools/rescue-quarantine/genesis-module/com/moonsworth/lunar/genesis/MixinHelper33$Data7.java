package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper33$Data7<E> extends MixinHelper33$Data3<E> implements Serializable {
   private final @Nullable E field1;
   private final int field2;
   private static final long field3 = 0L;

   MixinHelper33$Data7(@Nullable E var1, int var2) {
      this.field1 = (E)var1;
      this.field2 = var2;
      MixinHelper18_3.checkNonnegative(var2, "count");
   }

   @Override
   public final @Nullable E getElement() {
      return this.field1;
   }

   @Override
   public final int getCount() {
      return this.field2;
   }

   public MixinHelper33$Data7<E> method1() {
      return null;
   }
}
