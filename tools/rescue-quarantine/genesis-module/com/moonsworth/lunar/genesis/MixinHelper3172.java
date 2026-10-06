package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelper3172<K, V> extends MixinHelper317<K, V> implements MixinHelper133<K, V> {
   protected MixinHelper3172() {
   }

   protected abstract MixinHelper133<K, V> method3();

   @Override
   public List<V> get(@Nullable K var1) {
      return this.method3().get((K)var1);
   }

   @CanIgnoreReturnValue
   @Override
   public List<V> removeAll(@Nullable Object var1) {
      return this.method3().removeAll(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public List<V> replaceValues(K var1, Iterable<? extends V> var2) {
      return this.method3().replaceValues((K)var1, var2);
   }
}
