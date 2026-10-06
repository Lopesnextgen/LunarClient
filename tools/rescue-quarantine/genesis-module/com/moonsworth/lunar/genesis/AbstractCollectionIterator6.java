package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Spliterator;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible(emulated = true)
final class AbstractCollectionIterator6<K, V> extends ImmutableCollection<V> {
   private final ImmutableMap<K, V> field3;

   AbstractCollectionIterator6(ImmutableMap<K, V> var1) {
      this.field3 = var1;
   }

   @Override
   public int size() {
      return this.field3.size();
   }

   @Override
   public MixinHelperIterator3<V> method1() {
      return new MixinHelperIterator3<V>() {
         final MixinHelperIterator3<Entry<K, V>> field1 = AbstractCollectionIterator6.this.field3.method12().method1();

         @Override
         public boolean hasNext() {
            return this.field1.hasNext();
         }

         @Override
         public V next() {
            return this.field1.next().getValue();
         }
      };
   }

   @Override
   public Spliterator<V> spliterator() {
      return MixinHelper3_5.map(this.field3.method12().spliterator(), Entry::getValue);
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      return var1 != null && Iterators.contains(this.method1(), var1);
   }

   @Override
   boolean isPartialView() {
      return true;
   }

   @Override
   public ImmutableList<V> method2() {
      final ImmutableList var1 = this.field3.method12().method2();
      return new AbstractCollectionIterator33<V>() {
         @Override
         public V get(int var1x) {
            return (V)((Entry)var1.get(var1x)).getValue();
         }

         @Override
         ImmutableCollection<V> method4() {
            return AbstractCollectionIterator6.this;
         }
      };
   }

   @Annotation3
   @Override
   public void forEach(Consumer<? super V> var1) {
      Preconditions.checkNotNull(var1);
      this.field3.forEach((var1x, var2) -> var1.accept(var2));
   }

   @Annotation3
   @Override
   Object writeReplace() {
      return new AbstractCollectionIterator6.Data<>(this.field3);
   }

   @Annotation3
   private static class Data<V> implements Serializable {
      final ImmutableMap<?, V> field1;
      private static final long field2 = 0L;

      Data(ImmutableMap<?, V> var1) {
         this.field1 = var1;
      }

      Object readResolve() {
         return this.field1.method17();
      }
   }
}
