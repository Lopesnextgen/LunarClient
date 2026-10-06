package com.moonsworth.lunar.genesis;

import java.util.SortedSet;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class MixinHelper9_6 {
   private MixinHelper9_6() {
   }

   public static boolean hasSameComparator(java.util.Comparator<?> var0, Iterable<?> var1) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      java.util.Comparator var2;
      if (var1 instanceof SortedSet) {
         var2 = comparator((SortedSet)var1);
      } else {
         if (!(var1 instanceof IterableExtension)) {
            return false;
         }

         var2 = ((IterableExtension)var1).comparator();
      }

      return var0.equals(var2);
   }

   public static <E> java.util.Comparator<? super E> comparator(SortedSet<E> var0) {
      java.util.Comparator var1 = var0.comparator();
      if (var1 == null) {
         var1 = Ordering.method1();
      }

      return var1;
   }
}
