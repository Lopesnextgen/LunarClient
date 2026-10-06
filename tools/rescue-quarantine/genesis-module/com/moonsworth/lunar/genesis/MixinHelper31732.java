package com.moonsworth.lunar.genesis;

import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelper31732<K, V> extends MixinHelper3173<K, V> implements MixinHelper1322<K, V> {
   protected MixinHelper31732() {
   }

   protected abstract MixinHelper1322<K, V> method4();

   @Override
   public SortedSet<V> get(@Nullable K var1) {
      return this.method4().get((K)var1);
   }

   @Override
   public SortedSet<V> removeAll(@Nullable Object var1) {
      return this.method4().removeAll(var1);
   }

   @Override
   public SortedSet<V> replaceValues(K var1, Iterable<? extends V> var2) {
      return this.method4().replaceValues((K)var1, var2);
   }

   @Override
   public java.util.Comparator<? super V> valueComparator() {
      return this.method4().valueComparator();
   }
}
