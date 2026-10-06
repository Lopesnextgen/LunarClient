package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible(emulated = true)
final class AbstractCollectionIterator533<K, V> extends AbstractCollectionIterator53<K> {
   private final ImmutableMap<K, V> field10;

   AbstractCollectionIterator533(ImmutableMap<K, V> var1) {
      this.field10 = var1;
   }

   @Override
   public int size() {
      return this.field10.size();
   }

   @Override
   public MixinHelperIterator3<K> method1() {
      return this.field10.method16();
   }

   @Override
   public Spliterator<K> spliterator() {
      return this.field10.keySpliterator();
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      return this.field10.containsKey(var1);
   }

   @Override
   K get(int var1) {
      return this.field10.method12().method2().get(var1).getKey();
   }

   @Override
   public void forEach(Consumer<? super K> var1) {
      Preconditions.checkNotNull(var1);
      this.field10.forEach((var1x, var2) -> var1.accept(var1x));
   }

   @Override
   boolean isPartialView() {
      return true;
   }

   @Annotation3
   @Override
   Object writeReplace() {
      return new AbstractCollectionIterator533.Data<>(this.field10);
   }

   @Annotation3
   private static class Data<K> implements Serializable {
      final ImmutableMap<K, ?> field1;
      private static final long field2 = 0L;

      Data(ImmutableMap<K, ?> var1) {
         this.field1 = var1;
      }

      Object readResolve() {
         return this.field1.method14();
      }
   }
}
