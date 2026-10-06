package com.moonsworth.lunar.genesis;

import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;

final class AbstractMapLoader$Data40 implements Entry<K, V> {
   final Object field1;
   Object value;

   AbstractMapLoader$Data40(AbstractMapLoader_2 var1, Object var2, Object var3) {
      this.field2 = var1;
      this.field1 = var2;
      this.value = var3;
   }

   @Override
   public Object getKey() {
      return this.field1;
   }

   @Override
   public Object getValue() {
      return this.value;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof Entry)) {
         return false;
      }

      Entry var2 = (Entry)var1;
      return this.field1.equals(var2.getKey()) && this.value.equals(var2.getValue());
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode() ^ this.value.hashCode();
   }

   @Override
   public Object setValue(Object var1) {
      Object var2 = this.field2.put(this.field1, var1);
      this.value = var1;
      return var2;
   }

   @Override
   public String toString() {
      return this.getKey() + "=" + this.getValue();
   }
}
