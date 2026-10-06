package com.moonsworth.lunar.genesis;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class MixinHelper$Data35 implements Serializable {
   final Object field1;
   final Object field2;
   @Annotation3
   private static final long field3 = 0L;

   MixinHelper$Data35(Object var1, @Nullable Object var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = var2 == null ? this : var2;
   }

   Object delegate() {
      return this.field1;
   }

   @Override
   public String toString() {
      synchronized (this.field2) {
         return this.field1.toString();
      }
   }

   @Annotation3
   private void writeObject(ObjectOutputStream var1) {
      synchronized (this.field2) {
         var1.defaultWriteObject();
      }
   }
}
