package com.moonsworth.lunar.genesis;

import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Lists;
import com.google.common.base.Preconditions;

@GwtCompatible
@Annotation2
final class MixinHelper35_2 {
   private MixinHelper35_2() {
   }

   public static <E extends Comparable> int method1(List<? extends E> var0, E var1, MixinHelper35$Type var2, MixinHelper35$Type2 var3) {
      Preconditions.checkNotNull(var1);
      return method4(var0, var1, Ordering.method1(), var2, var3);
   }

   public static <E, K extends Comparable> int method2(
      List<E> var0, MixinHelper24_2<? super E, K> var1, @Nullable K var2, MixinHelper35$Type var3, MixinHelper35$Type2 var4
   ) {
      return method3(var0, var1, var2, Ordering.method1(), var3, var4);
   }

   public static <E, K> int method3(
      List<E> var0,
      MixinHelper24_2<? super E, K> var1,
      @Nullable K var2,
      java.util.Comparator<? super K> var3,
      MixinHelper35$Type var4,
      MixinHelper35$Type2 var5
   ) {
      return method4(Lists.method1(var0, var1), var2, var3, var4, var5);
   }

   public static <E> int method4(
      List<? extends E> var0, @Nullable E var1, java.util.Comparator<? super E> var2, MixinHelper35$Type var3, MixinHelper35$Type2 var4
   ) {
      Preconditions.checkNotNull(var2);
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var3);
      Preconditions.checkNotNull(var4);
      if (!(var0 instanceof RandomAccess)) {
         var0 = Lists.newArrayList(var0);
      }

      int var5 = 0;
      int var6 = var0.size() - 1;

      while (var5 <= var6) {
         int var7 = var5 + var6 >>> 1;
         int var8 = var2.compare(var1, var0.get(var7));
         if (var8 < 0) {
            var6 = var7 - 1;
         } else {
            if (var8 <= 0) {
               return var5 + var3.resultIndex(var2, var1, var0.subList(var5, var6 + 1), var7 - var5);
            }

            var5 = var7 + 1;
         }
      }

      return var4.resultIndex(var5);
   }
}
