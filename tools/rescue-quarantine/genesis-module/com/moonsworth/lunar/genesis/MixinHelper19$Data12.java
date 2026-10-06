package com.moonsworth.lunar.genesis;

import java.util.SortedMap;
import com.google.common.collect.Maps;

class MixinHelper19$Data12<K, V1, V2> extends MixinHelper19$Data27<K, V1, V2> implements SortedMap<K, V2> {
   protected SortedMap<K, V1> fromMap() {
      return (SortedMap<K, V1>)this.field1;
   }

   MixinHelper19$Data12(SortedMap<K, V1> var1, MixinHelper19$Extension<? super K, ? super V1, V2> var2) {
      super(var1, var2);
   }

   @Override
   public java.util.Comparator<? super K> comparator() {
      return this.fromMap().comparator();
   }

   @Override
   public K firstKey() {
      return this.fromMap().firstKey();
   }

   @Override
   public SortedMap<K, V2> headMap(K var1) {
      return Maps.method25(this.fromMap().headMap((K)var1), this.field2);
   }

   @Override
   public K lastKey() {
      return this.fromMap().lastKey();
   }

   @Override
   public SortedMap<K, V2> subMap(K var1, K var2) {
      return Maps.method25(this.fromMap().subMap((K)var1, (K)var2), this.field2);
   }

   @Override
   public SortedMap<K, V2> tailMap(K var1) {
      return Maps.method25(this.fromMap().tailMap((K)var1), this.field2);
   }
}
