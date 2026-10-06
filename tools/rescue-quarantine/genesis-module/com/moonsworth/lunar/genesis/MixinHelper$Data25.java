package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data25<K, V> extends MixinHelper$Data35 implements Map<K, V> {
   transient @Nullable Set<K> keySet;
   transient @Nullable Collection<V> values;
   transient @Nullable Set<Entry<K, V>> entrySet;
   private static final long field4 = 0L;

   MixinHelper$Data25(Map<K, V> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   Map<K, V> delegate() {
      return (Map<K, V>)super.delegate();
   }

   @Override
   public void clear() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().clear();
      }
   }

   @Override
   public boolean containsKey(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().containsKey(var1);
      }
   }

   @Override
   public boolean containsValue(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().containsValue(var1);
      }
   }

   @Override
   public Set<Entry<K, V>> entrySet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.entrySet == null) {
            this.entrySet = MixinHelper_8.set(this.delegate().entrySet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.entrySet;
      }
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().forEach(var1);
      }
   }

   @Override
   public V get(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().get(var1);
      }
   }

   @Override
   public V getOrDefault(Object var1, V var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().getOrDefault(var1, (V)var2);
      }
   }

   @Override
   public boolean isEmpty() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().isEmpty();
      }
   }

   @Override
   public Set<K> keySet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.keySet == null) {
            this.keySet = MixinHelper_8.set(this.delegate().keySet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.keySet;
      }
   }

   @Override
   public V put(K var1, V var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().put((K)var1, (V)var2);
      }
   }

   @Override
   public V putIfAbsent(K var1, V var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().putIfAbsent((K)var1, (V)var2);
      }
   }

   @Override
   public boolean replace(K var1, V var2, V var3) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().replace((K)var1, (V)var2, (V)var3);
      }
   }

   @Override
   public V replace(K var1, V var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().replace((K)var1, (V)var2);
      }
   }

   @Override
   public V computeIfAbsent(K var1, Function<? super K, ? extends V> var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().computeIfAbsent((K)var1, var2);
      }
   }

   @Override
   public V computeIfPresent(K var1, BiFunction<? super K, ? super V, ? extends V> var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().computeIfPresent((K)var1, var2);
      }
   }

   @Override
   public V compute(K var1, BiFunction<? super K, ? super V, ? extends V> var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().compute((K)var1, var2);
      }
   }

   @Override
   public V merge(K var1, V var2, BiFunction<? super V, ? super V, ? extends V> var3) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().merge((K)var1, (V)var2, var3);
      }
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().putAll(var1);
      }
   }

   @Override
   public void replaceAll(BiFunction<? super K, ? super V, ? extends V> var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().replaceAll(var1);
      }
   }

   @Override
   public V remove(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().remove(var1);
      }
   }

   @Override
   public boolean remove(Object var1, Object var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().remove(var1, var2);
      }
   }

   @Override
   public int size() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().size();
      }
   }

   @Override
   public Collection<V> values() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.values == null) {
            this.values = MixinHelper_8.access$500(this.delegate().values(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.values;
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      }

      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().equals(var1);
      }
   }

   @Override
   public int hashCode() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().hashCode();
      }
   }
}
