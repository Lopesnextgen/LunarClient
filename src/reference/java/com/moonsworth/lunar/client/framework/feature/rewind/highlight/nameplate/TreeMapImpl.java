package com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate;

import java.util.Comparator;
import java.util.TreeMap;
import lombok.Generated;

public class TreeMapImpl<K, V> extends TreeMap<K, V> implements Nameplate<K, V> {
   private final Nameplate2 field1;

   public TreeMapImpl(Nameplate2 var1, Comparator<K> var2) {
      super(var2);
      this.field1 = var1;
   }

   public void method1(Runnable var1) {
      this.method3(this.field1, var1);
   }

   @Override
   public V get(Object var1) {
      return super.get(var1);
   }

   @Override
   public V put(K var1, V var2) {
      return (V)this.method3(this.field1, this, var1, var2);
   }

   @Override
   public V method3(K var1, V var2) {
      return super.put((K)var1, (V)var2);
   }

   @Override
   public V remove(Object var1) {
      return (V)this.method3(this.field1, this, var1);
   }

   @Override
   public V method5(Object var1) {
      return super.remove(var1);
   }

   @Override
   public void clear() {
      this.method3(this.field1, this);
   }

   @Override
   public void method7() {
      super.clear();
   }

   @Generated
   public TreeMapImpl(Nameplate2 var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2 method5() {
      return this.field1;
   }
}
