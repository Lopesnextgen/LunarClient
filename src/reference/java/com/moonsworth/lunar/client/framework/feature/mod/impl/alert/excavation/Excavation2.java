package com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin.ExcavationType3;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Excavation2 {
   private final ExcavationType3[][] field1;

   private Excavation2(ExcavationType3[][] var1) {
      this.field1 = var1;
   }

   public static Excavation2 method1(List<ItemStackBridge> var0) {
      ExcavationType3[][] var1 = new ExcavationType3[9][];

      for (int var2 = 0; var2 < 9; var2++) {
         var1[var2] = new ExcavationType3[6];

         for (int var3 = 0; var3 < 6; var3++) {
            int var4 = var2 + var3 * 9;
            ItemStackBridge var5 = var4 < var0.size() ? (ItemStackBridge)var0.get(var4) : null;
            ExcavationType3 var6 = ExcavationType3.from(var5);
            var1[var2][var3] = var6;
         }
      }

      return new Excavation2(var1);
   }

   public List<Excavation> method2() {
      return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new ExcavationIterator(), 0), false)
         .filter(var1 -> var1.method2(this))
         .collect(Collectors.toList());
   }

   public ExcavationType3 method3(int var1, int var2) {
      return this.field1[var1][var2];
   }

   public boolean method4() {
      for (int var1 = 0; var1 < 9; var1++) {
         for (int var2 = 0; var2 < 6; var2++) {
            if (this.field1[var1][var2] == ExcavationType3.FOSSIL) {
               return true;
            }
         }
      }

      return false;
   }
}
