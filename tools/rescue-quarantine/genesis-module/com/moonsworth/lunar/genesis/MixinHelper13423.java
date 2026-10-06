package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class MixinHelper13423<K, V> extends MixinHelper1342<K, V> implements MixinHelper133<K, V> {
   private static final long field3 = 6588350623831699109L;

   protected MixinHelper13423(Map<K, Collection<V>> var1) {
      super(var1);
   }

   abstract List<V> createCollection();

   List<V> createUnmodifiableEmptyCollection() {
      return Collections.emptyList();
   }

   @Override
   <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> var1) {
      return Collections.unmodifiableList((List<? extends E>)var1);
   }

   @Override
   Collection<V> wrapCollection(K var1, Collection<V> var2) {
      return this.method2(var1, (List)var2, null);
   }

   @Override
   public List<V> get(@Nullable K var1) {
      return (List<V>)super.get((K)var1);
   }

   @CanIgnoreReturnValue
   @Override
   public List<V> removeAll(@Nullable Object var1) {
      return (List<V>)super.removeAll(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public List<V> replaceValues(@Nullable K var1, Iterable<? extends V> var2) {
      return (List<V>)super.replaceValues((K)var1, var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean put(@Nullable K var1, @Nullable V var2) {
      return super.put((K)var1, (V)var2);
   }

   @Override
   public Map<K, Collection<V>> asMap() {
      return super.asMap();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return super.equals(var1);
   }
}
