package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Spliterator;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

@GwtCompatible(emulated = true)
abstract class AbstractCollectionIterator57<K, V> extends ImmutableSet<Entry<K, V>> {
   abstract ImmutableMap<K, V> method4();

   @Override
   public int size() {
      return this.method4().size();
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      if (!(var1 instanceof Entry)) {
         return false;
      }

      Entry var2 = (Entry)var1;
      Object var3 = this.method4().get(var2.getKey());
      return var3 != null && var3.equals(var2.getValue());
   }

   @Override
   boolean isPartialView() {
      return this.method4().isPartialView();
   }

   @Annotation3
   @Override
   boolean isHashCodeFast() {
      return this.method4().isHashCodeFast();
   }

   @Override
   public int hashCode() {
      return this.method4().hashCode();
   }

   @Annotation3
   @Override
   Object writeReplace() {
      return new AbstractCollectionIterator57.Data2<>(this.method4());
   }

   static final class Data<K, V> extends AbstractCollectionIterator57<K, V> {
      private final transient ImmutableMap<K, V> field10;
      private final transient ImmutableList<Entry<K, V>> field11;

      Data(ImmutableMap<K, V> var1, Entry<K, V>[] var2) {
         this(var1, ImmutableList.method21(var2));
      }

      Data(ImmutableMap<K, V> var1, ImmutableList<Entry<K, V>> var2) {
         this.field10 = var1;
         this.field11 = var2;
      }

      @Override
      ImmutableMap<K, V> method4() {
         return this.field10;
      }

      @Annotation3("not used in GWT")
      @Override
      int copyIntoArray(Object[] var1, int var2) {
         return this.field11.copyIntoArray(var1, var2);
      }

      @Override
      public MixinHelperIterator3<Entry<K, V>> method1() {
         return this.field11.method1();
      }

      @Override
      public Spliterator<Entry<K, V>> spliterator() {
         return this.field11.spliterator();
      }

      @Override
      public void forEach(Consumer<? super Entry<K, V>> var1) {
         this.field11.forEach(var1);
      }

      @Override
      ImmutableList<Entry<K, V>> method17() {
         return new AbstractCollectionIterator332<>(this, this.field11);
      }
   }

   @Annotation3
   private static class Data2<K, V> implements Serializable {
      final ImmutableMap<K, V> field1;
      private static final long field2 = 0L;

      Data2(ImmutableMap<K, V> var1) {
         this.field1 = var1;
      }

      Object readResolve() {
         return this.field1.method12();
      }
   }
}
