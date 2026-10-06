package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Table;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class MixinHelper314<R, C, V> extends MixinHelper31_3 implements Table<R, C, V> {
   protected MixinHelper314() {
   }

   protected abstract Table<R, C, V> method1();

   @Override
   public Set<MixinHelper24$Extension<R, C, V>> cellSet() {
      return this.method1().cellSet();
   }

   @Override
   public void clear() {
      this.method1().clear();
   }

   @Override
   public Map<R, V> column(C var1) {
      return this.method1().column((C)var1);
   }

   @Override
   public Set<C> columnKeySet() {
      return this.method1().columnKeySet();
   }

   @Override
   public Map<C, Map<R, V>> columnMap() {
      return this.method1().columnMap();
   }

   @Override
   public boolean contains(Object var1, Object var2) {
      return this.method1().contains(var1, var2);
   }

   @Override
   public boolean containsColumn(Object var1) {
      return this.method1().containsColumn(var1);
   }

   @Override
   public boolean containsRow(Object var1) {
      return this.method1().containsRow(var1);
   }

   @Override
   public boolean containsValue(Object var1) {
      return this.method1().containsValue(var1);
   }

   @Override
   public V get(Object var1, Object var2) {
      return this.method1().get(var1, var2);
   }

   @Override
   public boolean isEmpty() {
      return this.method1().isEmpty();
   }

   @CanIgnoreReturnValue
   @Override
   public V put(R var1, C var2, V var3) {
      return this.method1().put((R)var1, (C)var2, (V)var3);
   }

   @Override
   public void method1(Table<? extends R, ? extends C, ? extends V> var1) {
      this.method1().method1(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public V remove(Object var1, Object var2) {
      return this.method1().remove(var1, var2);
   }

   @Override
   public Map<C, V> row(R var1) {
      return this.method1().row((R)var1);
   }

   @Override
   public Set<R> rowKeySet() {
      return this.method1().rowKeySet();
   }

   @Override
   public Map<R, Map<C, V>> rowMap() {
      return this.method1().rowMap();
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
   public boolean equals(Object var1) {
      return var1 == this || this.method1().equals(var1);
   }

   @Override
   public int hashCode() {
      return this.method1().hashCode();
   }
}
