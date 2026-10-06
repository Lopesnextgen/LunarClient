package com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate;

import java.util.LinkedHashMap;
import java.util.function.Consumer;
import lombok.Generated;

public class LinkedHashMapImpl<K, V> extends LinkedHashMap<K, V> implements Nameplate<K, V> {
   private final Nameplate2 field1;
   private Consumer<K> field2;

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
      if (this.field2 != null) {
         this.field2.accept((K)var1);
      }

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
   public LinkedHashMapImpl(Nameplate2 var1) {
      this.field1 = var1;
   }

   @Generated
   public void method5(Consumer<K> var1) {
      this.field2 = var1;
   }
}
