package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class Count implements Serializable {
   private int value;

   Count(int number1) {
      this.value = number1;
   }

   public int get() {
      return this.value;
   }

   public void add(int number1) {
      this.value += number1;
   }

   public int addAndGet(int number1) {
      return this.value += number1;
   }

   public void set(int number1) {
      this.value = number1;
   }

   public int getAndSet(int number1) {
      int number2 = this.value;
      this.value = number1;
      return number2;
   }

   @Override
   public int hashCode() {
      return this.value;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      return obj1 instanceof Count && ((Count)obj1).value == this.value;
   }

   @Override
   public String toString() {
      return Integer.toString(this.value);
   }
}
