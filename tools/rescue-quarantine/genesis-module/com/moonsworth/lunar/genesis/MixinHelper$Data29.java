package com.moonsworth.lunar.genesis;

import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;

@Annotation3
@Annotation4
class MixinHelper$Data29<K, V> extends MixinHelper$Data33<K, V> implements NavigableMap<K, V> {
   transient @Nullable NavigableSet<K> descendingKeySet;
   transient @Nullable NavigableMap<K, V> descendingMap;
   transient @Nullable NavigableSet<K> navigableKeySet;
   private static final long field6 = 0L;

   MixinHelper$Data29(NavigableMap<K, V> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   NavigableMap<K, V> delegate() {
      return (NavigableMap<K, V>)super.delegate();
   }

   @Override
   public Entry<K, V> ceilingEntry(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$700(this.delegate().ceilingEntry((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public K ceilingKey(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().ceilingKey((K)var1);
      }
   }

   @Override
   public NavigableSet<K> descendingKeySet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.descendingKeySet == null
            ? (this.descendingKeySet = MixinHelper_8.navigableSet(this.delegate().descendingKeySet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC))
            : this.descendingKeySet;
      }
   }

   @Override
   public NavigableMap<K, V> descendingMap() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.descendingMap == null
            ? (this.descendingMap = MixinHelper_8.navigableMap(this.delegate().descendingMap(), this.RRCCHORICIIHRICRICOROHRHOCHRIC))
            : this.descendingMap;
      }
   }

   @Override
   public Entry<K, V> firstEntry() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$700(this.delegate().firstEntry(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Entry<K, V> floorEntry(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$700(this.delegate().floorEntry((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public K floorKey(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().floorKey((K)var1);
      }
   }

   @Override
   public NavigableMap<K, V> headMap(K var1, boolean var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.navigableMap(this.delegate().headMap((K)var1, var2), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedMap<K, V> headMap(K var1) {
      return this.headMap((K)var1, false);
   }

   @Override
   public Entry<K, V> higherEntry(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$700(this.delegate().higherEntry((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public K higherKey(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().higherKey((K)var1);
      }
   }

   @Override
   public Entry<K, V> lastEntry() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$700(this.delegate().lastEntry(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Entry<K, V> lowerEntry(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$700(this.delegate().lowerEntry((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public K lowerKey(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().lowerKey((K)var1);
      }
   }

   @Override
   public Set<K> keySet() {
      return this.navigableKeySet();
   }

   @Override
   public NavigableSet<K> navigableKeySet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.navigableKeySet == null
            ? (this.navigableKeySet = MixinHelper_8.navigableSet(this.delegate().navigableKeySet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC))
            : this.navigableKeySet;
      }
   }

   @Override
   public Entry<K, V> pollFirstEntry() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$700(this.delegate().pollFirstEntry(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public Entry<K, V> pollLastEntry() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$700(this.delegate().pollLastEntry(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public NavigableMap<K, V> subMap(K var1, boolean var2, K var3, boolean var4) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.navigableMap(this.delegate().subMap((K)var1, var2, (K)var3, var4), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedMap<K, V> subMap(K var1, K var2) {
      return this.subMap((K)var1, true, (K)var2, false);
   }

   @Override
   public NavigableMap<K, V> tailMap(K var1, boolean var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.navigableMap(this.delegate().tailMap((K)var1, var2), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedMap<K, V> tailMap(K var1) {
      return this.tailMap((K)var1, true);
   }
}
