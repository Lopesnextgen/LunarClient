package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

@GwtCompatible
public abstract class MixinHelper317<K, V> extends MixinHelper31_3 implements Multimap<K, V> {
   protected MixinHelper317() {
   }

   protected abstract Multimap<K, V> method1();

   @Override
   public Map<K, Collection<V>> asMap() {
      return this.method1().asMap();
   }

   @Override
   public void clear() {
      this.method1().clear();
   }

   @Override
   public boolean containsEntry(@Nullable Object var1, @Nullable Object var2) {
      return this.method1().containsEntry(var1, var2);
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      return this.method1().containsKey(var1);
   }

   @Override
   public boolean containsValue(@Nullable Object var1) {
      return this.method1().containsValue(var1);
   }

   @Override
   public Collection<Entry<K, V>> entries() {
      return this.method1().entries();
   }

   @Override
   public Collection<V> get(@Nullable K var1) {
      return this.method1().get((K)var1);
   }

   @Override
   public boolean isEmpty() {
      return this.method1().isEmpty();
   }

   @Override
   public Multiset<K> method2() {
      return this.method1().method2();
   }

   @Override
   public Set<K> keySet() {
      return this.method1().keySet();
   }

   @CanIgnoreReturnValue
   @Override
   public boolean put(K var1, V var2) {
      return this.method1().put((K)var1, (V)var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean putAll(K var1, Iterable<? extends V> var2) {
      return this.method1().putAll((K)var1, var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean method1(Multimap<? extends K, ? extends V> var1) {
      return this.method1().method1(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean remove(@Nullable Object var1, @Nullable Object var2) {
      return this.method1().remove(var1, var2);
   }

   @CanIgnoreReturnValue
   @Override
   public Collection<V> removeAll(@Nullable Object var1) {
      return this.method1().removeAll(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public Collection<V> replaceValues(K var1, Iterable<? extends V> var2) {
      return this.method1().replaceValues((K)var1, var2);
   }

   @Override
   public int size() {
      return this.method1().size();
   }

   @Override
   public Collection<V> values() {
      return this.method1().values();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return var1 == this || this.method1().equals(var1);
   }

   @Override
   public int hashCode() {
      return this.method1().hashCode();
   }
}
