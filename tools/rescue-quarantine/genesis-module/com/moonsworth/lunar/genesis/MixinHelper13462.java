package com.moonsworth.lunar.genesis;

import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class MixinHelper13462<K, V> extends MixinHelper1346<K, V> implements MixinHelper133<K, V> {
   MixinHelper13462(MixinHelper133<K, V> var1, PredicateExtension<? super K> var2) {
      super(var1, var2);
   }

   public MixinHelper133<K, V> method5() {
      return (MixinHelper133<K, V>)super.method1();
   }

   @Override
   public List<V> get(K var1) {
      return (List<V>)super.get((K)var1);
   }

   @Override
   public List<V> removeAll(@Nullable Object var1) {
      return (List<V>)super.removeAll(var1);
   }

   @Override
   public List<V> replaceValues(K var1, Iterable<? extends V> var2) {
      return (List<V>)super.replaceValues((K)var1, var2);
   }
}
