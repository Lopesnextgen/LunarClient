package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collector;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

@GwtCompatible(serializable = true, emulated = true)
public abstract class SerializableIterator52<K, V> extends SerializableIterator5<K, V> implements MapExtension<K, V> {
   public static <T, K, V> Collector<T, ?, SerializableIterator52<K, V>> toImmutableBiMap(
      Function<? super T, ? extends K> var0, Function<? super T, ? extends V> var1
   ) {
      return MixinHelper16_5.toImmutableBiMap(var0, var1);
   }

   public static <K, V> SerializableIterator52<K, V> method3() {
      return (SerializableIterator52<K, V>)SerializableIterator524.field6;
   }

   public static <K, V> SerializableIterator52<K, V> method3(K var0, V var1) {
      return new SerializableIterator522<>((K)var0, (V)var1);
   }

   public static <K, V> SerializableIterator52<K, V> method4(K var0, V var1, K var2, V var3) {
      return SerializableIterator524.method1(entryOf(var0, var1), entryOf(var2, var3));
   }

   public static <K, V> SerializableIterator52<K, V> method5(K var0, V var1, K var2, V var3, K var4, V var5) {
      return SerializableIterator524.method1(entryOf(var0, var1), entryOf(var2, var3), entryOf(var4, var5));
   }

   public static <K, V> SerializableIterator52<K, V> method6(K var0, V var1, K var2, V var3, K var4, V var5, K var6, V var7) {
      return SerializableIterator524.method1(entryOf(var0, var1), entryOf(var2, var3), entryOf(var4, var5), entryOf(var6, var7));
   }

   public static <K, V> SerializableIterator52<K, V> method7(K var0, V var1, K var2, V var3, K var4, V var5, K var6, V var7, K var8, V var9) {
      return SerializableIterator524.method1(entryOf(var0, var1), entryOf(var2, var3), entryOf(var4, var5), entryOf(var6, var7), entryOf(var8, var9));
   }

   public static <K, V> SerializableIterator52.Data2<K, V> method8() {
      return new SerializableIterator52.Data2<>();
   }

   @Annotation2
   public static <K, V> SerializableIterator52.Data2<K, V> method9(int var0) {
      MixinHelper18_3.checkNonnegative(var0, "expectedSize");
      return new SerializableIterator52.Data2<>(var0);
   }

   public static <K, V> SerializableIterator52<K, V> method10(Map<? extends K, ? extends V> var0) {
      if (var0 instanceof SerializableIterator52) {
         SerializableIterator52 var1 = (SerializableIterator52)var0;
         if (!var1.isPartialView()) {
            return var1;
         }
      }

      return method11(var0.entrySet());
   }

   @Annotation2
   public static <K, V> SerializableIterator52<K, V> method11(Iterable<? extends Entry<? extends K, ? extends V>> var0) {
      Entry[] var1 = Iterables.toArray(var0, field1);
      switch (var1.length) {
         case 0:
            return method3();
         case 1:
            Entry var2 = var1[0];
            return method3((K)var2.getKey(), (V)var2.getValue());
         default:
            return SerializableIterator524.method1(var1);
      }
   }

   SerializableIterator52() {
   }

   public abstract SerializableIterator52<V, K> method11();

   public ImmutableSet<V> method20() {
      return this.method11().HHOORCICIHCIRHRRIIOIOHHOHHCCCR();
   }

   final ImmutableSet<V> method21() {
      throw new AssertionError("should never be called");
   }

   @Deprecated
   @CanIgnoreReturnValue
   @Override
   public V forcePut(K var1, V var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   Object writeReplace() {
      return new SerializableIterator52.Data(this);
   }

   private static class Data extends ImmutableMap.Data4 {
      private static final long field4 = 0L;

      Data(SerializableIterator52<?, ?> var1) {
         super(var1);
      }

      @Override
      Object readResolve() {
         SerializableIterator52.Data2 var1 = new SerializableIterator52.Data2();
         return this.HRICOROOOCCOCOROCRHHCRRIRCOICO(var1);
      }
   }

   public static final class Data2<K, V> extends ImmutableMap.Data2<K, V> {
      public Data2() {
      }

      Data2(int var1) {
         super(var1);
      }

      @CanIgnoreReturnValue
      public SerializableIterator52.Data2<K, V> method2(K var1, V var2) {
         super.method1((K)var1, (V)var2);
         return this;
      }

      @CanIgnoreReturnValue
      public SerializableIterator52.Data2<K, V> method3(Entry<? extends K, ? extends V> var1) {
         super.method2(var1);
         return this;
      }

      @CanIgnoreReturnValue
      public SerializableIterator52.Data2<K, V> method4(Map<? extends K, ? extends V> var1) {
         super.method3(var1);
         return this;
      }

      @CanIgnoreReturnValue
      @Annotation2
      public SerializableIterator52.Data2<K, V> method5(Iterable<? extends Entry<? extends K, ? extends V>> var1) {
         super.method4(var1);
         return this;
      }

      @CanIgnoreReturnValue
      @Annotation2
      public SerializableIterator52.Data2<K, V> method6(java.util.Comparator<? super V> var1) {
         super.method5(var1);
         return this;
      }

      @CanIgnoreReturnValue
      SerializableIterator52.Data2<K, V> method7(ImmutableMap.Data2<K, V> var1) {
         super.method6(var1);
         return this;
      }

      public SerializableIterator52<K, V> method9() {
         switch (this.size) {
            case 0:
               return SerializableIterator52.method3();
            case 1:
               return SerializableIterator52.method3(this.entries[0].getKey(), this.entries[0].getValue());
            default:
               if (this.valueComparator != null) {
                  if (this.entriesUsed) {
                     this.entries = Arrays.copyOf(this.entries, this.size);
                  }

                  Arrays.sort(this.entries, 0, this.size, Ordering.method2(this.valueComparator).method12(Maps.method2()));
               }

               this.entriesUsed = true;
               return SerializableIterator524.method2(this.size, this.entries);
         }
      }

      @Annotation4
      SerializableIterator52<K, V> method10() {
         Preconditions.checkState(this.valueComparator == null, "buildJdkBacked is for tests only, doesn't support orderEntriesByValue");
         switch (this.size) {
            case 0:
               return SerializableIterator52.method3();
            case 1:
               return SerializableIterator52.method3(this.entries[0].getKey(), this.entries[0].getValue());
            default:
               this.entriesUsed = true;
               return SerializableIterator524.method2(this.size, this.entries);
         }
      }
   }
}
