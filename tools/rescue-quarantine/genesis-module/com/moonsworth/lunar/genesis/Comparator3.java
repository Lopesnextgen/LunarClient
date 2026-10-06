package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
final class Comparator3<T> extends Ordering<Iterable<T>> implements Serializable {
   final java.util.Comparator<? super T> field3;
   private static final long field4 = 0L;

   Comparator3(java.util.Comparator<? super T> var1) {
      this.field3 = var1;
   }

   public int compare(Iterable<T> var1, Iterable<T> var2) {
      Iterator var3 = var1.iterator();
      Iterator var4 = var2.iterator();

      while (var3.hasNext()) {
         if (!var4.hasNext()) {
            return 1;
         }

         int var5 = this.field3.compare((T)var3.next(), (T)var4.next());
         if (var5 != 0) {
            return var5;
         }
      }

      return var4.hasNext() ? -1 : 0;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof Comparator3) {
         Comparator3 var2 = (Comparator3)var1;
         return this.field3.equals(var2.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode() ^ 2075626741;
   }

   @Override
   public String toString() {
      return this.field3 + ".lexicographical()";
   }
}
