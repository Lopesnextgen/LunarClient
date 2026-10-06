package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Table;
import com.google.common.collect.Maps;

final class MixinHelper$Data38<R, C, V> extends MixinHelper$Data35 implements Table<R, C, V> {
   MixinHelper$Data38(Table<R, C, V> var1, Object var2) {
      super(var1, var2);
   }

   Table<R, C, V> method1() {
      return (Table<R, C, V>)super.delegate();
   }

   @Override
   public boolean contains(@Nullable Object var1, @Nullable Object var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().contains(var1, var2);
      }
   }

   @Override
   public boolean containsRow(@Nullable Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().containsRow(var1);
      }
   }

   @Override
   public boolean containsColumn(@Nullable Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().containsColumn(var1);
      }
   }

   @Override
   public boolean containsValue(@Nullable Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().containsValue(var1);
      }
   }

   @Override
   public V get(@Nullable Object var1, @Nullable Object var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().get(var1, var2);
      }
   }

   @Override
   public boolean isEmpty() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().isEmpty();
      }
   }

   @Override
   public int size() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().size();
      }
   }

   @Override
   public void clear() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.method1().clear();
      }
   }

   @Override
   public V put(@Nullable R var1, @Nullable C var2, @Nullable V var3) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().put((R)var1, (C)var2, (V)var3);
      }
   }

   @Override
   public void method1(Table<? extends R, ? extends C, ? extends V> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.method1().method1(var1);
      }
   }

   @Override
   public V remove(@Nullable Object var1, @Nullable Object var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().remove(var1, var2);
      }
   }

   @Override
   public Map<C, V> row(@Nullable R var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.map(this.method1().row((R)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Map<R, V> column(@Nullable C var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.map(this.method1().column((C)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Set<MixinHelper24$Extension<R, C, V>> cellSet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.set(this.method1().cellSet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Set<R> rowKeySet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.set(this.method1().rowKeySet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Set<C> columnKeySet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.set(this.method1().columnKeySet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Collection<V> values() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$500(this.method1().values(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Map<R, Map<C, V>> rowMap() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.map(Maps.method21(this.method1().rowMap(), new MixinHelper24_2<Map<C, V>, Map<C, V>>() {
            public Map<C, V> apply(Map<C, V> var1) {
               return MixinHelper_8.map(var1, MixinHelper$Data38.this.RRCCHORICIIHRICRICOROHRHOCHRIC);
            }
         }), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Map<C, Map<R, V>> columnMap() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.map(Maps.method21(this.method1().columnMap(), new MixinHelper24_2<Map<R, V>, Map<R, V>>() {
            public Map<R, V> apply(Map<R, V> var1) {
               return MixinHelper_8.map(var1, MixinHelper$Data38.this.RRCCHORICIIHRICRICOROHRHOCHRIC);
            }
         }), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public int hashCode() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().hashCode();
      }
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      }

      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().equals(var1);
      }
   }
}
