package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class SerializableImpl implements Serializable {
   private int value;

   SerializableImpl(int var1) {
      this.value = var1;
   }

   public int get() {
      return this.value;
   }

   public void add(int var1) {
      this.value += var1;
   }

   public int addAndGet(int var1) {
      return this.value += var1;
   }

   public void set(int var1) {
      this.value = var1;
   }

   public int getAndSet(int var1) {
      int var2 = this.value;
      this.value = var1;
      return var2;
   }

   @Override
   public int hashCode() {
      return this.value;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return var1 instanceof SerializableImpl && ((SerializableImpl)var1).value == this.value;
   }

   @Override
   public String toString() {
      return Integer.toString(this.value);
   }
}
