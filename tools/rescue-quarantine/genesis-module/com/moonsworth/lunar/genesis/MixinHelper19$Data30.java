package com.moonsworth.lunar.genesis;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Map.Entry;

class MixinHelper19$Data30<K, V> extends MixinHelper19$Data10<K, V> implements SortedMap<K, V> {
   MixinHelper19$Data30(SortedMap<K, V> var1, PredicateExtension<? super Entry<K, V>> var2) {
      super(var1, var2);
   }

   SortedMap<K, V> sortedMap() {
      return (SortedMap<K, V>)this.field1;
   }

   public SortedSet<K> keySet() {
      return (SortedSet<K>)super.keySet();
   }

   SortedSet<K> createKeySet() {
      return new MixinHelper19$Data30.Data();
   }

   @Override
   public java.util.Comparator<? super K> comparator() {
      return this.sortedMap().comparator();
   }

   @Override
   public K firstKey() {
      return this.keySet().iterator().next();
   }

   @Override
   public K lastKey() {
      SortedMap var1 = this.sortedMap();

      while (true) {
         Object var2 = var1.lastKey();
         if (this.apply(var2, (V)this.field1.get(var2))) {
            return (K)var2;
         }

         var1 = this.sortedMap().headMap((K)var2);
      }
   }

   @Override
   public SortedMap<K, V> headMap(K var1) {
      return new MixinHelper19$Data30<>(this.sortedMap().headMap((K)var1), this.field2);
   }

   @Override
   public SortedMap<K, V> subMap(K var1, K var2) {
      return new MixinHelper19$Data30<>(this.sortedMap().subMap((K)var1, (K)var2), this.field2);
   }

   @Override
   public SortedMap<K, V> tailMap(K var1) {
      return new MixinHelper19$Data30<>(this.sortedMap().tailMap((K)var1), this.field2);
   }

   class Data extends MixinHelper19$Data10<K, V>.Data implements SortedSet<K> {
      @Override
      public java.util.Comparator<? super K> comparator() {
         return MixinHelper19$Data30.this.sortedMap().comparator();
      }

      @Override
      public SortedSet<K> subSet(K var1, K var2) {
         return (SortedSet<K>)MixinHelper19$Data30.this.subMap((K)var1, (K)var2).keySet();
      }

      @Override
      public SortedSet<K> headSet(K var1) {
         return (SortedSet<K>)MixinHelper19$Data30.this.headMap((K)var1).keySet();
      }

      @Override
      public SortedSet<K> tailSet(K var1) {
         return (SortedSet<K>)MixinHelper19$Data30.this.tailMap((K)var1).keySet();
      }

      @Override
      public K first() {
         return MixinHelper19$Data30.this.firstKey();
      }

      @Override
      public K last() {
         return MixinHelper19$Data30.this.lastKey();
      }
   }
}
