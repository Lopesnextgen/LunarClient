package com.moonsworth.lunar.genesis;

import java.util.Map;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

class MixinHelper19$Data36<K, V> implements MapDifference<K, V> {
   final Map<K, V> field1;
   final Map<K, V> field2;
   final Map<K, V> field3;
   final Map<K, MapDifference.Extension<V>> field4;

   MixinHelper19$Data36(Map<K, V> var1, Map<K, V> var2, Map<K, V> var3, Map<K, MapDifference.Extension<V>> var4) {
      this.field1 = Maps.access$100(var1);
      this.field2 = Maps.access$100(var2);
      this.field3 = Maps.access$100(var3);
      this.field4 = Maps.access$100(var4);
   }

   @Override
   public boolean areEqual() {
      return this.field1.isEmpty() && this.field2.isEmpty() && this.field4.isEmpty();
   }

   @Override
   public Map<K, V> entriesOnlyOnLeft() {
      return this.field1;
   }

   @Override
   public Map<K, V> entriesOnlyOnRight() {
      return this.field2;
   }

   @Override
   public Map<K, V> entriesInCommon() {
      return this.field3;
   }

   @Override
   public Map<K, MapDifference.Extension<V>> entriesDiffering() {
      return this.field4;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      }

      if (!(var1 instanceof MapDifference)) {
         return false;
      }

      MapDifference var2 = (MapDifference)var1;
      return this.entriesOnlyOnLeft().equals(var2.entriesOnlyOnLeft())
         && this.entriesOnlyOnRight().equals(var2.entriesOnlyOnRight())
         && this.entriesInCommon().equals(var2.entriesInCommon())
         && this.entriesDiffering().equals(var2.entriesDiffering());
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.entriesOnlyOnLeft(), this.entriesOnlyOnRight(), this.entriesInCommon(), this.entriesDiffering());
   }

   @Override
   public String toString() {
      if (this.areEqual()) {
         return "equal";
      }

      StringBuilder var1 = new StringBuilder("not equal");
      if (!this.field1.isEmpty()) {
         var1.append(": only on left=").append(this.field1);
      }

      if (!this.field2.isEmpty()) {
         var1.append(": only on right=").append(this.field2);
      }

      if (!this.field4.isEmpty()) {
         var1.append(": value differences=").append(this.field4);
      }

      return var1.toString();
   }
}
