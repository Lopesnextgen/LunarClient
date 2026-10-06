package com.moonsworth.lunar.client.framework.feature.itemtracker;

import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Generated;

class Itemtracker {
   private final Object2ObjectMap<String, List<Itemtracker.Data>> field1 = new Object2ObjectOpenHashMap();

   public Itemtracker(Itemtracker var1) {
      this.field1.putAll(var1.field1);
   }

   public Set<String> keySet() {
      return this.field1.keySet();
   }

   public int method1(String var1) {
      List var2 = (List)this.field1.get(var1);
      if (var2 == null) {
         return 0;
      }

      int var3 = 0;

      for (Itemtracker.Data var5 : var2) {
         var3 += var5.field2;
      }

      return var3;
   }

   public int method2(String var1, Int2BooleanMap var2) {
      List var3 = (List)this.field1.get(var1);
      if (var3 == null) {
         return 0;
      }

      int var4 = 0;

      for (Itemtracker.Data var6 : var3) {
         if (!var2.get(var6.field1)) {
            var4 += var6.field2;
         }
      }

      return var4;
   }

   public void method3(String var1, int var2, int var3) {
      ((List)this.field1.computeIfAbsent(var1, var0 -> new ArrayList())).add(new Itemtracker.Data(var2, var3));
   }

   public void remove(String var1) {
      this.field1.remove(var1);
   }

   @Generated
   public Itemtracker() {
   }

   private class Data {
      private final int field1;
      private final int field2;

      private Data(int var1, int var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public int method1() {
         return this.field1;
      }

      public int count() {
         return this.field2;
      }
   }
}
