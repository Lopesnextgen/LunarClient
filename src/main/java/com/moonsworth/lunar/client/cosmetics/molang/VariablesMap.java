package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Assignable;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

public class VariablesMap {
   private final Object2IntMap<Assignable> field1 = new Object2IntOpenHashMap();
   private int field2 = 2;

   public VariablesMap() {
   }

   public int method1(Assignable assignable1, boolean flag) {
      if (this.field1.containsKey(assignable1)) {
         return this.field1.getInt(assignable1);
      }

      this.field1.put(assignable1, this.field2);
      int number3 = this.field2;
      this.field2 += flag ? 2 : 1;
      return number3;
   }

   public boolean method2(Assignable assignable1) {
      return this.field1.containsKey(assignable1);
   }

   public int method3() {
      return this.field2++;
   }

   @Override
   public String toString() {
      return "VariablesMap{assignableMap=" + this.field1 + ", nextVarId=" + this.field2 + "}";
   }
}
