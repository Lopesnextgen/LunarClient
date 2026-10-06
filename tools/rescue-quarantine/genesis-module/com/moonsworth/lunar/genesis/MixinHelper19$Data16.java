package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import com.google.common.collect.Sets;

class MixinHelper19$Data16<K, V> extends MixinHelper19$Data18<K, V> {
   final PredicateExtension<? super K> field3;

   MixinHelper19$Data16(Map<K, V> var1, PredicateExtension<? super K> var2, PredicateExtension<? super Entry<K, V>> var3) {
      super(var1, var3);
      this.field3 = var2;
   }

   @Override
   protected Set<Entry<K, V>> createEntrySet() {
      return Sets.method7(this.field1.entrySet(), this.field3);
   }

   @Override
   Set<K> createKeySet() {
      return Sets.method7(this.field1.keySet(), this.field3);
   }

   @Override
   public boolean containsKey(Object var1) {
      return this.field1.containsKey(var1) && this.field3.apply((K)var1);
   }
}
