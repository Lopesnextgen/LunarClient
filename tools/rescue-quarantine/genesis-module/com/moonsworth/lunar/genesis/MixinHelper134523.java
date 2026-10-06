package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableList;

@GwtCompatible(serializable = true, emulated = true)
public class MixinHelper134523<K, V> extends ImmutableMultimap<K, V> implements MixinHelper133<K, V> {
   @LazyInit
   @RetainedWith
   private transient MixinHelper134523<V, K> field5;
   @Annotation3
   private static final long field6 = 0L;

   public static <T, K, V> Collector<T, ?, MixinHelper134523<K, V>> toImmutableListMultimap(
      Function<? super T, ? extends K> var0, Function<? super T, ? extends V> var1
   ) {
      Preconditions.checkNotNull(var0, "keyFunction");
      Preconditions.checkNotNull(var1, "valueFunction");
      return Collector.of(
         MixinHelper134523::method8,
         (var2, var3) -> var2.method2((K)var0.apply(var3), (V)var1.apply(var3)),
         MixinHelper134523.Data::method7,
         MixinHelper134523.Data::method11
      );
   }

   public static <T, K, V> Collector<T, ?, MixinHelper134523<K, V>> flatteningToImmutableListMultimap(
      Function<? super T, ? extends K> var0, Function<? super T, ? extends Stream<? extends V>> var1
   ) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      return Collectors.collectingAndThen(
         MixinHelper37.flatteningToMultimap(
            var1x -> Preconditions.checkNotNull((K)var0.apply((T)var1x)),
            var1x -> ((Stream)var1.apply((T)var1x)).peek(Preconditions::checkNotNull),
            MixinHelper43.method3().method1()::method2
         ),
         MixinHelper134523::method9
      );
   }

   public static <K, V> MixinHelper134523<K, V> method4() {
      return MixinHelper1345232.field7;
   }

   public static <K, V> MixinHelper134523<K, V> method3(K var0, V var1) {
      MixinHelper134523.Data var2 = method8();
      var2.method2(var0, var1);
      return var2.method11();
   }

   public static <K, V> MixinHelper134523<K, V> method4(K var0, V var1, K var2, V var3) {
      MixinHelper134523.Data var4 = method8();
      var4.method2(var0, var1);
      var4.method2(var2, var3);
      return var4.method11();
   }

   public static <K, V> MixinHelper134523<K, V> method5(K var0, V var1, K var2, V var3, K var4, V var5) {
      MixinHelper134523.Data var6 = method8();
      var6.method2(var0, var1);
      var6.method2(var2, var3);
      var6.method2(var4, var5);
      return var6.method11();
   }

   public static <K, V> MixinHelper134523<K, V> method6(K var0, V var1, K var2, V var3, K var4, V var5, K var6, V var7) {
      MixinHelper134523.Data var8 = method8();
      var8.method2(var0, var1);
      var8.method2(var2, var3);
      var8.method2(var4, var5);
      var8.method2(var6, var7);
      return var8.method11();
   }

   public static <K, V> MixinHelper134523<K, V> method7(K var0, V var1, K var2, V var3, K var4, V var5, K var6, V var7, K var8, V var9) {
      MixinHelper134523.Data var10 = method8();
      var10.method2(var0, var1);
      var10.method2(var2, var3);
      var10.method2(var4, var5);
      var10.method2(var6, var7);
      var10.method2(var8, var9);
      return var10.method11();
   }

   public static <K, V> MixinHelper134523.Data<K, V> method8() {
      return new MixinHelper134523.Data<>();
   }

   public static <K, V> MixinHelper134523<K, V> method9(Multimap<? extends K, ? extends V> var0) {
      if (var0.isEmpty()) {
         return method4();
      }

      if (var0 instanceof MixinHelper134523) {
         MixinHelper134523 var1 = (MixinHelper134523)var0;
         if (!var1.isPartialView()) {
            return var1;
         }
      }

      return method10(var0.asMap().entrySet(), null);
   }

   @Annotation2
   public static <K, V> MixinHelper134523<K, V> method10(Iterable<? extends Entry<? extends K, ? extends V>> var0) {
      return new MixinHelper134523.Data<K, V>().method4(var0).method11();
   }

   static <K, V> MixinHelper134523<K, V> method10(
      Collection<? extends Entry<? extends K, ? extends Collection<? extends V>>> var0, @Nullable Ordering<? super V> var1
   ) {
      if (var0.isEmpty()) {
         return method4();
      }

      ImmutableMap.Data2 var2 = new ImmutableMap.Data2(var0.size());
      int var3 = 0;

      for (Entry var5 : var0) {
         Object var6 = var5.getKey();
         Collection var7 = (Collection)var5.getValue();
         ImmutableList var8 = var1 == null ? ImmutableList.method15(var7) : ImmutableList.method19(var1, var7);
         if (!var8.isEmpty()) {
            var2.method1(var6, var8);
            var3 += var8.size();
         }
      }

      return new MixinHelper134523<>(var2.method7(), var3);
   }

   MixinHelper134523(ImmutableMap<K, ImmutableList<V>> var1, int var2) {
      super(var1, var2);
   }

   public ImmutableList<V> method11(@Nullable K var1) {
      ImmutableList var2 = (ImmutableList)this.field2.get(var1);
      return var2 == null ? ImmutableList.method3() : var2;
   }

   public MixinHelper134523<V, K> method12() {
      MixinHelper134523 var1 = this.field5;
      return var1 == null ? (this.field5 = this.method14()) : var1;
   }

   private MixinHelper134523<V, K> method14() {
      MixinHelper134523.Data var1 = method8();
      MixinHelperIterator3 var2 = this.HRICIRRCCHCIROCHOIOOOCRHIICRHI().method1();

      while (var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         var1.method2(var3.getValue(), var3.getKey());
      }

      MixinHelper134523 var4 = var1.method11();
      var4.field5 = (MixinHelper134523<V, K>)this;
      return var4;
   }

   @Deprecated
   @CanIgnoreReturnValue
   public ImmutableList<V> method14(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   @CanIgnoreReturnValue
   public ImmutableList<V> method15(K var1, Iterable<? extends V> var2) {
      throw new UnsupportedOperationException();
   }

   @Annotation3
   private void writeObject(ObjectOutputStream var1) {
      var1.defaultWriteObject();
      MixinHelper7_10.method4(this, var1);
   }

   @Annotation3
   private void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      int var2 = var1.readInt();
      if (var2 < 0) {
         throw new InvalidObjectException("Invalid key count " + var2);
      }

      ImmutableMap.Data2 var3 = ImmutableMap.method7();
      int var4 = 0;

      for (int var5 = 0; var5 < var2; var5++) {
         Object var6 = var1.readObject();
         int var7 = var1.readInt();
         if (var7 <= 0) {
            throw new InvalidObjectException("Invalid value count " + var7);
         }

         ImmutableList.Data2 var8 = ImmutableList.method30();

         for (int var9 = 0; var9 < var7; var9++) {
            var8.method2(var1.readObject());
         }

         var3.method1(var6, var8.method6());
         var4 += var7;
      }

      ImmutableMap var11;
      try {
         var11 = var3.method7();
      } catch (IllegalArgumentException var10) {
         throw (InvalidObjectException)new InvalidObjectException(var10.getMessage()).initCause(var10);
      }

      ImmutableMultimap.Data3.field1.set(this, var11);
      ImmutableMultimap.Data3.field2.set(this, var4);
   }

   public static final class Data<K, V> extends ImmutableMultimap.Data2<K, V> {
      @CanIgnoreReturnValue
      public MixinHelper134523.Data<K, V> method2(K var1, V var2) {
         super.method1((K)var1, (V)var2);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper134523.Data<K, V> method3(Entry<? extends K, ? extends V> var1) {
         super.method2(var1);
         return this;
      }

      @CanIgnoreReturnValue
      @Annotation2
      public MixinHelper134523.Data<K, V> method4(Iterable<? extends Entry<? extends K, ? extends V>> var1) {
         super.method3(var1);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper134523.Data<K, V> method5(K var1, Iterable<? extends V> var2) {
         super.method4((K)var1, var2);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper134523.Data<K, V> method6(K var1, V... var2) {
         super.method5((K)var1, (V[])var2);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper134523.Data<K, V> method7(Multimap<? extends K, ? extends V> var1) {
         super.method6(var1);
         return this;
      }

      @CanIgnoreReturnValue
      MixinHelper134523.Data<K, V> method7(ImmutableMultimap.Data2<K, V> var1) {
         super.method9(var1);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper134523.Data<K, V> method9(java.util.Comparator<? super K> var1) {
         super.method7(var1);
         return this;
      }

      @CanIgnoreReturnValue
      public MixinHelper134523.Data<K, V> method10(java.util.Comparator<? super V> var1) {
         super.method8(var1);
         return this;
      }

      public MixinHelper134523<K, V> method11() {
         return (MixinHelper134523<K, V>)super.method10();
      }
   }
}
