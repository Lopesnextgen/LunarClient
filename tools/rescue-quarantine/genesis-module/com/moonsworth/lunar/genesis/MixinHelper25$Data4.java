package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

@Annotation4
class MixinHelper25$Data4<T> implements SupplierExtension<T>, Serializable {
   final SupplierExtension<T> field1;
   final long field2;
   transient volatile @Nullable T value;
   transient volatile long expirationNanos;
   private static final long field3 = 0L;

   MixinHelper25$Data4(SupplierExtension<T> var1, long var2, TimeUnit var4) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = var4.toNanos(var2);
      Preconditions.checkArgument(var2 > 0L, "duration (%s %s) must be > 0", var2, var4);
   }

   @Override
   public T get() {
      long var1 = this.expirationNanos;
      long var3 = MixinHelper14.systemNanoTime();
      if (var1 == 0L || var3 - var1 >= 0L) {
         synchronized (this) {
            if (var1 == this.expirationNanos) {
               Object var6 = this.field1.get();
               this.value = (T)var6;
               var1 = var3 + this.field2;
               this.expirationNanos = var1 == 0L ? 1L : var1;
               return (T)var6;
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
