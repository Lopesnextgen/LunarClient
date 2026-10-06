package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelper3173<K, V> extends MixinHelper317<K, V> implements MixinHelper132_2<K, V> {
   protected abstract MixinHelper132_2<K, V> method3();

   @Override
   public Set<Entry<K, V>> entries() {
      return this.method3().entries();
   }

   @Override
   public Set<V> get(@Nullable K var1) {
      return this.method3().get((K)var1);
   }

   @CanIgnoreReturnValue
   @Override
   public Set<V> removeAll(@Nullable Object var1) {
      return this.method3().removeAll(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public Set<V> replaceValues(K var1, Iterable<? extends V> var2) {
      return this.method3().replaceValues((K)var1, var2);
   }
}
