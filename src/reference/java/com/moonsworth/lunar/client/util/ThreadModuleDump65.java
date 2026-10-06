package com.moonsworth.lunar.client.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.lunarclient.dfu.serialization.DataResult;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceImmutableList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import javax.annotation.Nullable;
import lombok.Generated;

public final class ThreadModuleDump65 {
   public static <T> T make(Supplier<T> var0) {
      return (T)var0.get();
   }

   public static <T> T make(T var0, Consumer<? super T> var1) {
      var1.accept(var0);
      return (T)var0;
   }

   public static <T> T method1(Supplier<T> var0, Consumer<? super T> var1) {
      Object var2 = var0.get();
      var1.accept(var2);
      return (T)var2;
   }

   public static <T> T method2(List<T> var0) {
      return (T)var0.get(var0.size() - 1);
   }

   public static <T> T method3(Iterable<T> var0, @Nullable T var1) {
      Iterator var2 = var0.iterator();
      Object var3 = var2.next();
      if (var1 != null) {
         Object var4 = var3;

         while (var4 != var1) {
            if (var2.hasNext()) {
               var4 = var2.next();
            }
         }

         if (var2.hasNext()) {
            return (T)var2.next();
         }
      }

      return (T)var3;
   }

   public static <T> T method4(Iterable<T> var0, @Nullable T var1) {
      Iterator var2 = var0.iterator();
      Object var3 = null;

      while (var2.hasNext()) {
         Object var4 = var2.next();
         if (var4 == var1) {
            if (var3 == null) {
               var3 = var2.hasNext() ? Iterators.getLast(var2) : var1;
            }
            break;
         }

         var3 = var4;
      }

      return (T)var3;
   }

   public static boolean isNullOrEmpty(String var0) {
      return var0 == null || var0.isEmpty();
   }

   public static <T> Optional<T> method5(Optional<T> var0, Consumer<T> var1, Runnable var2) {
      if (var0.isPresent()) {
         var1.accept(var0.get());
      } else {
         var2.run();
      }

      return var0;
   }

   public static <T> T firstNonNull(@Nullable T var0, @Nullable T var1) {
      return (T)(var0 != null ? var0 : Preconditions.checkNotNull(var1));
   }

   public static DataResult<int[]> method6(IntStream var0, int var1) {
      int[] var2 = var0.limit(var1 + 1).toArray();
      if (var2.length != var1) {
         Supplier var3 = () -> "Input is not a list of " + var1 + " ints";
         return var2.length >= var1 ? DataResult.error(var3, Arrays.copyOf(var2, var1)) : DataResult.error(var3);
      } else {
         return DataResult.success(var2);
      }
   }

   public static DataResult<long[]> method7(LongStream var0, int var1) {
      long[] var2 = var0.limit(var1 + 1).toArray();
      if (var2.length != var1) {
         Supplier var3 = () -> "Input is not a list of " + var1 + " longs";
         return var2.length >= var1 ? DataResult.error(var3, Arrays.copyOf(var2, var1)) : DataResult.error(var3);
      } else {
         return DataResult.success(var2);
      }
   }

   public static <T> DataResult<List<T>> method8(List<T> var0, int var1) {
      if (var0.size() != var1) {
         Supplier var2 = () -> "Input is not a list of " + var1 + " elements";
         return var0.size() >= var1 ? DataResult.error(var2, var0.subList(0, var1)) : DataResult.error(var2);
      } else {
         return DataResult.success(var0);
      }
   }

   public static <T> ToIntFunction<T> method9(List<T> var0) {
      int var1 = var0.size();
      if (var1 < 8) {
         return var0::indexOf;
      }

      Object2IntOpenHashMap var2 = new Object2IntOpenHashMap(var1);
      var2.defaultReturnValue(-1);

      for (int var3 = 0; var3 < var1; var3++) {
         var2.put(var0.get(var3), var3);
      }

      return var2;
   }

   public static <T> ToIntFunction<T> method10(List<T> var0) {
      int var1 = var0.size();
      if (var1 < 8) {
         ReferenceImmutableList var4 = new ReferenceImmutableList(var0);
         return var4::indexOf;
      }

      Reference2IntOpenHashMap var2 = new Reference2IntOpenHashMap(var1);
      var2.defaultReturnValue(-1);

      for (int var3 = 0; var3 < var1; var3++) {
         var2.put(var0.get(var3), var3);
      }

      return var2;
   }

   public static <T> ToIntFunction<T> method11(T[] var0) {
      int var1 = var0.length;
      if (var1 < 8) {
         return var2x -> {
            for (int var3x = 0; var3x < var1; var3x++) {
               if (var0[var3x] == var2x) {
                  return var3x;
               }
            }

            return -1;
         };
      }

      Object2IntOpenHashMap var2 = new Object2IntOpenHashMap(var1);
      var2.defaultReturnValue(-1);

      for (int var3 = 0; var3 < var1; var3++) {
         var2.put(var0[var3], var3);
      }

      return var2;
   }

   public static <T> ToIntFunction<T> method12(T[] var0) {
      int var1 = var0.length;
      if (var1 < 8) {
         ReferenceImmutableList var4 = new ReferenceImmutableList(var0);
         return var4::indexOf;
      }

      Reference2IntOpenHashMap var2 = new Reference2IntOpenHashMap(var1);
      var2.defaultReturnValue(-1);

      for (int var3 = 0; var3 < var1; var3++) {
         var2.put(var0[var3], var3);
      }

      return var2;
   }

   @Generated
   private ThreadModuleDump65() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
