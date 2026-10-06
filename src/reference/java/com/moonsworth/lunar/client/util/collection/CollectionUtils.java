package com.moonsworth.lunar.client.util.collection;

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

public final class CollectionUtils {
   public static <T> T make(Supplier<T> supplier0) {
      return (T)supplier0.get();
   }

   public static <T> T make(T value0, Consumer<? super T> consumer1) {
      consumer1.accept(value0);
      return (T)value0;
   }

   public static <T> T method1(Supplier<T> supplier0, Consumer<? super T> consumer1) {
      Object obj2 = supplier0.get();
      consumer1.accept(obj2);
      return (T)obj2;
   }

   public static <T> T method2(List<T> list0) {
      return (T)list0.get(list0.size() - 1);
   }

   public static <T> T method3(Iterable<T> list0, @Nullable T value1) {
      Iterator iterator2 = list0.iterator();
      Object obj3 = iterator2.next();
      if (value1 != null) {
         Object obj4 = obj3;

         while (obj4 != value1) {
            if (iterator2.hasNext()) {
               obj4 = iterator2.next();
            }
         }

         if (iterator2.hasNext()) {
            return (T)iterator2.next();
         }
      }

      return (T)obj3;
   }

   public static <T> T method4(Iterable<T> list0, @Nullable T value1) {
      Iterator iterator2 = list0.iterator();
      Object obj3 = null;

      while (iterator2.hasNext()) {
         Object obj4 = iterator2.next();
         if (obj4 == value1) {
            if (obj3 == null) {
               obj3 = iterator2.hasNext() ? Iterators.getLast(iterator2) : value1;
            }
            break;
         }

         obj3 = obj4;
      }

      return (T)obj3;
   }

   public static boolean isNullOrEmpty(String text0) {
      return text0 == null || text0.isEmpty();
   }

   public static <T> Optional<T> method5(Optional<T> optional0, Consumer<T> consumer1, Runnable runnable2) {
      if (optional0.isPresent()) {
         consumer1.accept(optional0.get());
      } else {
         runnable2.run();
      }

      return optional0;
   }

   public static <T> T firstNonNull(@Nullable T value0, @Nullable T value1) {
      return (T)(value0 != null ? value0 : Preconditions.checkNotNull(value1));
   }

   public static DataResult<int[]> method6(IntStream intstream0, int number1) {
      int[] items2 = intstream0.limit(number1 + 1).toArray();
      if (items2.length != number1) {
         Supplier supplier3 = () -> "Input is not a list of " + number1 + " ints";
         return items2.length >= number1 ? DataResult.error(supplier3, Arrays.copyOf(items2, number1)) : DataResult.error(supplier3);
      } else {
         return DataResult.success(items2);
      }
   }

   public static DataResult<long[]> method7(LongStream longstream0, int number1) {
      long[] items2 = longstream0.limit(number1 + 1).toArray();
      if (items2.length != number1) {
         Supplier supplier3 = () -> "Input is not a list of " + number1 + " longs";
         return items2.length >= number1 ? DataResult.error(supplier3, Arrays.copyOf(items2, number1)) : DataResult.error(supplier3);
      } else {
         return DataResult.success(items2);
      }
   }

   public static <T> DataResult<List<T>> method8(List<T> list0, int index1) {
      if (list0.size() != index1) {
         Supplier supplier2 = () -> "Input is not a list of " + index1 + " elements";
         return list0.size() >= index1 ? DataResult.error(supplier2, list0.subList(0, index1)) : DataResult.error(supplier2);
      } else {
         return DataResult.success(list0);
      }
   }

   public static <T> ToIntFunction<T> method9(List<T> list0) {
      int number1 = list0.size();
      if (number1 < 8) {
         return list0::indexOf;
      }

      Object2IntOpenHashMap object2intopenhashmap2 = new Object2IntOpenHashMap(number1);
      object2intopenhashmap2.defaultReturnValue(-1);

      for (int index3 = 0; index3 < number1; index3++) {
         object2intopenhashmap2.put(list0.get(index3), index3);
      }

      return object2intopenhashmap2;
   }

   public static <T> ToIntFunction<T> method10(List<T> list0) {
      int number1 = list0.size();
      if (number1 < 8) {
         ReferenceImmutableList referenceimmutablelist4 = new ReferenceImmutableList(list0);
         return referenceimmutablelist4::indexOf;
      }

      Reference2IntOpenHashMap reference2intopenhashmap2 = new Reference2IntOpenHashMap(number1);
      reference2intopenhashmap2.defaultReturnValue(-1);

      for (int index3 = 0; index3 < number1; index3++) {
         reference2intopenhashmap2.put(list0.get(index3), index3);
      }

      return reference2intopenhashmap2;
   }

   public static <T> ToIntFunction<T> method11(T[] items0) {
      int number1 = items0.length;
      if (number1 < 8) {
         return arg2x -> {
            for (int index3x = 0; index3x < number1; index3x++) {
               if (items0[index3x] == arg2x) {
                  return index3x;
               }
            }

            return -1;
         };
      }

      Object2IntOpenHashMap object2intopenhashmap2 = new Object2IntOpenHashMap(number1);
      object2intopenhashmap2.defaultReturnValue(-1);

      for (int index3 = 0; index3 < number1; index3++) {
         object2intopenhashmap2.put(items0[index3], index3);
      }

      return object2intopenhashmap2;
   }

   public static <T> ToIntFunction<T> method12(T[] items0) {
      int number1 = items0.length;
      if (number1 < 8) {
         ReferenceImmutableList referenceimmutablelist4 = new ReferenceImmutableList(items0);
         return referenceimmutablelist4::indexOf;
      }

      Reference2IntOpenHashMap reference2intopenhashmap2 = new Reference2IntOpenHashMap(number1);
      reference2intopenhashmap2.defaultReturnValue(-1);

      for (int index3 = 0; index3 < number1; index3++) {
         reference2intopenhashmap2.put(items0[index3], index3);
      }

      return reference2intopenhashmap2;
   }

   @Generated
   private CollectionUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
