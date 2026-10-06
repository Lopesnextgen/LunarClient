package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentMap;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ForwardingMap;

@GwtCompatible
public abstract class MixinHelper3126<K, V> extends ForwardingMap<K, V> implements ConcurrentMap<K, V> {
   protected MixinHelper3126() {
   }

   protected abstract ConcurrentMap<K, V> delegate();

   @CanIgnoreReturnValue
   @Override
   public V putIfAbsent(K var1, V var2) {
      return this.delegate().putIfAbsent((K)var1, (V)var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean remove(Object var1, Object var2) {
      return this.delegate().remove(var1, var2);
   }

   @CanIgnoreReturnValue
   @Override
   public V replace(K var1, V var2) {
      return this.delegate().replace((K)var1, (V)var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean replace(K var1, V var2, V var3) {
      return this.delegate().replace((K)var1, (V)var2, (V)var3);
   }
}
