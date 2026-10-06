package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

class MixinHelper$Data39<K, V> extends MixinHelper$Data35 implements Multimap<K, V> {
   transient @Nullable Set<K> keySet;
   transient @Nullable Collection<V> valuesCollection;
   transient @Nullable Collection<Entry<K, V>> entries;
   transient @Nullable Map<K, Collection<V>> asMap;
   transient @Nullable Multiset<K> field4;
   private static final long field5 = 0L;

   Multimap<K, V> method1() {
      return (Multimap<K, V>)super.delegate();
   }

   MixinHelper$Data39(Multimap<K, V> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   @Override
   public int size() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().size();
      }
   }

   @Override
   public boolean isEmpty() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().isEmpty();
      }
   }

   @Override
   public boolean containsKey(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().containsKey(var1);
      }
   }

   @Override
   public boolean containsValue(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().containsValue(var1);
      }
   }

   @Override
   public boolean containsEntry(Object var1, Object var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().containsEntry(var1, var2);
      }
   }

   @Override
   public Collection<V> get(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$400(this.method1().get((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public boolean put(K var1, V var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().put((K)var1, (V)var2);
      }
   }

   @Override
   public boolean putAll(K var1, Iterable<? extends V> var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().putAll((K)var1, var2);
      }
   }

   @Override
   public boolean method1(Multimap<? extends K, ? extends V> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().method1(var1);
      }
   }

   @Override
   public Collection<V> replaceValues(K var1, Iterable<? extends V> var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().replaceValues((K)var1, var2);
      }
   }

   @Override
   public boolean remove(Object var1, Object var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().remove(var1, var2);
      }
   }

   @Override
   public Collection<V> removeAll(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().removeAll(var1);
      }
   }

   @Override
   public void clear() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.method1().clear();
      }
   }

   @Override
   public Set<K> keySet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.keySet == null) {
            this.keySet = MixinHelper_8.access$300(this.method1().keySet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.keySet;
      }
   }

   @Override
   public Collection<V> values() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.valuesCollection == null) {
            this.valuesCollection = MixinHelper_8.access$500(this.method1().values(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.valuesCollection;
      }
   }

   @Override
   public Collection<Entry<K, V>> entries() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.entries == null) {
            this.entries = MixinHelper_8.access$400(this.method1().entries(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.entries;
      }
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.method1().forEach(var1);
      }
   }

   @Override
   public Map<K, Collection<V>> asMap() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.asMap == null) {
            this.asMap = new MixinHelper$Data30<>(this.method1().asMap(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.asMap;
      }
   }

   @Override
   public Multiset<K> method2() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.field4 == null) {
            this.field4 = MixinHelper_8.method1(this.method1().method2(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.field4;
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      }

      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().equals(var1);
      }
   }

   @Override
   public int hashCode() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().hashCode();
      }
   }
}
