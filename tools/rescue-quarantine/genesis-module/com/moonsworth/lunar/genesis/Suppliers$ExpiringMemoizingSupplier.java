package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.eventbus.Subscribe;
import com.google.common.base.Supplier;
import com.google.common.base.Preconditions;

@Subscribe
class Suppliers$ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {
   final Supplier<T> field1;
   final long field2;
   transient volatile @Nullable T value;
   transient volatile long expirationNanos;
   private static final long field3 = 0L;

   Suppliers$ExpiringMemoizingSupplier(Supplier<T> supplierextension1, long number2, TimeUnit timeunit4) {
      this.field1 = (Supplier<T>)Preconditions.checkNotNull(supplierextension1);
      this.field2 = timeunit4.toNanos(number2);
      Preconditions.checkArgument(number2 > 0L, "duration (%s %s) must be > 0", number2, timeunit4);
   }

   public T get() {
      long number1 = this.expirationNanos;
      long number3 = MixinHelper14.systemNanoTime();
      if (number1 == 0L || number3 - number1 >= 0L) {
         synchronized (this) {
            if (number1 == this.expirationNanos) {
               Object obj6 = this.field1.get();
               this.value = (T)obj6;
               number1 = number3 + this.field2;
               this.expirationNanos = number1 == 0L ? 1L : number1;
               return (T)obj6;
            }
         }
      }

      return this.value;
   }

   @Override
   public String toString() {
      return "Suppliers.memoizeWithExpiration(" + this.field1 + ", " + this.field2 + ", NANOS)";
   }
}
